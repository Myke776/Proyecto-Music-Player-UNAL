package musicfun.ui.components;

import java.util.List;

import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import musicfun.App;
import musicfun.model.SongModel;
import musicfun.service.SongService;

public class SongCell extends ListCell<SongModel> {

	private final HBox contentMain = new HBox(10);
	private final ContainerImageTitleArtist containerTitleAndArtist = new ContainerImageTitleArtist();
	private final Text duration = new Text();

	public SongCell(List<SongModel> parentList) {
		this.contentMain.getChildren().addAll(this.containerTitleAndArtist, this.duration);

		this.contentMain.getStyleClass().add("content-song");
		this.duration.getStyleClass().add("text-bold");

		HBox.setHgrow(containerTitleAndArtist, Priority.ALWAYS);
		HBox.setHgrow(duration, Priority.ALWAYS);

		setOnMouseClicked(event -> {
			if (!isEmpty() && getItem() != null) {
				App.getMusicPlayer().setQueue(parentList);
				App.getMusicPlayer().playSong(getItem());
			}
		});
	}

	@Override
	protected void updateItem(SongModel song, boolean empty) {
		super.updateItem(song, empty);
		if (empty || song == null) {
			super.setGraphic(null);
		} else {
			this.containerTitleAndArtist.setArtist(song.getArtist());
			this.containerTitleAndArtist.setTitle(song.getTitle());
			this.containerTitleAndArtist.setImage(SongService.getImage(song.getCover()));

			this.duration.setText(song.getFormattedDuration());
			super.setGraphic(this.contentMain);
		}
	}
}
