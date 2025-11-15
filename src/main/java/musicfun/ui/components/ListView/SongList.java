package musicfun.ui.components.ListView;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import musicfun.model.SongModel;

public class SongList extends ListView<SongModel> {

	public SongList(ObservableList<SongModel> listSongs) {
		super();
		// ObservableList<SongModel> songs = FXCollections.observableArrayList(listSongs);
		this.createListView(listSongs);		
	}

	public SongList(List<SongModel> listSongs) {
		ObservableList<SongModel> songs = FXCollections.observableArrayList(listSongs);
		this.createListView(songs);
	}

	private void createListView(ObservableList<SongModel> listSongs) {
		super.setItems(listSongs);
		super.setCellFactory(listView -> new SongCell(listSongs));

		String cssSong = getClass().getResource("/styles/songList.css").toExternalForm();
		super.getStylesheets().add(cssSong);

		super.getStyleClass().add("song-list");
	}
}
