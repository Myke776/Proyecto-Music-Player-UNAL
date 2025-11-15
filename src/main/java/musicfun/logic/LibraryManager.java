package musicfun.logic;

import musicfun.model.AlbumModel;
import musicfun.model.LibraryModel;
import musicfun.model.PlaylistModel;
import musicfun.model.SongModel;
import musicfun.service.MetadataService;
import musicfun.service.MultimediaSearch;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryManager {
	private LibraryModel library;
	private MetadataService metadataReader;

	public enum SortType {
		TITLE,
		ARTIST,
		ALBUM,
		YEAR
	}

	public LibraryManager() {
		this.library = new LibraryModel();
		this.metadataReader = new MetadataService();
	}

	public void scanFolder() {
		List<String> folders = List.of(
				"C:\\Users\\Oscar\\Downloads"
		// , "C:\\Users\\Oscar\\Documents"
		);
		List<String> paths = MultimediaSearch.getWindowsAudioPaths(folders);

		for (String rute : paths) {
			SongModel song = metadataReader.readMetadata(rute);
			library.addSong(song);
		}

		sortSongs(SortType.TITLE, true);
	}

	public List<SongModel> searchSongs(String query) {
		if (query == null || query.trim().isEmpty()) {
			return this.getSongs();
		}

		String lowerQuery = query.toLowerCase();
		return this.getSongs().stream()
				.filter(song -> song.getTitle().toLowerCase().contains(lowerQuery) ||
						song.getArtist().toLowerCase().contains(lowerQuery) ||
						song.getAlbum().toLowerCase().contains(lowerQuery) ||
						(song.getGenre() != null && song.getGenre().toLowerCase().contains(lowerQuery)))
				.collect(Collectors.toList());
	}

	public List<SongModel> getSongsByArtist(String artist) {
		return this.getSongs().stream()
				.filter(song -> song.getArtist().equalsIgnoreCase(artist))
				.collect(Collectors.toList());
	}

	public List<SongModel> getSongsByAlbum(String album) {
		return this.getSongs().stream()
				.filter(song -> song.getAlbum().equalsIgnoreCase(album))
				.collect(Collectors.toList());
	}

	public List<SongModel> getSongsByGenre(String genre) {
		return this.getSongs().stream()
				.filter(song -> song.getGenre() != null && song.getGenre().equalsIgnoreCase(genre))
				.collect(Collectors.toList());
	}

	public List<SongModel> getRecentlyAddedSongs(int limit) {
		// En una implementación real, esto usaría fechas de incorporación
		List<SongModel> allSongs = this.getSongs();
		int startIndex = Math.max(0, allSongs.size() - limit);
		return allSongs.subList(startIndex, allSongs.size());
	}

	public boolean removeSong(SongModel song) {
		library.removeSong(song);
		return true;
	}

	public String getLibraryStats() {
		int totalSongs = library.getTotalSongCount();
		int totalArtists = this.getAllArtists().size();
		int totalAlbums = library.getTotalAlbumCount();
		long totalDuration = this.getSongs().stream().mapToLong(SongModel::getDuration).sum();

		//Cambiar por funciones que ya se crearon en manejo de tiempo
		long totalMinutes = totalDuration / 60000;
		long hours = totalMinutes / 60;
		long minutes = totalMinutes % 60;

		return String.format(
				"Canciones: %d | Artistas: %d | Álbumes: %d | Duración: %d:%02d horas",
				totalSongs, totalArtists, totalAlbums, hours, minutes);
	}

	public PlaylistModel getPlaylistByName(String name) {
		return library.getPlaylists().stream()
				.filter(playlist -> playlist.getName().equalsIgnoreCase(name))
				.findFirst()
				.orElse(null);
	}

	public AlbumModel getAlbumByTitleAndArtist(String title, String artist) {
		return library.getAlbums().stream()
				.filter(album -> album.getTitle().equalsIgnoreCase(title) &&
						album.getArtist().equalsIgnoreCase(artist))
				.findFirst()
				.orElse(null);
	}

	public List<String> getAllArtists() {
		return this.getSongs().stream()
				.map(SongModel::getArtist)
				.distinct()
				.sorted()
				.collect(Collectors.toList());
	}

	public List<String> getAllAlbums() {
		return this.getSongs().stream()
				.map(SongModel::getAlbum)
				.distinct()
				.sorted()
				.collect(Collectors.toList());
	}

	public List<String> getAllGenres() {
		return this.getSongs().stream()
				.map(SongModel::getGenre)
				.filter(genre -> genre != null && !genre.trim().isEmpty())
				.distinct()
				.sorted()
				.collect(Collectors.toList());
	}

	public List<SongModel> getRecentlyAddedSongs() {
		List<SongModel> recentlyAdded = new ArrayList<>(this.getSongs());
		recentlyAdded.sort(Comparator.comparing(SongModel::getCreation));
		return recentlyAdded;
	}

	public List<SongModel> getSongs() {
		return library.getSongs();
	}

	public List<PlaylistModel> getPlayLists() {
		return library.getPlaylists();
	}

	public List<AlbumModel> getAlbums() {
		return library.getAlbums();
	}

	public void sortSongs(List<SongModel> songs, SortType type, boolean ascending) {
		Comparator<SongModel> comparator;

		switch (type) {
			case ARTIST: 
				comparator = Comparator.comparing(SongModel::getArtist, String.CASE_INSENSITIVE_ORDER);
				break;
			case ALBUM:
				comparator = Comparator.comparing(SongModel::getAlbum, String.CASE_INSENSITIVE_ORDER);
				break;
			case YEAR :
				comparator = Comparator.comparing(SongModel::getYear); // Ver aqui para poder manejar años/fechas adecuadamente.
				break;
			default: 
				comparator = Comparator.comparing(SongModel::getTitle, String.CASE_INSENSITIVE_ORDER);
				break;
		}

		if (!ascending) {
			comparator = comparator.reversed();
		}

		songs.sort(comparator);
	}

	public void sortSongs(SortType type, boolean ascending) {
		this.sortSongs(getSongs(), type, ascending);
	}
}