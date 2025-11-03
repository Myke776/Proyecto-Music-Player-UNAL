package musicfun.ui.components;

import java.io.ByteArrayInputStream;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import musicfun.model.SongModel;

public class SongCell extends ListCell<SongModel> {

	private final HBox contentMain = new HBox(10);
	private final ImageView coverView = new ImageView();
	private final Text title = new Text();
	private final Text artist = new Text();
	private final Text duration = new Text();

	private static final Image DEFAULT_COVER = new Image(
			SongCell.class.getResourceAsStream("/icons/no-image-14598.png"));

	public SongCell() {
		this.coverView.setFitHeight(40);
		this.coverView.setFitWidth(40);
		this.coverView.setPreserveRatio(true);

		VBox contentText = new VBox(this.title, this.artist);
		this.contentMain.getChildren().addAll(this.coverView, contentText, this.duration);

		this.contentMain.getStyleClass().add("content-song");
		this.title.getStyleClass().add("title");
		this.artist.getStyleClass().add("artist");
		this.duration.getStyleClass().add("duration");

		HBox.setHgrow(contentText, Priority.ALWAYS);
		HBox.setHgrow(duration, Priority.ALWAYS);

		setOnMouseClicked(event -> {
			if (!isEmpty() && getItem() != null) {
				if (event.getClickCount() == 1) {
					System.out.println("Click: " + getItem().getTitle());
				}
			}
		});
	}

	@Override
	protected void updateItem(SongModel song, boolean empty) {
		super.updateItem(song, empty);
		if (empty || song == null) {
			super.setGraphic(null);
		} else {
			if (song.getCover() != null) {
				try {
					Image cover = new Image(new ByteArrayInputStream(song.getCover()));
					this.coverView.setImage(cover);
				} catch (Exception e) {
					this.coverView.setImage(DEFAULT_COVER);
					System.err.println("Error cargando la carátula de " + song.getTitle());
				}
			} else {
				this.coverView.setImage(DEFAULT_COVER);
			}

			this.title.setText(song.getTitle());
			this.artist.setText(song.getArtist());
			this.duration.setText(song.getFormattedDuration());
			super.setGraphic(this.contentMain);
		}
	}
}
