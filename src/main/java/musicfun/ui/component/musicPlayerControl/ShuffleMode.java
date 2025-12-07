package musicfun.ui.component.musicPlayerControl;

import musicfun.model.PlayerStateModel;
import musicfun.logic.MusicPlayerLogic;
import musicfun.ui.component.ButtonImage;

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
