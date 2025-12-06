package musicfun.ui.views;


import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import musicfun.model.LibraryModel;
import musicfun.model.SongModel;
import musicfun.ui.component.SortListSongs;
import musicfun.ui.component.listView.ListSong;
import musicfun.ui.model.navigation.SceneInfo;

public class MyMusic extends SceneInfo<VBox> {

	public MyMusic() {
		super("MyMusic", "music-note-10259.png", "myMusic", true, false, new VBox());
	}

	@Override
	protected void initializeUI(VBox scene) {
		Label message = new Label("All music");
		Region spacer = new Region();
		HBox container = new HBox(message, spacer, new SortListSongs());

		ObservableList<SongModel> songs = LibraryModel.getSongs();

		ListSong songList = new ListSong(songs);
		scene.getChildren().addAll(container, songList);

		HBox.setHgrow(spacer, Priority.ALWAYS);
		VBox.setVgrow(songList, Priority.ALWAYS);

		container.setAlignment(Pos.CENTER);
		message.getStyleClass().add("text-bold");
		scene.setPadding(new Insets(10, 0, 0, 0));
	}
}
