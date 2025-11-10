package musicfun.ui.components;

import javafx.geometry.Pos;
// import javafx.scene.control.Slider;
import musicfun.App;
import musicfun.service.SongService;

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


		// super.setContent("left", true, containerTitleAndArtist, containerTitleAndArtist);
		super.setContent("main", true, containerTitleAndArtist,containerButtons);
		super.setContent("footer", true, slider);
		super.getStyleClass().add("scene");
		super.setStyle("-fx-padding: 20px 100px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 5, 0.5, 0, 1);");
		super.setVgap(20);
	}
}
