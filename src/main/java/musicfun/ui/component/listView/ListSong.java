package musicfun.ui.component.listView;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import musicfun.model.SongModel;

public class ListSong extends ListView<SongModel> {

	public ListSong(ObservableList<SongModel> listSongs, ParamsCell cellparams) {
		super();
		this.createListView(listSongs, cellparams);
	}

	public ListSong(ObservableList<SongModel> listSongs) {
		this(listSongs, new ParamsCell());
	}

	public ListSong(List<SongModel> listSongs, ParamsCell cellparams) {
		ObservableList<SongModel> songs = FXCollections.observableArrayList(listSongs);
		this.createListView(songs, cellparams);
	}

	public ListSong(List<SongModel> listSongs) {
		this(listSongs, new ParamsCell());
	}

	protected void createListView(ObservableList<SongModel> listSongs, ParamsCell cellparams) {
		super.setItems(listSongs);
		super.setCellFactory(listView -> new CellSong(cellparams));
	}
}
