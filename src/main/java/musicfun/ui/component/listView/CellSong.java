package musicfun.ui.component.listView;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import musicfun.logic.MusicPlayerLogic;
import musicfun.model.SongModel;
import musicfun.service.SongService;
import musicfun.ui.component.ContainerImageTitleText;

public class CellSong extends ListCell<SongModel> {

	private StackPane contentMain = new StackPane();
	private ContainerImageTitleText containerTitleAndArtist;
	private Label duration = new Label();
	private Button addSongToPlaylist = new Button();

	public CellSong(CellParams<SongModel> cellParams) {
		containerTitleAndArtist = new ContainerImageTitleText(cellParams.getSizeImage(),
				cellParams.getOrientation());

		this.contentMain.getChildren().addAll(this.containerTitleAndArtist);

		if (cellParams.getOrientation() == Orientation.HORIZONTAL) {
			this.contentMain.getChildren().addAll(duration, addSongToPlaylist);

			ImageView imageView = new ImageView(new Image(
					getClass().getResourceAsStream("/icons/music-player-add-playlist-queue-round-outline-icon.png")));
			addSongToPlaylist.setGraphic(imageView);
			imageView.setFitWidth(cellParams.getSizeImage().getPrefHeight() * 0.7);
			imageView.setFitHeight(cellParams.getSizeImage().getPrefHeight() * 0.7);

			this.duration.getStyleClass().add("text-bold");

			StackPane.setAlignment(containerTitleAndArtist, Pos.CENTER_LEFT);
			StackPane.setAlignment(duration, Pos.CENTER);
			StackPane.setAlignment(addSongToPlaylist, Pos.CENTER_RIGHT);
		}

		if (cellParams.getEvent() != null) {
			setOnMouseClicked(cellParams.getEvent());
		} else {
			setOnMouseClicked(event -> {
				if (!super.isEmpty() && super.getItem() != null) {
					MusicPlayerLogic.setQueue(cellParams.getList());
					MusicPlayerLogic.playSong(getItem());
				}
			});
		}

		addSongToPlaylist.setOnAction(__ -> {
			new AddSongToPlaylist(super.getItem());
		});

		cellParams.getSizeCell().applyTo(contentMain);
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