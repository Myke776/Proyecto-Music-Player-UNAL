package musicfun.ui.views;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import musicfun.model.LibraryModel;
import musicfun.model.PlaylistModel;
import musicfun.ui.components.ListView.ListMusicCollection;
import musicfun.ui.navigation.SceneInfo;

public class PlaylistsView extends SceneInfo<VBox> {

	public PlaylistsView() {
		super("Playlists", "list-6216.png", "playlists", true, false, new VBox());
		initializeUI();
	}

	private void initializeUI() {
		VBox scene = super.getSceneLoader();
		
		new Thread(() -> {
			Label message = new Label("Playlists");
			scene.getChildren().addAll(message);

			ObservableList<PlaylistModel> playlists = LibraryModel.getPlaylists();

			javafx.application.Platform.runLater(() -> {
				ListMusicCollection<PlaylistModel> playlistsView = new ListMusicCollection<>(playlists);
				scene.getChildren().add(playlistsView);
				VBox.setVgrow(playlistsView, Priority.ALWAYS);

			});
		}).start();
	}
}