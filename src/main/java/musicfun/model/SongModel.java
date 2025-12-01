package musicfun.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SongModel {
	private String title;
	private String artist;
	private String album;
	private String filePath;
	private long duration;
	private String genre;
	private String year;
	private byte[] cover;
	private LocalDateTime creationDateTime;
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");;

	public SongModel(String title, String artist, String album, String filePath, long duration, String genre,
			String year, byte[] cover, LocalDateTime creationDateTime, LocalDateTime lastModified) {
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.filePath = filePath;
		this.duration = duration;
		this.genre = genre;
		this.year = year;
		this.cover = cover;
		this.creationDateTime = creationDateTime;
	}

	public SongModel(String filePath) {
		this.filePath = filePath;
		this.title = "Unknown";
		this.artist = "Unknown";
		this.album = "Unknown";
		this.genre = "Unknown";
		this.year = "Unknown";
	};

	public String getTitle() {
		return this.title;
	}

	public String getArtist() {
		return this.artist;
	}

	public String getAlbum() {
		return this.album;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public long getDuration() {
		return this.duration;
	}

	public String getGenre() {
		return this.genre;
	}

	public String getYear() {
		return this.year;
	}

	public byte[] getCover() {
		return this.cover;
	}

	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setCover(byte[] cover) {
		this.cover = cover;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public String getFormattedDuration() {
		//Cambiar por servicion, asi se recicla codigo
		long hours = this.duration / 3600;
		long minutes = (this.duration % 3600) / 60;
		long seconds = this.duration % 60;

		if (hours > 0) {
			return String.format("%d:%02d:%02d", hours, minutes, seconds);
		} else {
			return String.format("%02d:%02d", minutes, seconds);
		}
	}

	public String getFormattedCreationDateTime() {
		return this.creationDateTime.format(FORMATTER);
	}

	@Override
	public String toString() {
		return "title: " + this.title + ", duration: " + this.getFormattedDuration() + ", path: " + this.filePath;
	}
}
