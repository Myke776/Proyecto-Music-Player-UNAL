package musicfun.ui.components;

import javafx.scene.control.Button;
import musicfun.model.SongModel;

public class SongButton extends Button {
	public SongButton(SongModel song) {
		super(song.getTitle());
		String cssSong = getClass().getResource("/styles/scene.css").toExternalForm();
		super.getStylesheets().add(cssSong);
		super.getStyleClass().add("song-btn");
	}
}
