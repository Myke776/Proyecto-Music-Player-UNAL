package musicfun.ui.views;

import java.util.List;

import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import musicfun.App;
import musicfun.model.SongModel;
import musicfun.ui.components.ListView.SongList;
import musicfun.ui.navigation.SceneInfo;

public class HomeView extends SceneInfo<VBox> {

	public HomeView() {
		super("Home", "home-3119.png", "home", true, false, new VBox());
		initializeUI();
	}

	private void initializeUI() {
		VBox scene = super.getSceneLoader();
		new Thread(() -> {
			List<SongModel> recentlyAddedSongs = App.getLibraryManager().getRecentlyAddedSongs();
			// System.out.println(App.getLibraryManager().getAlbums());
			javafx.application.Platform.runLater(() -> {
				SongList songList = new SongList(recentlyAddedSongs);
				scene.getChildren().add(songList);
				VBox.setVgrow(songList, Priority.ALWAYS);
			});
		}).start();
	}
}