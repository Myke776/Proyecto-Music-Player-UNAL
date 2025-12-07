package musicfun.ui.component.musicPlayerControl;

import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import musicfun.model.PlayerStateModel;
import musicfun.logic.MusicPlayerLogic;
import musicfun.ui.component.ButtonImage;

public class ContainerButtons extends HBox {
	private ButtonImage playPauseView = new ButtonImage();
	private static final Image PLAY = new Image(
			ContainerButtons.class.getResourceAsStream("/icons/play-button-4208.png"));
	private static final Image PAUSE = new Image(
			ContainerButtons.class.getResourceAsStream("/icons/pause-black-circle-button-20755.png"));

	public ContainerButtons() {
		super();
		ButtonImage previusView = new ButtonImage("/icons/arrow-232.png");
		ButtonImage nextView = new ButtonImage("/icons/arrow-232.png");

		CycleRepeatMode cycleRepeatMode = new CycleRepeatMode();
		ShuffleMode shuffleMode = new ShuffleMode();
		
		previusView.setRotate(180);


		this.playPauseView.getImageView().setImage(PLAY);
		super.getChildren().addAll(cycleRepeatMode, previusView, playPauseView, nextView, shuffleMode);
		super.setSpacing(50);

		PlayerStateModel.addListenerPlaying((obs, old, playing) -> {
			this.toggle(playing);
		});

		playPauseView.setOnMouseClicked(event -> {
			if(PlayerStateModel.isPlaying()) {
				MusicPlayerLogic.pause();
			}else {
				MusicPlayerLogic.resume();
			}
		});

		nextView.setOnAction(__ -> {
			MusicPlayerLogic.playNext();
		});
		previusView.setOnAction(__ -> {
			MusicPlayerLogic.playPrevious();
		});
	}

	public void toggle(boolean val) {
		if (val) {
			this.playPauseView.getImageView().setImage(PAUSE);
		} else {
			this.playPauseView.getImageView().setImage(PLAY);
		}
	}
}
