package musicfun.logic;

import musicfun.model.AlbumModel;
import musicfun.model.ArtistModel;
import musicfun.model.LibraryModel;
import musicfun.model.PlaylistModel;
import musicfun.model.SongModel;
import musicfun.service.MetadataService;
import musicfun.service.MultimediaSearch;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class LibraryLogic {
	private static MetadataService metadataReader = new MetadataService();
	private static List<String> folders;

	public static enum SortType {
		NAME,
		ARTIST,
		ALBUM,
		YEAR,
		CREATION_DATE_TIME
	}

	private static final int RECENT_SONGS_LIMIT = 50;
	private static final int RECENT_ALBUMS_LIMIT = 20;
	private static final int RECENT_ARTISTS_LIMIT = 20;

	public LibraryLogic(List<String> folders_p) {
		folders = folders_p;
	}

	public LibraryLogic() {
	}

	public static void setFolders(List<String> folders_p) {
		folders = new ArrayList<>(folders_p);
	}

	public static void scanFolder() {
		List<String> paths = MultimediaSearch.getWindowsAudioPaths(folders);

		for (String rute : paths) {
			SongModel song = metadataReader.readMetadata(rute);
			LibraryModel.addSong(song);
		}

		sortSongs(SortType.NAME, true);
	}

	public static List<SongModel> searchSongs(String query) {
		if (query == null || query.trim().isEmpty()) {
			return LibraryModel.getSongs();
		}

		String lowerQuery = query.toLowerCase();
		return LibraryModel.getSongs().stream()
				.filter(song -> song.getTitle().toLowerCase().contains(lowerQuery) ||
						song.getArtist().toLowerCase().contains(lowerQuery) ||
						song.getAlbum().toLowerCase().contains(lowerQuery) ||
						(song.getGenre() != null && song.getGenre().toLowerCase().contains(lowerQuery)))
				.collect(Collectors.toList());
	}

	public static List<SongModel> getSongsByArtist(String artist) {
		return LibraryModel.getSongs().stream()
				.filter(song -> song.getArtist().equalsIgnoreCase(artist))
				.collect(Collectors.toList());
	}

	public static List<SongModel> getSongsByAlbum(String album) {
		return LibraryModel.getSongs().stream()
				.filter(song -> song.getAlbum().equalsIgnoreCase(album))
				.collect(Collectors.toList());
	}

	public static List<SongModel> getSongsByGenre(String genre) {
		return LibraryModel.getSongs().stream()
				.filter(song -> song.getGenre() != null && song.getGenre().equalsIgnoreCase(genre))
				.collect(Collectors.toList());
	}

	public static List<SongModel> getRecentlyAddedSongs(int limit) {
		List<SongModel> allSongs = LibraryModel.getSongs();
		List<SongModel> recentlyAdded = new ArrayList<>(allSongs);
		sortSongs(recentlyAdded, SortType.CREATION_DATE_TIME, false);

		// Ver luego como colocarla en un metodo aparte, para poder mejorar la lista de
		// musica.
		int startIndex;
		if (limit < 0) {
			startIndex = 0;
		} else {
			startIndex = Math.max(0, recentlyAdded.size() - limit);
		}

		return recentlyAdded.subList(startIndex, allSongs.size());
	}

	public static List<SongModel> getRecentlyAddedSongs() {
		List<SongModel> recentlyAdded = getRecentlyAddedSongs(-1);
		return recentlyAdded;
	}

	public static String getLibraryStats() {
		int totalSongs = LibraryModel.getTotalSongCount();
		int totalArtists = getAllArtists().size();
		int totalAlbums = getAllAlbums().size();
		long totalDuration = LibraryModel.getSongs().stream().mapToLong(SongModel::getDuration).sum();

		// Cambiar por funciones que ya se crearon en manejo de tiempo
		long totalMinutes = totalDuration / 60000;
		long hours = totalMinutes / 60;
		long minutes = totalMinutes % 60;

		return String.format(
				"Canciones: %d | Artistas: %d | Álbumes: %d | Duración: %d:%02d horas",
				totalSongs, totalArtists, totalAlbums, hours, minutes);
	}

	public static PlaylistModel getPlaylistByName(String name) {
		return LibraryModel.getPlaylists().stream()
				.filter(playlist -> playlist.getName().equalsIgnoreCase(name))
				.findFirst()
				.orElse(null);
	}

	public static List<String> getAllArtists() {
		return LibraryModel.getSongs().stream()
				.map(SongModel::getArtist)
				.distinct()
				.sorted()
				.collect(Collectors.toList());
	}

	public static List<String> getAllAlbums() {
		return LibraryModel.getSongs().stream()
				.map(SongModel::getAlbum)
				.distinct()
				.sorted()
				.collect(Collectors.toList());
	}

	public static List<String> getAllGenres() {
		return LibraryModel.getSongs().stream()
				.filter(genre -> genre.getGenre() != null && !genre.getGenre().trim().isEmpty())
				.map(SongModel::getGenre)
				.distinct()
				.sorted()
				.collect(Collectors.toList());
	}

	public static void sortSongs(List<SongModel> songs, SortType type, boolean ascending) {
		Comparator<SongModel> comparator;

		switch (type) {
			case ARTIST:
				comparator = Comparator.comparing(SongModel::getArtist, String.CASE_INSENSITIVE_ORDER);
				break;
			case ALBUM:
				comparator = Comparator.comparing(SongModel::getAlbum, String.CASE_INSENSITIVE_ORDER);
				break;
			case YEAR:
				comparator = Comparator.comparing(SongModel::getYear);
				break;
			case CREATION_DATE_TIME:
				comparator = Comparator.comparing(SongModel::getCreationDateTime);
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

	public static void sortSongs(SortType type, boolean ascending) {
		sortSongs(LibraryModel.getSongs(), type, ascending);
	}

	public static void markSongAsPlayed(SongModel song) {
		updateRecentlyPlayedSongs(song);
        updateRecentlyPlayedAlbums(song);
        updateRecentlyPlayedArtists(song);
	}

	private static void updateRecentlyPlayedSongs(SongModel song) {
        LibraryModel.removeRecentlyPlayedSong(song);
        LibraryModel.addToRecentlyPlayedSongs(song);
        
        if (LibraryModel.getRecentlyPlayedSongs().size() > RECENT_SONGS_LIMIT) {
            LibraryModel.removeRecentlyPlayedSong(RECENT_SONGS_LIMIT);
        }
    }

    private static void updateRecentlyPlayedAlbums(SongModel song) {
        AlbumModel album = getAlbum(song.getAlbum());
        
		LibraryModel.removeRecentlyPlayedAlbum(album);
        LibraryModel.addToRecentlyPlayedAlbums(album);
        
        if (LibraryModel.getRecentlyPlayedAlbums().size() > RECENT_ALBUMS_LIMIT) {
            LibraryModel.removeRecentlyPlayedAlbum(RECENT_ALBUMS_LIMIT);
        }
    }

    private static void updateRecentlyPlayedArtists(SongModel song) {
        ArtistModel artist = getArtist(song.getArtist());
		
		LibraryModel.removeRecentlyPlayedArtist(artist);
        LibraryModel.addToRecentlyPlayedArtists(artist);
        
        if (LibraryModel.getRecentlyPlayedArtists().size() > RECENT_ARTISTS_LIMIT) {
			LibraryModel.removeRecentlyPlayedArtist(RECENT_ARTISTS_LIMIT);
        }
    }

	public static AlbumModel getAlbum(String albumName) {
		List<SongModel> songs = new ArrayList<>(LibraryModel.getSongs().stream().filter(
			song -> song.getAlbum().toLowerCase().equals(albumName.toLowerCase())
		).toList());
		sortSongs(songs, SortType.NAME, false); //Mirar para poder organizar las canciones.

		AlbumModel album = new AlbumModel(albumName, songs.get(0).getArtist());
		album.setSongs(songs);

		return album;
	}

	public static ArtistModel getArtist(String artistName) {
		List<SongModel> songs = LibraryModel.getSongs().stream().filter(
			song -> song.getArtist().equals((artistName))
		).toList();
		// sortSongs(songs, SortType.NAME, false);

		List<String> albums = songs.stream().map(SongModel::getAlbum).toList();

		ArtistModel artirt = new ArtistModel(artistName, "");
		artirt.setSongs(songs);
		artirt.setAlbums(albums);

		return artirt;
	}
}