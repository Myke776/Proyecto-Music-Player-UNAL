package musicfun.ui.views;

import java.util.List;

import javafx.scene.layout.VBox;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import musicfun.logic.LibraryLogic;
import musicfun.model.AlbumModel;
import musicfun.model.ArtistModel;
import musicfun.model.LibraryModel;
import musicfun.model.SongModel;
import musicfun.ui.components.ListView.AlbumList;
import musicfun.ui.components.ListView.ListComponent;
import musicfun.ui.components.ListView.SongList;
import musicfun.ui.navigation.SceneInfo;

public class HomeView extends SceneInfo<VBox> {

	public HomeView() {
		super("Home", "home-3119.png", "home", true, false, new VBox());
		initializeUI();
	}

	private void initializeUI() {
		VBox scene = super.getSceneLoader();
		HBox horizontalContainer = new HBox();

		new Thread(() -> {
			List<SongModel> recentlyAddedSongs = LibraryLogic.getRecentlyAddedSongs();

			ObservableList<SongModel> recentlyPlayedSongs = LibraryModel.getRecentlyPlayedSongs();
			ObservableList<AlbumModel> recentlyPlayedAlbums = LibraryModel.getRecentlyPlayedAlbums();
			// ObservableList<ArtistModel> recentlyPlayedArtist = LibraryModel.getRecentlyPlayedArtists();

			javafx.application.Platform.runLater(() -> {
				SongList recentlyAddedSongsList = new SongList(recentlyAddedSongs);
				scene.getChildren().addAll(recentlyAddedSongsList, horizontalContainer);

				// SongList recentlyPlayedSongsList = new SongList(recentlyPlayedSongs);
				ListComponent<SongModel> recentlyPlayedSongsList = new ListComponent<SongModel>(recentlyPlayedSongs, __ -> {});
				AlbumList recentlyAlbumList = new AlbumList(recentlyPlayedAlbums);
				horizontalContainer.getChildren().addAll(recentlyPlayedSongsList, recentlyAlbumList);


				VBox.setVgrow(recentlyPlayedSongsList, Priority.ALWAYS);
				VBox.setVgrow(horizontalContainer, Priority.ALWAYS);
			});
		}).start();
	}
}