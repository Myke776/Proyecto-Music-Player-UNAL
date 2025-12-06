package musicfun.ui.component.listView;

import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import musicfun.App;
import musicfun.model.AlbumModel;
import musicfun.model.MusicCollection;
import musicfun.service.SongService;
import musicfun.ui.component.ContainerImageTitleText;

public class CellMusicCollection <Collection extends MusicCollection> extends ListCell<Collection> {
	private HBox contentMain = new HBox(10);
	private ContainerImageTitleText containerTitleAndArtist;

	public CellMusicCollection(CellParams<Collection> cellparams) {
		// this(parentList, sizeImage, orientationCell, null);
		containerTitleAndArtist = new ContainerImageTitleText(cellparams.getSizeImage(), cellparams.getOrientation());
		this.contentMain.getChildren().addAll(this.containerTitleAndArtist);
		if(cellparams.getEvent() != null){
			setOnMouseClicked(cellparams.getEvent());
		}else{
			setOnMouseClicked(event -> {
				if (!isEmpty() && getItem() != null) {
					App.getNavigation().navigateTo("musicCollection", super.getItem()); // crear ventana de MusicCollection
				}
			});
		}
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
