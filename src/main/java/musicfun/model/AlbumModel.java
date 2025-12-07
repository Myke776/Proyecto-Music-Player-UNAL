package musicfun.model;

public class AlbumModel extends MusicCollectionModel {
	private String artist;
	private int year;
	private String genre;

	public AlbumModel() {
		super();
	}

	public AlbumModel(String name, String artist) {
		super(name);
		this.artist = artist;
	}

	public AlbumModel(String name, String artist, int year) {
		this(name, artist);
		this.year = year;
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

	@Override
	public boolean equals(Object obj) {
		if(!super.equals(obj)) return false;

		AlbumModel obj2 = (AlbumModel) obj;
		return obj2.getArtist().toLowerCase().equals(this.getArtist().toLowerCase());
	}
}