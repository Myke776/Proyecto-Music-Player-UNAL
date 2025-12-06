package musicfun.ui.component;

import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import musicfun.ui.layout.SizeConstraints;

public class ContainerImageTitleText extends GridPane {
	private final ImageView COVER_VIEW = new ImageView();
	private final Label TITLE = new Label();
	private final Label TEXT = new Label();

	public ContainerImageTitleText(Image image, SizeConstraints imageSize, String title, String text,
			Orientation orientation) {
		super();

		this.setTitle(title);
		this.setImage(image);
		this.setArtist(text);

		this.COVER_VIEW.setFitHeight(imageSize.getPrefHeight());
		this.COVER_VIEW.setFitWidth(imageSize.getPrefWidth());
		this.COVER_VIEW.setPreserveRatio(true);
		this.COVER_VIEW.setSmooth(true);

		double radius = 20;
		Rectangle clip = new Rectangle(imageSize.getPrefWidth(), imageSize.getPrefHeight());
		clip.setArcWidth(radius);
		clip.setArcHeight(radius);
		this.COVER_VIEW.setClip(clip);

		if (orientation == Orientation.HORIZONTAL) {
			setHorizontal();
		} else {
			setVertical();
			TITLE.setMaxWidth(imageSize.getPrefWidth());
			TEXT.setMaxWidth(imageSize.getPrefWidth());
		}
		super.getChildren().addAll(COVER_VIEW, TITLE, TEXT);

		this.TITLE.getStyleClass().add("text-bold");
		this.TEXT.getStyleClass().add("text-opacity");
		super.getStyleClass().add("container-image-title-artist");
	}

	public ContainerImageTitleText(SizeConstraints imageSize, Orientation orientation) {
		this(null, imageSize, "", "", orientation);
	}

	public ContainerImageTitleText(SizeConstraints imageSize) {
		this(null, imageSize, "", "", Orientation.HORIZONTAL);
	}

	public ContainerImageTitleText(Orientation orientation) {
		this(null, new SizeConstraints(40), "", "", orientation);
	}

	public ContainerImageTitleText() {
		this(new SizeConstraints(40), Orientation.HORIZONTAL);
	}

	private void setHorizontal() {
		super.getRowConstraints().clear();
		super.getColumnConstraints().clear();
		super.setConstraints(COVER_VIEW, 0, 0, 1, 2);
		super.setConstraints(TITLE, 1, 0);
		super.setConstraints(TEXT, 1, 1);
		super.setStyle("-fx-hgap: 10px");

	}

	private void setVertical() {
		super.getRowConstraints().clear();
		super.getColumnConstraints().clear();
		super.setConstraints(COVER_VIEW, 0, 0);
		super.setConstraints(TITLE, 0, 1);
		super.setConstraints(TEXT, 0, 2);
		super.setStyle("-fx-vgap: 10px");
	}

	public void setOrientation(Orientation orientation) {
		// ver si funciona, posiblemente se necesite borrar los elementos en la clase de
		// los hijos y volver agregarlos.
		if (orientation == Orientation.HORIZONTAL) {
			setHorizontal();
		} else {
			setVertical();
		}
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
