package musicfun.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryModel {
	private List<SongModel> allSongs;
	private List<PlaylistModel> playlists;
	private List<AlbumModel> albums;

	public LibraryModel() {
		this.allSongs = new ArrayList<>();
		this.playlists = new ArrayList<>();
		this.albums = new ArrayList<>();
	}

	// Métodos de gestión de canciones
	public void addSong(SongModel song) {
		if (!allSongs.contains(song)) {
			this.allSongs.add(song);
		}
	}

	public void removeSong(SongModel song) {
		allSongs.remove(song);
		// Remover de todas las playlists también
		playlists.forEach(playlist -> playlist.removeSong(song));
	}

	public List<SongModel> searchSongs(String query) {
		if (query == null || query.trim().isEmpty()) {
			return new ArrayList<>(allSongs);
		}

		String lowerQuery = query.toLowerCase();
		return allSongs.stream()
				.filter(song -> song.getTitle().toLowerCase().contains(lowerQuery) ||
						song.getArtist().toLowerCase().contains(lowerQuery) ||
						song.getAlbum().toLowerCase().contains(lowerQuery))
				.collect(Collectors.toList());
	}

	public List<SongModel> getSongsByArtist(String artist) {
		return allSongs.stream()
				.filter(song -> song.getArtist().equalsIgnoreCase(artist))
				.collect(Collectors.toList());
	}

	public List<SongModel> getSongsByAlbum(String album) {
		return allSongs.stream()
				.filter(song -> song.getAlbum().equalsIgnoreCase(album))
				.collect(Collectors.toList());
	}

	// Métodos de gestión de playlists
	public void addPlaylist(PlaylistModel playlist) {
		if (!playlists.contains(playlist)) {
			playlists.add(playlist);
		}
	}

	public void removePlaylist(PlaylistModel playlist) {
		playlists.remove(playlist);
	}

	public PlaylistModel getPlaylistByName(String name) {
		return playlists.stream()
				.filter(playlist -> playlist.getName().equalsIgnoreCase(name))
				.findFirst()
				.orElse(null);
	}

	// Métodos de gestión de álbumes
	public void addAlbum(AlbumModel album) {
		if (!albums.contains(album)) {
			albums.add(album);
		}
	}

	public void removeAlbum(AlbumModel album) {
		albums.remove(album);
	}

	public AlbumModel getAlbumByTitleAndArtist(String title, String artist) {
		return albums.stream()
				.filter(album -> album.getTitle().equalsIgnoreCase(title) &&
						album.getArtist().equalsIgnoreCase(artist))
				.findFirst()
				.orElse(null);
	}

	// Getters (retornar copias para evitar modificación externa)
	public List<SongModel> getAllSongs() {
		return new ArrayList<>(allSongs);
	}

	public List<PlaylistModel> getPlaylists() {
		return new ArrayList<>(playlists);
	}

	public List<AlbumModel> getAlbums() {
		return new ArrayList<>(albums);
	}

	public int getTotalSongCount() {
		return allSongs.size();
	}

	public int getTotalPlaylistCount() {
		return playlists.size();
	}

	public int getTotalAlbumCount() {
		return albums.size();
	}

	// Métodos para obtener listas organizadas
	public List<String> getAllArtists() {
		return allSongs.stream()
				.map(SongModel::getArtist)
				.distinct()
				.sorted()
				.collect(Collectors.toList());
	}

	public List<String> getAllAlbums() {
		return allSongs.stream()
				.map(SongModel::getAlbum)
				.distinct()
				.sorted()
				.collect(Collectors.toList());
	}

	public List<String> getAllGenres() {
		return allSongs.stream()
				.map(SongModel::getGenre)
				.filter(genre -> genre != null && !genre.trim().isEmpty())
				.distinct()
				.sorted()
				.collect(Collectors.toList());
	}
}