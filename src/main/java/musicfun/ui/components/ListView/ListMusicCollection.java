package musicfun.ui.components.ListView;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import musicfun.model.MusicCollection;

public class ListMusicCollection <Collection extends MusicCollection> extends ListView<Collection>{
	public ListMusicCollection(ObservableList<Collection> listCollection, double sizeImage, Orientation orientationCell) {
		super();
		this.createListView(listCollection, sizeImage, orientationCell);
	}

	public ListMusicCollection(ObservableList<Collection> listCollection) {
		this(listCollection, 40, Orientation.HORIZONTAL);
	}

	public ListMusicCollection(List<Collection> listCollection, double sizeImage, Orientation orientationCell) {
		ObservableList<Collection> collections = FXCollections.observableArrayList(listCollection);
		this.createListView(collections, sizeImage, orientationCell);
	}

	public ListMusicCollection(List<Collection> listCollection) {
		ObservableList<Collection> collections = FXCollections.observableArrayList(listCollection);
		this.createListView(collections, 40, Orientation.HORIZONTAL);
	}

	private void createListView(ObservableList<Collection> listCollection, double sizeImage, Orientation orientationCell) {
		super.setItems(listCollection);
		super.setCellFactory(listView -> new CellMusicCollection<Collection>(listCollection, sizeImage, orientationCell));
	}
}
