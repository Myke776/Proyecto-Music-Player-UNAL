package musicfun.ui.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ContainerImageTitleArtist extends HBox {
	private final ImageView coverView = new ImageView();
	private final Text titleText = new Text();
	private final Text artistText = new Text();
	
	public ContainerImageTitleArtist(Image image, double imageSize, String title, String artist) {
		super();
		VBox vbox = new VBox(this.titleText, this.artistText);
		super.getChildren().addAll(this.coverView,vbox);
		this.titleText.getStyleClass().add("text-bold");
		this.artistText.getStyleClass().add("text-opacity");

		setTitle(title);
		setArtist(artist);
		setImage(image);

		this.coverView.setFitHeight(imageSize);
		this.coverView.setFitWidth(imageSize);
		this.coverView.setPreserveRatio(true);
		super.getStyleClass().add("container-image-title-artist");
	}
	public ContainerImageTitleArtist(double imageSize){
		this(null, imageSize,"", "");
	}
	public ContainerImageTitleArtist() {
		this(40);
	}

	public void setTitle(String title) {
		this.titleText.setText(title);
	}
	public void setArtist(String artist) {
		this.artistText.setText(artist);
	}
	public void setImage(Image image) {
		coverView.setImage(image);
	}
}
