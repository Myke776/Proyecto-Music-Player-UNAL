package musicfun.model;

import java.util.ArrayList;
import java.util.List;

public class LibraryModel {
	private List<SongModel> allSongs;
	private List<PlaylistModel> playlists;
	private List<AlbumModel> albums;

	public LibraryModel() {
		this.allSongs = new ArrayList<>();
		this.playlists = new ArrayList<>();
		this.albums = new ArrayList<>();
	}

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

	public void addPlaylist(PlaylistModel playlist) {
		if (!playlists.contains(playlist)) {
			playlists.add(playlist);
		}
	}

	public void removePlaylist(PlaylistModel playlist) {
		playlists.remove(playlist);
	}

	public void addAlbum(AlbumModel album) {
		if (!albums.contains(album)) {
			albums.add(album);
		}
	}

	public void removeAlbum(AlbumModel album) {
		albums.remove(album);
	}

	public List<SongModel> getSongs() {
		return allSongs;
	}

	public List<PlaylistModel> getPlaylists() {
		return playlists;
	}

	public List<AlbumModel> getAlbums() {
		return albums;
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
}