package musicfun.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PlaylistModel {
	private String id;
	private String name;
	private String description;
	private List<SongModel> songs;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;

	public PlaylistModel() {
		this.songs = new ArrayList<>();
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
	}

	public PlaylistModel(String name) {
		this();
		this.name = name;
	}

	public PlaylistModel(String name, String description) {
		this();
		this.name = name;
		this.description = description;
	}

	public void addSong(SongModel song) {
		if (!songs.contains(song)) {
			songs.add(song);
			modifiedDate = LocalDateTime.now();
		}
	}

	public void removeSong(SongModel song) {
		if (this.songs.remove(song)) {
			this.modifiedDate = LocalDateTime.now();
		}
	}

	public void removeSong(int index) {
		if (index >= 0 && index < this.songs.size()) {
			this.songs.remove(index);
			this.modifiedDate = LocalDateTime.now();
		}
	}

	public boolean containsSong(SongModel song) {
		return this.songs.contains(song);
	}

	public int getSongCount() {
		return this.songs.size();
	}

	public long getTotalDuration() {
		return this.songs.stream().mapToLong(SongModel::getDuration).sum();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.modifiedDate = LocalDateTime.now();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		this.modifiedDate = LocalDateTime.now();
	}

	public List<SongModel> getSongs() {
		return new ArrayList<>(songs);
	}

	public void setSongs(List<SongModel> songs) {
		this.songs = new ArrayList<>(songs);
		this.modifiedDate = LocalDateTime.now();
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}
}