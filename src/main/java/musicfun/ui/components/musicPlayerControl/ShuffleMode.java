package musicfun.ui.components.musicPlayerControl;

import musicfun.App;
import musicfun.ui.components.ButtonImage;

public class ShuffleMode extends ButtonImage {
	public ShuffleMode() {
		super("/icons/shuffle-arrows-2831.png");

		App.getMusicPlayer().getPlayerState().addListenerShuffle((obs, old, newMode) -> {
			if(newMode) {
				super.imageView.setStyle("-fx-opacity: 1;");
			}
			else {
				super.imageView.setStyle("-fx-opacity: 0.5;");
			}
		});

		super.setOnAction(e -> {
			App.getMusicPlayer().toggleShuffleMode();
		});
		
		super.imageView.setStyle("-fx-opacity: 0.5;");
	}
}
