package musicfun.ui.views;

import java.util.List;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import musicfun.App;
import musicfun.model.SongModel;
import musicfun.ui.components.ListView.SongList;
import musicfun.ui.navigation.SceneInfo;

public class MyMusic extends SceneInfo<HBox> {

	public MyMusic() {
		super("MyMusic", "music-note-10259.png", "myMusic", true, false, new HBox());
		initializeUI();
	}

	private void initializeUI() {
		HBox scene = super.getSceneLoader();
		new Thread(() -> {
			List<SongModel> songs = App.getLibraryManager().getSongs();

			javafx.application.Platform.runLater(() -> {
				SongList songList = new SongList(songs);
				scene.getChildren().add(songList);
				HBox.setHgrow(songList, Priority.ALWAYS);
			});
		}).start();
	}
}
