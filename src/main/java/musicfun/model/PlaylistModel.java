package musicfun.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javafx.collections.ObservableList;

public class PlaylistModel extends MusicCollection {
	private String id;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;

	public PlaylistModel(String id, String name, String description, ObservableList<SongModel> songs,
			LocalDateTime createdDate, LocalDateTime modifiedDate) {
		super(name, description, songs);
		this.id = id;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public PlaylistModel(String name, String description) {
		super(name, description);
		String id = UUID.randomUUID().toString();
		this.id = id;
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
	}

	public PlaylistModel(String name) {
		this(name, "");
	}

	public String getId() {
		return id;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	@Override
	protected void onSongAdded(SongModel song) {
		modifiedDate = LocalDateTime.now();
	}

	@Override
	protected void onSongRemoved(SongModel song) {
		modifiedDate = LocalDateTime.now();
	}

	@Override
	protected void onSongsClear() {
		modifiedDate = LocalDateTime.now();
	}

	@Override
	protected void onSongsSet(List<SongModel> songs) {
		modifiedDate = LocalDateTime.now();
	}

	@Override
	protected void onName(String name) {
		modifiedDate = LocalDateTime.now();

	}

	@Override
	protected void onDescription(String description) {
		modifiedDate = LocalDateTime.now();
	}
}