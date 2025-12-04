package musicfun.ui.components;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButtonImage extends Button {
	protected ImageView imageView;
	protected double size = 30;

	public ButtonImage(String ruteImage) {
		super();
		Image image = new Image(ButtonImage.class.getResourceAsStream(ruteImage));//cambiar para que se pueda usar elementos fuera del entorno de trabajo.
		initButton(image);
	}

	public ButtonImage() {
		super();
		initButton(null);
	}

	private void initButton(Image image) {
		this.imageView = new ImageView(image);

		super.setGraphic(imageView);

		this.imageView.setFitHeight(size);
		this.imageView.setFitWidth(size);
		super.setStyle("-fx-background-color: transparent");
		super.setWidth(size);
		super.setHeight(size);
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}
}
