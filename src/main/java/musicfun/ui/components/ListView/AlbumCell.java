package musicfun.ui.components.ListView;

import java.util.List;

import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import musicfun.model.AlbumModel;
import musicfun.service.SongService;
import musicfun.ui.components.ContainerImageTitleArtist;

public class AlbumCell extends ListCell<AlbumModel> {
	private HBox contentMain = new HBox(10);
	private ContainerImageTitleArtist containerTitleAndArtist = new ContainerImageTitleArtist();
	private Text duration = new Text();

	public AlbumCell(List<AlbumModel> parentList) {
		this.contentMain.getChildren().addAll(this.containerTitleAndArtist, this.duration);

		this.contentMain.getStyleClass().add("content-song");
		this.duration.getStyleClass().add("text-bold");

		HBox.setHgrow(containerTitleAndArtist, Priority.ALWAYS);
		HBox.setHgrow(duration, Priority.ALWAYS);

		// setOnMouseClicked(event -> {
			// if (!isEmpty() && getItem() != null) {
			// 	App.getMusicPlayer().setQueue(parentList);
			// 	App.getMusicPlayer().playSong(getItem());
			// }
		// });
	}

	@Override
	protected void updateItem(AlbumModel album, boolean empty) {
		super.updateItem(album, empty);
		if (empty || album == null) {
			super.setGraphic(null);
		} else {
			this.containerTitleAndArtist.setArtist(album.getArtist());
			this.containerTitleAndArtist.setTitle(album.getName());
			this.containerTitleAndArtist.setImage(SongService.getImage(album.getCover()));

			// this.duration.setText(album.getTotalSongCount());
			super.setGraphic(this.contentMain);
		}
	}
}
