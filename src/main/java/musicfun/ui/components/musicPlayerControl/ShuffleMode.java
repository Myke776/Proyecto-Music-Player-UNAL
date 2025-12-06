package musicfun.ui.components.musicPlayerControl;

import musicfun.logic.MusicPlayerLogic;
import musicfun.model.PlayerStateModel;
import musicfun.ui.components.ButtonImage;

public class ShuffleMode extends ButtonImage {
	public ShuffleMode() {
		super("/icons/shuffle-arrows-2831.png");

		PlayerStateModel.addListenerShuffle((obs, old, newMode) -> {
			if(newMode) {
				super.imageView.setStyle("-fx-opacity: 1;");
			}
			else {
				super.imageView.setStyle("-fx-opacity: 0.5;");
			}
		});

		super.setOnAction(e -> {
			MusicPlayerLogic.toggleShuffleMode();
		});
		
		super.imageView.setStyle("-fx-opacity: 0.5;");
	}
}
