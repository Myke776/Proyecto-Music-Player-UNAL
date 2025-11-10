package musicfun.ui.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import musicfun.App;

public class ContainerButtons extends HBox {
	private final ImageView playPauseView = new ImageView();
	private static final Image PLAY = new Image(
			ContainerButtons.class.getResourceAsStream("/icons/play-button-4208.png"));
	private static final Image PAUSE = new Image(
			ContainerButtons.class.getResourceAsStream("/icons/pause-black-circle-button-20755.png"));

	public ContainerButtons() {
		super();
		ImageView previusView = new ImageView(
				new Image(ContainerButtons.class.getResourceAsStream("/icons/arrow-232.png")));
		ImageView nextView = new ImageView(
				new Image(ContainerButtons.class.getResourceAsStream("/icons/arrow-232.png")));

		double size = 30;
		playPauseView.setFitHeight(size);
		playPauseView.setFitWidth(size);
		previusView.setFitHeight(size);
		previusView.setFitWidth(size);
		nextView.setFitHeight(size);
		nextView.setFitWidth(size);
		previusView.setScaleX(-1);

		this.playPauseView.setImage(PLAY);
		super.getChildren().addAll(previusView, playPauseView, nextView);
		super.setSpacing(50);

		App.getMusicPlayer().getPlayerState().addListenerPlaying((obs, old, playing) -> {
			this.toggle(playing);
		});

		playPauseView.setOnMouseClicked(event -> {
			if(App.getMusicPlayer().getPlayerState().isPlaying()) {
				App.getMusicPlayer().pause();
			}else {
				App.getMusicPlayer().resume();
			}
		});

		nextView.setOnMouseClicked(__ -> {
			App.getMusicPlayer().playNext();
		});
		previusView.setOnMouseClicked(__ -> {
			App.getMusicPlayer().playPrevious();
		});
	}

	public void toggle(boolean val) {
		if (val) {
			this.playPauseView.setImage(PAUSE);
		} else {
			this.playPauseView.setImage(PLAY);
		}
	}
}
