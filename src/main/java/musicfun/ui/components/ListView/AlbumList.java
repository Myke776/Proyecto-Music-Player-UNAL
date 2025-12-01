package musicfun.ui.components.ListView;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import musicfun.model.AlbumModel;

public class AlbumList extends ListView<AlbumModel>{
	public AlbumList(ObservableList<AlbumModel> listAlbums) {
		super();
		this.createListView(listAlbums);		
	}

	public AlbumList(List<AlbumModel> listAlbums) {
		ObservableList<AlbumModel> songs = FXCollections.observableArrayList(listAlbums);
		this.createListView(songs);
	}

	private void createListView(ObservableList<AlbumModel> listAlbums) {
		super.setItems(listAlbums);
		super.setCellFactory(listView -> new AlbumCell(listAlbums));
	}
}
