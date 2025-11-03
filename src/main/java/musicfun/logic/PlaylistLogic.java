package musicfun.logic;

import musicfun.model.PlaylistModel;
import musicfun.model.SongModel;
import java.util.ArrayList;
import java.util.List;

public class PlaylistLogic {
	private List<PlaylistModel> playlists;

	public PlaylistLogic() {
		this.playlists = new ArrayList<>();
	}

	public PlaylistModel createPlaylist(String name) {
		PlaylistModel playlist = new PlaylistModel(name);
		playlists.add(playlist);
		return playlist;
	}

	public PlaylistModel createPlaylist(String name, List<SongModel> initialSongs) {
		PlaylistModel playlist = new PlaylistModel(name);
		playlist.setSongs(initialSongs);
		playlists.add(playlist);
		return playlist;
	}

	public boolean deletePlaylist(PlaylistModel playlist) {
		return playlists.remove(playlist);
	}

	public boolean deletePlaylist(String name) {
		return playlists.removeIf(playlist -> playlist.getName().equals(name));
	}

	public List<PlaylistModel> findPlaylistsByName(String name) {
		List<PlaylistModel> results = new ArrayList<>();
		for (PlaylistModel playlist : playlists) {
			if (playlist.getName().toLowerCase().contains(name.toLowerCase())) {
				results.add(playlist);
			}
		}
		return results;
	}

	public void addSongToPlaylist(PlaylistModel playlist, SongModel song) {
		if (!playlist.containsSong(song)) {
			playlist.addSong(song);
		}
	}

	public void removeSongFromPlaylist(PlaylistModel playlist, SongModel song) {
		playlist.removeSong(song);
	}

	public void reorderPlaylistSongs(PlaylistModel playlist, int fromIndex, int toIndex) {
		List<SongModel> songs = playlist.getSongs();
		if (fromIndex >= 0 && fromIndex < songs.size() &&
				toIndex >= 0 && toIndex < songs.size()) {
			SongModel song = songs.remove(fromIndex);
			songs.add(toIndex, song);
			playlist.setSongs(songs);
		}
	}

	public void importSongsToPlaylist(PlaylistModel target, PlaylistModel source) {
		for (SongModel song : source.getSongs()) {
			if (!target.containsSong(song)) {
				target.addSong(song);
			}
		}
	}

	public PlaylistModel duplicatePlaylist(PlaylistModel original, String newName) {
		PlaylistModel duplicate = new PlaylistModel(newName);
		duplicate.setSongs(original.getSongs());
		playlists.add(duplicate);
		return duplicate;
	}

	public List<PlaylistModel> getAllPlaylists() {
		return new ArrayList<>(playlists);
	}

	public int getPlaylistCount() {
		return playlists.size();
	}

	public PlaylistModel getPlaylistById(String id) {
		return playlists.stream()
				.filter(playlist -> playlist.getId().equals(id))
				.findFirst()
				.orElse(null);
	}
}