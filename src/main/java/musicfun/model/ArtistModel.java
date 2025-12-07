package musicfun.model;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ArtistModel extends MusicCollectionModel {
	private ObservableList<AlbumModel> albums;

	public ArtistModel(String name, String description) {
		super(name, description);
		this.albums = FXCollections.observableArrayList();
	}

	public ObservableList<AlbumModel> getAlbums() {
		return albums;
	}

	public void setAlbums(List<AlbumModel> albums) {
		this.albums.setAll(albums);
	}
}
