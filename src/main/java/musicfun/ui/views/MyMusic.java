package musicfun.ui.views;

import java.util.List;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import musicfun.model.LibraryModel;
import musicfun.model.SongModel;
import musicfun.ui.components.listView.ListSong;
import musicfun.ui.model.navigation.SceneInfo;

public class MyMusic extends SceneInfo<HBox> {

	public MyMusic() {
		super("MyMusic", "music-note-10259.png", "myMusic", true, false, new HBox());
	}

	@Override
	protected void initializeUI(HBox scene) {

		List<SongModel> songs = LibraryModel.getSongs();

		javafx.application.Platform.runLater(() -> {
			ListSong songList = new ListSong(songs);
			scene.getChildren().add(songList);
			HBox.setHgrow(songList, Priority.ALWAYS);
		});
	}
}
