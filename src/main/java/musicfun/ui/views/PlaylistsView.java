package musicfun.ui.views;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import musicfun.model.LibraryModel;
import musicfun.model.PlaylistModel;
import musicfun.ui.components.listView.ListMusicCollection;
import musicfun.ui.model.navigation.SceneInfo;

public class PlaylistsView extends SceneInfo<VBox> {

	public PlaylistsView() {
		super("Playlists", "list-6216.png", "playlists", true, false, new VBox());
	}

	@Override
	protected void initializeUI(VBox scene) {
		Label message = new Label("Your playlists:");
		scene.getChildren().addAll(message);

		ObservableList<PlaylistModel> playlists = LibraryModel.getPlaylists();

		ListMusicCollection<PlaylistModel> playlistsView = new ListMusicCollection<>(playlists);
		scene.getChildren().add(playlistsView);
		VBox.setVgrow(playlistsView, Priority.ALWAYS);
		message.getStyleClass().add("text-bold");
		scene.setPadding(new Insets(20));
	}
}