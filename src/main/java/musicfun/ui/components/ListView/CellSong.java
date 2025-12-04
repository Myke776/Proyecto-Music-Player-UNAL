package musicfun.ui.components.ListView;

import java.util.List;

import javafx.geometry.Orientation;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import musicfun.App;
import musicfun.model.SongModel;
import musicfun.service.SongService;
import musicfun.ui.components.ContainerImageTitleText;

public class CellSong extends ListCell<SongModel> {

	private HBox contentMain = new HBox(10);
	private ContainerImageTitleText containerTitleAndArtist;
	private Text duration = new Text();

	public CellSong(List<SongModel> parentList, double sizeImage, Orientation orientationCell) {
		containerTitleAndArtist = new ContainerImageTitleText(sizeImage, orientationCell);
		this.contentMain.getChildren().addAll(this.containerTitleAndArtist, this.duration);
		
		if(orientationCell == Orientation.HORIZONTAL){
			HBox.setHgrow(containerTitleAndArtist, Priority.ALWAYS);
			HBox.setHgrow(duration, Priority.ALWAYS);
		}else {
		}
		this.duration.getStyleClass().add("text-bold");

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
