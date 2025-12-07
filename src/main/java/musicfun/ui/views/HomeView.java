package musicfun.ui.views;

import java.util.List;

import javafx.scene.layout.VBox;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import musicfun.logic.LibraryLogic;
import musicfun.model.AlbumModel;
import musicfun.model.ArtistModel;
import musicfun.model.LibraryModel;
import musicfun.model.SongModel;
import musicfun.ui.component.listView.ParamsCell;
import musicfun.ui.component.listView.ListMusicCollection;
import musicfun.ui.component.listView.ListSong;
import musicfun.ui.layout.SizeConstraints;
import musicfun.ui.model.navigation.SceneInfo;

public class HomeView extends SceneInfo<VBox> {

	public HomeView() {
		super("Home", "home-3119.png", "home", true, false, new VBox());
	}

	@Override
	protected void initializeUI(VBox scene) {
		HBox horizontalContainer = new HBox();

		List<SongModel> recentlyAddedSongs = LibraryLogic.getRecentlyAddedSongs();

		ObservableList<SongModel> recentlyPlayedSongs = LibraryModel.getRecentlyPlayedSongs();
		ObservableList<AlbumModel> recentlyPlayedAlbums = LibraryModel.getRecentlyPlayedAlbums();
		ObservableList<ArtistModel> recentlyPlayedArtist = LibraryModel.getRecentlyPlayedArtists();
		Label labelAddedSongs = new Label("Recently added songs.");

		ParamsCell cellparams = new ParamsCell(Orientation.VERTICAL, new SizeConstraints(), new SizeConstraints(100));
		ListSong recentlyAddedSongsList = new ListSong(recentlyAddedSongs, cellparams);
		recentlyAddedSongsList.setOrientation(Orientation.HORIZONTAL);
		recentlyAddedSongsList.setMinHeight(220);
		recentlyAddedSongsList.setMaxHeight(220);
		recentlyAddedSongsList.setPrefHeight(220);

		Label labelRecentlyPlayedSongs = new Label("Recently played songs.");
		ListSong recentlyPlayedSongsList = new ListSong(recentlyPlayedSongs);
		VBox container1 = new VBox(labelRecentlyPlayedSongs, recentlyPlayedSongsList);
		Label labelRecentlyPlayedAlbums = new Label("Recently played albums.");
		ListMusicCollection<AlbumModel> recentlyAlbumList = new ListMusicCollection<>(recentlyPlayedAlbums);
		VBox container2 = new VBox(labelRecentlyPlayedAlbums, recentlyAlbumList);
		Label labelRecentlyPlayedArtists = new Label("Recently played artists.");
		ListMusicCollection<ArtistModel> recentlyArtistsList = new ListMusicCollection<>(recentlyPlayedArtist);
		VBox container3 = new VBox(labelRecentlyPlayedArtists, recentlyArtistsList);
		horizontalContainer.getChildren().addAll(container1, container2, container3);

		VBox.setVgrow(horizontalContainer, Priority.ALWAYS);

		HBox.setHgrow(container1, Priority.ALWAYS);
		HBox.setHgrow(container2, Priority.ALWAYS);
		HBox.setHgrow(container3, Priority.ALWAYS);
		scene.getChildren().addAll(labelAddedSongs, recentlyAddedSongsList, horizontalContainer);
		scene.setSpacing(10);
		scene.setStyle("-fx-padding: 20 0 0 0;");
	}
}