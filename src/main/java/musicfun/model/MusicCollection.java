package musicfun.model;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class MusicCollection {
	private byte[] cover;
	protected String name;
	protected String description;
	protected ObservableList<SongModel> songs;

	public MusicCollection(String name, String description, ObservableList<SongModel> songs) {
		this.name = name;
		this.description = description;
		this.songs = songs;
	}

	public MusicCollection(String name, String description) {
		this(name, description, null);
		this.songs = FXCollections.observableArrayList();
	}

	public MusicCollection(String name, ObservableList<SongModel> songs) {
		this(name, "", songs);
	}

	public MusicCollection(String name) {
		this(name, "");
	}

	public MusicCollection() {
		this("Unknown");
	}

	public void addSong(SongModel song) {
		if (!songs.contains(song)) {
			this.songs.add(song);
			this.onSongAdded(song);
		}

		if (!this.hasCover() && song != null) {
			this.setCover(song.getCover());
		}
	}

	public void removeSong(SongModel song) {
		if (this.songs.remove(song)) {
			this.onSongRemoved(song);
		}
	}

	public void removeSong(int index) {
		if (index >= 0 && index < this.songs.size()) {
			SongModel song = this.songs.remove(index);
			if (song != null) {
				onSongRemoved(song);
			}
		}
	}

	public void clear() {
		this.songs.clear();
		this.onSongsClear();
		this.cover = null;
	}

	public boolean containsSong(SongModel song) {
		return songs.contains(song);
	}

	public int getTotalSongCount() {
		return this.songs.size();
	}

	public long getTotalDuration() {
		return this.songs.stream().mapToLong(SongModel::getDuration).sum();
	}

	public byte[] getCover() {
		return cover;
	}

	public void setCover(byte[] coverArt) {
		this.cover = coverArt;
	}

	public boolean hasCover() {
		return cover != null && cover.length > 0;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ObservableList<SongModel> getSongs() {
		return this.songs;
	}

	public void setSongs(List<SongModel> songs) {
		this.songs.setAll(songs);
		this.onSongsSet(songs);

		if (!this.hasCover() && songs.size() > 0) {
			this.setCover(songs.get(0).getCover());
		}
	}

	// Para escuchar cambios en las demas clases hijas.
	protected void onSongAdded(SongModel song) {
	};

	protected void onSongRemoved(SongModel song) {
	};

	protected void onSongsClear() {
	};

	protected void onSongsSet(List<SongModel> songs) {
	};

	protected void onName(String name) {
	};

	protected void onDescription(String description) {
	};

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		MusicCollection obj2 = (MusicCollection) obj;
		return obj2.getName().toLowerCase().equals(this.getName().toLowerCase());
	}
}