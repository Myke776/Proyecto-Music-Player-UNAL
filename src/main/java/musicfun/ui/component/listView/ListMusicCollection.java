package musicfun.ui.component.listView;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import musicfun.model.MusicCollectionModel;

public class ListMusicCollection <Collection extends MusicCollectionModel> extends ListView<Collection>{


	public ListMusicCollection(ObservableList<Collection> listCollection, ParamsCell cellParams) {
		super();
		this.createListView(listCollection, cellParams);
	}

	public ListMusicCollection(ObservableList<Collection> listCollection) {
		this(listCollection, new ParamsCell());
	}

	public ListMusicCollection(List<Collection> listCollection, ParamsCell cellParams) {
		ObservableList<Collection> collections = FXCollections.observableArrayList(listCollection);
		this.createListView(collections, cellParams);
	}

	public ListMusicCollection(List<Collection> listCollection) {
		ObservableList<Collection> collections = FXCollections.observableArrayList(listCollection);
		this.createListView(collections, new ParamsCell());
	}

	private void createListView(ObservableList<Collection> listCollection, ParamsCell cellParams) {
		super.setItems(listCollection);
		super.setCellFactory(listView -> new CellMusicCollection<Collection>(cellParams));
	}
}
