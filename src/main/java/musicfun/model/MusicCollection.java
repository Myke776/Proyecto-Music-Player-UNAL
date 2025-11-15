package musicfun.model;

import java.util.List;
import java.util.UUID;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class MusicCollection {
	protected String id;
	protected String name;
	protected String description;
	protected ObservableList<SongModel> songs;

	public MusicCollection(String id, String name, String description, ObservableList<SongModel> songs) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.songs = songs;
	}

	public MusicCollection(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.songs = FXCollections.observableArrayList();
	}

	public MusicCollection(String id, String name) {
		this(id, name, "");
	}

	public MusicCollection(String name) {
		this("", name);
		String id = UUID.randomUUID().toString();
		this.id = id;
	}

	public MusicCollection() {
		this("Unknown");
	}

	public void addSong(SongModel song) {
		if (!songs.contains(song)) {
			this.songs.add(song);
			this.onSongAdded(song);
		}
	}

	public void removeSong(SongModel song) {
		if (songs.remove(song)) {
			this.onSongRemoved(song);
		}
	}

	public void clear() {
		this.songs.clear();
		this.onSongsClear();
	}

	public boolean containsSong(SongModel song) {
		return songs.contains(song);
	}

	public int getSongCount() {
		return this.songs.size();
	}

	public long getTotalDuration() {
		return this.songs.stream().mapToLong(SongModel::getDuration).sum();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ObservableList<SongModel> getSongs() {
		return songs;
	}

	public void setSongs(List<SongModel> songs) {
		this.songs.setAll(songs);
		this.onSongsSet(songs);
	}

	protected abstract void onSongAdded(SongModel song);

	protected abstract void onSongRemoved(SongModel song);

	protected abstract void onSongsClear();

	protected abstract void onSongsSet(List<SongModel> songs);
}