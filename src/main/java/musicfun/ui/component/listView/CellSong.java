package musicfun.ui.component.listView;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.StackPane;
import musicfun.logic.MusicPlayerLogic;
import musicfun.model.SongModel;
import musicfun.service.SongService;
import musicfun.ui.component.ContainerImageTitleText;

public class CellSong extends ListCell<SongModel> {

	private StackPane containertMain = new StackPane();
	private ContainerImageTitleText containerTitleAndArtist;
	private Label duration = new Label();
	private MenuCellSong menu;
	private ParamsCell paramsCell;

	public CellSong(ParamsCell cellParams) {
		this.paramsCell = cellParams;

		containerTitleAndArtist = new ContainerImageTitleText(cellParams.getSizeImage(),
				cellParams.getOrientation());
		this.menu = new MenuCellSong(super.getItem(), paramsCell.getIdPlayList(),
					cellParams.getSizeImage().getPrefHeight());
		this.containertMain.getChildren().addAll(this.containerTitleAndArtist);

		if (cellParams.getOrientation() == Orientation.HORIZONTAL) {
			this.containertMain.getChildren().addAll(duration, menu);
			this.duration.getStyleClass().add("text-bold");
			StackPane.setAlignment(duration, Pos.CENTER);
			StackPane.setAlignment(menu, Pos.CENTER_RIGHT);
		}

		StackPane.setAlignment(containerTitleAndArtist, Pos.CENTER_LEFT);

		if (cellParams.getEvent() != null) {
			setOnMouseClicked(cellParams.getEvent());
		} else {
			setOnMouseClicked(event -> {
				if (!super.isEmpty() && super.getItem() != null) {
					MusicPlayerLogic.setQueue(super.getListView().getItems());
					MusicPlayerLogic.playSong(getItem());
				}
			});
		}

		cellParams.getSizeCell().applyTo(containertMain);
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
			this.menu.setSong(song);
			this.menu.setIdPlaylist(paramsCell.getIdPlayList());
			super.setGraphic(this.containertMain);
		}
	}
}