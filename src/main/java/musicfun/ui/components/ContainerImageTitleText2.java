package musicfun.ui.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ContainerImageTitleText2 extends HBox {
	private final ImageView COVER_VIEW = new ImageView();
	private final Text TITLE = new Text();
	private final Text TEXT = new Text();

	public ContainerImageTitleText2(Image image, double imageSize, String title, String text) {
		super();
		VBox vbox = new VBox(this.TITLE, this.TEXT);
		super.getChildren().addAll(this.COVER_VIEW, vbox);
		this.TITLE.getStyleClass().add("text-bold");
		this.TEXT.getStyleClass().add("text-opacity");

		setTitle(title);
		setImage(image);
		setArtist(text);

		this.COVER_VIEW.setFitHeight(imageSize);
		this.COVER_VIEW.setFitWidth(imageSize);
		this.COVER_VIEW.setPreserveRatio(true);
	}

	public ContainerImageTitleText2(double imageSize) {
		this(null, imageSize, "", "");
	}

	public ContainerImageTitleText2() {
		this(40);
	}

	public void setTitle(String title) {
		this.TITLE.setText(title);
	}

	public void setArtist(String text) {
		this.TEXT.setText(text);
	}

	public void setImage(Image image) {
		COVER_VIEW.setImage(image);
	}
}
