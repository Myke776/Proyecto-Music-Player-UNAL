package musicfun.ui.components.listView;

import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import musicfun.App;
import musicfun.model.AlbumModel;
import musicfun.model.MusicCollection;
import musicfun.service.SongService;
import musicfun.ui.components.ContainerImageTitleText;

public class CellMusicCollection <Collection extends MusicCollection> extends ListCell<Collection> {
	private HBox contentMain = new HBox(10);
	private ContainerImageTitleText containerTitleAndArtist;

	public CellMusicCollection(List<Collection> parentList, double sizeImage, Orientation orientationCell, EventHandler<? super MouseEvent> event) {
		containerTitleAndArtist = new ContainerImageTitleText(sizeImage, orientationCell);
		this.contentMain.getChildren().addAll(this.containerTitleAndArtist);

		this.contentMain.getStyleClass().add("content-song");

		// HBox.setHgrow(containerTitleAndArtist, Priority.ALWAYS);
		// HBox.setHgrow(duration, Priority.ALWAYS);

		setOnMouseClicked(event);
	}

	public CellMusicCollection(List<Collection> parentList, double sizeImage, Orientation orientationCell) {
		this(parentList, sizeImage, orientationCell, null);
		// HBox.setHgrow(containerTitleAndArtist, Priority.ALWAYS);
		// HBox.setHgrow(duration, Priority.ALWAYS);

		setOnMouseClicked(event -> {
			if (!isEmpty() && getItem() != null) {
				App.getNavigation().navigateTo("musicCollection", parentList); // crear ventana de MusicCollection
			}
		});
	}

	@Override
	protected void updateItem(Collection collection, boolean empty) {
		super.updateItem(collection, empty);
		if (empty || collection == null) {
			super.setGraphic(null);
		} else {
			this.containerTitleAndArtist.setImage(SongService.getImage(collection.getCover()));

			this.containerTitleAndArtist.setTitle(collection.getName());

			if(collection instanceof AlbumModel) {
				AlbumModel album = (AlbumModel) collection;
				this.containerTitleAndArtist.setArtist("Artist: " + album.getArtist());
			}else {
				this.containerTitleAndArtist.setArtist(collection.getDescription());
			}

			super.setGraphic(this.contentMain);
		}
	}
}
