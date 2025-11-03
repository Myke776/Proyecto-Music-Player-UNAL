package musicfun.logic;

import musicfun.model.LibraryModel;
import musicfun.model.SongModel;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryManager {
	private LibraryModel library;
	private MetadataReader metadataReader;

	public LibraryManager() {
		this.library = new LibraryModel();
		this.metadataReader = new MetadataReader();
	}

	/**
	 * Escanea una carpeta en busca de archivos de audio
	 */
	public void scanFolder() {
		List<String> folders = List.of(
				"C:\\Users\\Oscar\\Downloads"
		// , "C:\\Users\\Oscar\\Documents"
		);
		List<String> paths = MultimediaSearch.getWindowsAudioPaths(folders);

		for (String rute : paths) {
			File file = new File(rute);
			SongModel song = metadataReader.readMetadata(file);
			library.addSong(song);
		}
	}

	/**
	 * Busca canciones en la biblioteca
	 */
	public List<SongModel> searchSongs(String query) {
		if (query == null || query.trim().isEmpty()) {
			return library.getAllSongs();
		}

		String lowerQuery = query.toLowerCase();
		return library.getAllSongs().stream()
				.filter(song -> song.getTitle().toLowerCase().contains(lowerQuery) ||
						song.getArtist().toLowerCase().contains(lowerQuery) ||
						song.getAlbum().toLowerCase().contains(lowerQuery) ||
						(song.getGenre() != null && song.getGenre().toLowerCase().contains(lowerQuery)))
				.collect(Collectors.toList());
	}

	/**
	 * Filtra canciones por artista
	 */
	public List<SongModel> getSongsByArtist(String artist) {
		return library.getAllSongs().stream()
				.filter(song -> song.getArtist().equalsIgnoreCase(artist))
				.collect(Collectors.toList());
	}

	/**
	 * Filtra canciones por álbum
	 */
	public List<SongModel> getSongsByAlbum(String album) {
		return library.getAllSongs().stream()
				.filter(song -> song.getAlbum().equalsIgnoreCase(album))
				.collect(Collectors.toList());
	}

	/**
	 * Filtra canciones por género
	 */
	public List<SongModel> getSongsByGenre(String genre) {
		return library.getAllSongs().stream()
				.filter(song -> song.getGenre() != null && song.getGenre().equalsIgnoreCase(genre))
				.collect(Collectors.toList());
	}

	/**
	 * Obtiene canciones recién agregadas
	 */
	public List<SongModel> getRecentlyAddedSongs(int limit) {
		// En una implementación real, esto usaría fechas de incorporación
		List<SongModel> allSongs = library.getAllSongs();
		int startIndex = Math.max(0, allSongs.size() - limit);
		return allSongs.subList(startIndex, allSongs.size());
	}

	/**
	 * Elimina una canción de la biblioteca
	 */
	public boolean removeSong(SongModel song) {
		library.removeSong(song);
		return true;
	}

	/**
	 * Obtiene estadísticas de la biblioteca
	 */
	public String getLibraryStats() {
		int totalSongs = library.getTotalSongCount();
		int totalArtists = library.getAllArtists().size();
		int totalAlbums = library.getTotalAlbumCount();
		long totalDuration = library.getAllSongs().stream().mapToLong(SongModel::getDuration).sum();

		long totalMinutes = totalDuration / 60000;
		long hours = totalMinutes / 60;
		long minutes = totalMinutes % 60;

		return String.format(
				"Canciones: %d | Artistas: %d | Álbumes: %d | Duración: %d:%02d horas",
				totalSongs, totalArtists, totalAlbums, hours, minutes);
	}

	// Getters
	public LibraryModel getLibrary() {
		return library;
	}

	public List<String> getAllArtists() {
		return library.getAllArtists();
	}

	public List<String> getAllAlbums() {
		return library.getAllAlbums();
	}

	public List<String> getAllGenres() {
		return library.getAllGenres();
	}

	public List<SongModel> getAllSongs() {
		return library.getAllSongs();
	}
}