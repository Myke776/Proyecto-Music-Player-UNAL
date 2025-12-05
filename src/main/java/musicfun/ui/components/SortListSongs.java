package musicfun.ui.components;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import musicfun.logic.LibraryLogic;
import musicfun.logic.LibraryLogic.SortType;
import musicfun.model.SongModel;

public class SortListSongs extends ChoiceBox<String> {
	public SortListSongs(List<SongModel> songs) {
		super();
		ObservableList<String> list = FXCollections.observableArrayList("Name", "Artist", "Album", "Year", "Creation date time");
		super.setItems(list);

		super.getSelectionModel().selectedIndexProperty().addListener((obs, wasSelected, isNowSelected) ->{
			switch (isNowSelected.intValue()) {
				case 0:
					LibraryLogic.sortSongs(songs, SortType.NAME, true);
					break;
				case 1:
					LibraryLogic.sortSongs(songs, SortType.ARTIST, true);
					break;
				case 2:
					LibraryLogic.sortSongs(songs, SortType.ALBUM, true);
					break;
				case 3:
					LibraryLogic.sortSongs(songs, SortType.YEAR, true);
					break;
				case 4:
					LibraryLogic.sortSongs(songs, SortType.CREATION_DATE_TIME, true);
					break;
			}
        });
	}

	public SortListSongs() {
		super(FXCollections.observableArrayList("Name", "Artist", "Album", "Year", "Creation date time"));

		super.getSelectionModel().selectedIndexProperty().addListener((obs, wasSelected, isNowSelected) ->{
			switch (isNowSelected.intValue()) {
				case 0:
					LibraryLogic.sortSongs(SortType.NAME, true);
					break;
				case 1:
					LibraryLogic.sortSongs(SortType.ARTIST, true);
					break;
				case 2:
					LibraryLogic.sortSongs(SortType.ALBUM, true);
					break;
				case 3:
					LibraryLogic.sortSongs(SortType.YEAR, true);
					break;
				case 4:
					LibraryLogic.sortSongs(SortType.CREATION_DATE_TIME, true);
					break;
			}
        });
	}
}
