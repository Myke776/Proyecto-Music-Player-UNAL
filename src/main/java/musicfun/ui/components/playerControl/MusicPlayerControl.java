package musicfun.ui.components.playerControl;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
// import javafx.scene.control.Slider;
import musicfun.App;
import musicfun.service.SongService;
import musicfun.ui.components.ContainerImageTitleArtist;
import musicfun.ui.components.GridLayoutManager;

public class MusicPlayerControl extends GridLayoutManager {
	private final ContainerImageTitleArtist containerTitleAndArtist = new ContainerImageTitleArtist(60);
	private final ContainerButtons containerButtons = new ContainerButtons();
	private final SliderTime slider = new SliderTime();

	public MusicPlayerControl() {
		super();
		super.setExpand(false, true);
		containerButtons.setAlignment(Pos.CENTER);
		
		App.getMusicPlayer().getPlayerState().addListenerCurrentSong((obs, oldSong, newSong) -> {
			this.containerTitleAndArtist.setArtist(newSong.getArtist());
			this.containerTitleAndArtist.setTitle(newSong.getTitle());
			this.containerTitleAndArtist.setImage(SongService.getImage(newSong.getCover()));
			this.slider.setMax(newSong.getDuration());
		});
		
		CycleRepeatMode cycleRepeatMode = new CycleRepeatMode();
		
		super.setContent("main", true, containerTitleAndArtist, containerButtons, cycleRepeatMode);
		super.setContent("footer", true, slider);
		super.getStyleClass().add("scene");
		super.setStyle("-fx-padding: 20px 100px;");
		super.setVgap(20);

		StackPane.setAlignment(cycleRepeatMode, Pos.CENTER_RIGHT);
	}
}
