package musicfun.ui.component.listView;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import musicfun.model.MusicCollection;

public class ListMusicCollection <Collection extends MusicCollection> extends ListView<Collection>{


	public ListMusicCollection(ObservableList<Collection> listCollection, CellParams<Collection> cellParams) {
		super();
		this.createListView(listCollection, cellParams);
	}

	public ListMusicCollection(ObservableList<Collection> listCollection) {
		this(listCollection, new CellParams<>());
	}

	public ListMusicCollection(List<Collection> listCollection, CellParams<Collection> cellParams) {
		ObservableList<Collection> collections = FXCollections.observableArrayList(listCollection);
		this.createListView(collections, cellParams);
	}

	public ListMusicCollection(List<Collection> listCollection) {
		ObservableList<Collection> collections = FXCollections.observableArrayList(listCollection);
		this.createListView(collections, new CellParams<>());
	}

	private void createListView(ObservableList<Collection> listCollection, CellParams<Collection> cellParams) {
		super.setItems(listCollection);
		cellParams.setList(listCollection);
		super.setCellFactory(listView -> new CellMusicCollection<Collection>(cellParams));
	}
}
