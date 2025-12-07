package musicfun.logic;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import musicfun.model.LibraryModel;
import musicfun.model.PlaylistModel;
import musicfun.model.SongModel;

public class PlaylistLogic {
	private static ObservableList<PlaylistModel> playlists = LibraryModel.getPlaylists();


	public static PlaylistModel createPlaylist(String name) {
		PlaylistModel playlist = new PlaylistModel(name);
		playlists.add(playlist);
		return playlist;
	}

	public static PlaylistModel createPlaylist(String name, List<SongModel> initialSongs) {
		PlaylistModel playlist = new PlaylistModel(name);
		playlist.setSongs(initialSongs);
		playlists.add(playlist);
		return playlist;
	}

	public static boolean deletePlaylist(PlaylistModel playlist) {
		return playlists.remove(playlist);
	}

	public static boolean deletePlaylist(String name) {
		return playlists.removeIf(playlist -> playlist.getName().equals(name));
	}

	public static List<PlaylistModel> findPlaylistsByName(String name) {
		List<PlaylistModel> results = new ArrayList<>();
		for (PlaylistModel playlist : playlists) {
			if (playlist.getName().toLowerCase().contains(name.toLowerCase())) {
				results.add(playlist);
			}
		}
		return results;
	}

	public static void addSongToPlaylist(PlaylistModel playlist, SongModel song) {
		if (!playlist.containsSong(song)) {
			playlist.addSong(song);
		}
	}

	public static void removeSongFromPlaylist(PlaylistModel playlist, SongModel song) {
		playlist.removeSong(song);
	}

	public static void reorderPlaylistSongs(PlaylistModel playlist, int fromIndex, int toIndex) {
		List<SongModel> songs = playlist.getSongs();
		if (fromIndex >= 0 && fromIndex < songs.size() &&
				toIndex >= 0 && toIndex < songs.size()) {
			SongModel song = songs.remove(fromIndex);
			songs.add(toIndex, song);
			playlist.setSongs(songs);
		}
	}

	public static void importSongsToPlaylist(PlaylistModel target, PlaylistModel source) {
		for (SongModel song : source.getSongs()) {
			if (!target.containsSong(song)) {
				target.addSong(song);
			}
		}
	}

	public static PlaylistModel duplicatePlaylist(PlaylistModel original, String newName) {
		PlaylistModel duplicate = new PlaylistModel(newName);
		duplicate.setSongs(original.getSongs());
		playlists.add(duplicate);
		return duplicate;
	}

	public static List<PlaylistModel> getAllPlaylists() {
		return new ArrayList<>(playlists);
	}

	public static int getPlaylistCount() {
		return playlists.size();
	}

	public static PlaylistModel getPlaylistById(String id) {
		return playlists.stream()
				.filter(playlist -> playlist.getId().equals(id))
				.findFirst()
				.orElse(null);
	}

	public static PlaylistModel getPlaylistByName(String name) {
		return LibraryModel.getPlaylists().stream()
				.filter(playlist -> playlist.getName().equalsIgnoreCase(name))
				.findFirst()
				.orElse(null);
	}
}