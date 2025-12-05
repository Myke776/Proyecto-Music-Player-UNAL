package musicfun.ui.components.listView;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import musicfun.model.SongModel;

public class ListSong extends ListView<SongModel> {

	public ListSong(ObservableList<SongModel> listSongs, double sizeImage, Orientation orientationCell) {
		super();
		this.createListView(listSongs, sizeImage,Orientation.HORIZONTAL);
	}

	public ListSong(ObservableList<SongModel> listSongs) {
		this(listSongs, 40, Orientation.HORIZONTAL);
	}

	public ListSong(List<SongModel> listSongs, double sizeImage, Orientation orientationCell) {
		ObservableList<SongModel> songs = FXCollections.observableArrayList(listSongs);
		this.createListView(songs, sizeImage, orientationCell);
	}

	public ListSong(List<SongModel> listSongs) {
		this(listSongs, 40, Orientation.HORIZONTAL);
	}

	protected void createListView(ObservableList<SongModel> listSongs, double sizeImage, Orientation orientationCell) {
		super.setItems(listSongs);
		super.setCellFactory(listView -> new CellSong(listSongs, sizeImage, orientationCell));
	}
}
