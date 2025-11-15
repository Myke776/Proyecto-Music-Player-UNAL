package musicfun.ui.views;

import javafx.collections.ObservableList;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import musicfun.App;
import musicfun.model.SongModel;
import musicfun.ui.components.ListView.SongList;
import musicfun.ui.navigation.SceneInfo;

public class PlaylistsView extends SceneInfo<VBox> {

	public PlaylistsView() {
		super("Playlists", "list-6216.png", "playlists", true, false, new VBox());
		initializeUI();
	}

	private void initializeUI() {
		VBox scene = super.getSceneLoader();

		Text message = new Text("Playing playlist now..");
		scene.getChildren().addAll(message);


		new Thread(() -> {
			ObservableList<SongModel> songs = App.getMusicPlayer().getCurrentQueue();

			javafx.application.Platform.runLater(() -> {
				SongList songList = new SongList(songs);
				scene.getChildren().add(songList);
				VBox.setVgrow(songList, Priority.ALWAYS);

				// App.getMusicPlayer().addListenerCurrentQueue((ev) -> {
				// 	ObservableList<SongModel> songsL = App.getMusicPlayer().getCurrentQueue();
				// 	songList.getItems().clear();
				// 	songList.setItems(songsL);
				// 	VBox.setVgrow(songList, Priority.ALWAYS);
				// });

			});
		}).start();
	}
}