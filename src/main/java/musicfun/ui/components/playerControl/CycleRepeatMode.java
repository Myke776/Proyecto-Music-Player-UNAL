package musicfun.ui.components.playerControl;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import musicfun.App;

public class CycleRepeatMode extends Button {
	private ImageView imageView;
	private Text text;

	private static final Image SHUFFLE = new Image(
			ContainerButtons.class.getResourceAsStream("/icons/shuffle-arrows-2831.png"));
	private static final Image REPEAT = new Image(
			ContainerButtons.class.getResourceAsStream("/icons/convert-3210.png"));

	public CycleRepeatMode() {
		super();
		this.imageView = new ImageView(REPEAT);
		this.text = new Text();

		super.setGraphic(new StackPane(text, imageView));
		App.getMusicPlayer().getPlayerState().addListenerRepeatMode((obs, old, newMode) -> {
			switch (newMode) {
				case NONE:
					imageView.setImage(REPEAT);
					imageView.setStyle("-fx-opacity: 0.5;");
					break;
				case ALL:
					imageView.setStyle("-fx-opacity: 1;");
					break;
				case ONE:
					text.setText("1");
					break;
				default:
					imageView.setImage(SHUFFLE);
					text.setText("");
					break;
			}
			System.out.println(newMode);
		});

		super.setOnAction(e -> {
			App.getMusicPlayer().cycleRepeatMode();
		});

		double size = 30;
		this.imageView.setFitHeight(size);
		this.imageView.setFitWidth(size);
		text.getStyleClass().add("text-bold");
		super.setStyle("-fx-background-color: transparent");
	}
}