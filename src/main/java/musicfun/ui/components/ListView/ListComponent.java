package musicfun.ui.components.ListView;


import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import musicfun.model.SongModel;

public class ListComponent <Element extends SongModel> extends ListView<Element> {
	// public ListComponent(ObservableList<Element> listSongs, Function<List<Element>, ListCell<Element>> cell) {
	// 	super();
	// 	this.createListView(listSongs, cell);
	// }

	public ListComponent(ObservableList<Element> listSongs, Consumer<List<Element>> funtion) {
		super();
		this.createListView(listSongs, list -> new CellComponent<Element>(list, funtion));
	}

	public ListComponent(List<Element> listSongs, Function<List<Element>, ListCell<Element>> cell) {
		super();
		ObservableList<Element> songs = FXCollections.observableArrayList(listSongs);
		this.createListView(songs, cell);
	}

	private void createListView(ObservableList<Element> listSongs, Function<List<Element>, ListCell<Element>> Cell) {
		super.setItems(listSongs);
		super.setCellFactory(listView -> Cell.apply(listSongs));
	}
}
