package musicfun.model;

public class SongModel {
	private String title;
	private String artist;
	private String album;
	private String filePath;
	private long duration;
	private String genre;
	private String year;
	private byte[] cover;

	public SongModel(String title, String artist, String album, String filePath, long duration, String genre,
			String year,
			byte[] cover) {
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.filePath = filePath;
		this.duration = duration;
		this.genre = genre;
		this.year = year;
		this.cover = cover;
	}

	public SongModel(String filePath) {
		this.filePath = filePath;
		this.title = "Unknown";
		this.artist = "Unknown";
		this.album = "Unknown";
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

	public String getFormattedDuration() {
		long seconds = this.duration / 1000;
		long minutes = seconds / 60;
		seconds = seconds % 60;
		return String.format("%02d:%02d", minutes, seconds);
	}

	@Override
	public String toString() {
		return "title: " + this.title + ", duration: " + this.getFormattedDuration() + ", path: " + this.filePath;
	}
}
