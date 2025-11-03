package musicfun.model;

import java.util.ArrayList;
import java.util.List;

public class AlbumModel {
	private String title;
	private String artist;
	private int year;
	private String genre;
	private byte[] coverArt;
	private List<SongModel> songs;

	public AlbumModel() {
		this.songs = new ArrayList<>();
	}

	public AlbumModel(String title, String artist) {
		this();
		this.title = title;
		this.artist = artist;
	}

	public AlbumModel(String title, String artist, int year) {
		this(title, artist);
		this.year = year;
	}

	public void addSong(SongModel song) {
		if (!songs.contains(song)) {
			songs.add(song);
		}
	}

	public void removeSong(SongModel song) {
		songs.remove(song);
	}

	public int getSongCount() {
		return songs.size();
	}

	public long getTotalDuration() {
		return songs.stream().mapToLong(SongModel::getDuration).sum();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public byte[] getCoverArt() {
		return coverArt;
	}

	public void setCoverArt(byte[] coverArt) {
		this.coverArt = coverArt;
	}

	public List<SongModel> getSongs() {
		return new ArrayList<>(songs);
	}

	public void setSongs(List<SongModel> songs) {
		this.songs = new ArrayList<>(songs);
	}

	public boolean hasCoverArt() {
		return coverArt != null && coverArt.length > 0;
	}
}