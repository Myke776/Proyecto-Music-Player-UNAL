package musicfun.ui.components;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import musicfun.model.SongModel;

public class SongList extends ListView<SongModel> {

	public SongList(List<SongModel> listSongs) {
		super();

		ObservableList<SongModel> songs = FXCollections.observableArrayList(listSongs);
		super.setItems(songs);
		super.setCellFactory(listView -> new SongCell());

		String cssSong = getClass().getResource("/styles/songList.css").toExternalForm();
		super.getStylesheets().add(cssSong);

		super.getStyleClass().add("song-list");
	}
}
