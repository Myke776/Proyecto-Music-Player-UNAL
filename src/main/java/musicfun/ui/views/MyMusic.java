package musicfun.ui.views;

import java.util.List;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import musicfun.model.LibraryModel;
import musicfun.model.SongModel;
import musicfun.ui.components.ListView.ListSong;
import musicfun.ui.navigation.SceneInfo;

public class MyMusic extends SceneInfo<HBox> {

	public MyMusic() {
		super("MyMusic", "music-note-10259.png", "myMusic", true, false, new HBox());
		initializeUI();
	}

	private void initializeUI() {
		HBox scene = super.getSceneLoader();
		new Thread(() -> {
			List<SongModel> songs = LibraryModel.getSongs();

			javafx.application.Platform.runLater(() -> {
				ListSong songList = new ListSong(songs);
				scene.getChildren().add(songList);
				HBox.setHgrow(songList, Priority.ALWAYS);
			});
		}).start();
	}
}
