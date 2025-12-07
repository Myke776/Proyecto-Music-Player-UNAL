package musicfun.model;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LibraryModel {
	private static ObservableList<SongModel> allSongs = FXCollections.observableArrayList();
	private static ObservableList<PlaylistModel> playlists = FXCollections.observableArrayList();
	private static ObservableList<SongModel> recentlyPlayedSongs = FXCollections.observableArrayList();
	private static ObservableList<AlbumModel> recentlyPlayedAlbums = FXCollections.observableArrayList();
	private static ObservableList<ArtistModel> recentlyPlayedArtists = FXCollections.observableArrayList();

	public static ObservableList<SongModel> getSongs() {
		return allSongs;
	}

	public static int getTotalSongCount() {
		return allSongs.size();
	}

	public static void addSong(SongModel song) {
		if (!allSongs.contains(song)) {
			allSongs.add(song);
		}
	}

	public static void removeSong(SongModel song) {
		allSongs.remove(song);
		playlists.forEach(playlist -> playlist.removeSong(song));
	}

	public static void addPlaylist(PlaylistModel playlist) {
		if (!playlists.contains(playlist)) {
			playlists.add(playlist);
		}
	}

	public static void setPlaylist(List<PlaylistModel> playlist) {
		playlists.setAll(playlist);
	}

	public static ObservableList<PlaylistModel> getPlaylists() {
		return playlists;
	}

	public static void removePlaylist(PlaylistModel playlist) {
		playlists.remove(playlist);
	}

	public static ObservableList<SongModel> getRecentlyPlayedSongs() {
		return recentlyPlayedSongs;
	}

	public static void addToRecentlyPlayedSongs(SongModel song) {
		if(!recentlyPlayedSongs.contains(song)) {
			recentlyPlayedSongs.add(0, song);
		}
	}

	public static void removeRecentlyPlayedSong(SongModel song) {
		recentlyPlayedSongs.remove(song);
	}

	public static void removeRecentlyPlayedSong(int index) {
		recentlyPlayedSongs.remove(index);
	}

	public static void addToRecentlyPlayedAlbums(AlbumModel album) {
		if (!recentlyPlayedAlbums.contains(album)) {
			recentlyPlayedAlbums.add(0, album);
		}
	}

	public static ObservableList<AlbumModel> getRecentlyPlayedAlbums() {
		return recentlyPlayedAlbums;
	}

	public static void removeRecentlyPlayedAlbum(AlbumModel album) {
		recentlyPlayedAlbums.remove(album);
	}

	public static void removeRecentlyPlayedAlbum(int index) {
		recentlyPlayedAlbums.remove(index);
	}

	public static void addToRecentlyPlayedArtists(ArtistModel artist) {
		if (!recentlyPlayedArtists.contains(artist)) {
			recentlyPlayedArtists.add(0, artist);
		}
	}

	public static ObservableList<ArtistModel> getRecentlyPlayedArtists() {
		return recentlyPlayedArtists;
	}

	public static void removeRecentlyPlayedArtist(ArtistModel artist) {
		recentlyPlayedArtists.remove(artist);
	}

	public static void removeRecentlyPlayedArtist(int index) {
		recentlyPlayedArtists.remove(index);
	}

	public static int getTotalPlaylistCount() {
		return playlists.size();
	}

	public static int getTotalRecentlyPlayedSongCount() {
		return recentlyPlayedSongs.size();
	}

	public static int getTotalRecentlyPlayedAlbumCount() {
		return recentlyPlayedAlbums.size();
	}

	public static int getTotalRecentlyPlayedArtistCount() {
		return recentlyPlayedArtists.size();
	}
}