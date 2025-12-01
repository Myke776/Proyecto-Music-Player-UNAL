package musicfun.model;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ArtistModel extends MusicCollection {
	private ObservableList<String> albums;

	public ArtistModel(String name, String description) {
		super(name, description);
		this.albums = FXCollections.observableArrayList();
	}

	public ObservableList<String> getAlbums() {
		return albums;
	}

	public void setAlbums(List<String> albums) {
		this.albums.setAll(albums);
	}
}
