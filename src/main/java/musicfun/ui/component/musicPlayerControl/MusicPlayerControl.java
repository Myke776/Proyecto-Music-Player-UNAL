package musicfun.ui.component.musicPlayerControl;

import javafx.geometry.Pos;
import musicfun.model.PlayerStateModel;
import musicfun.service.SongService;
import musicfun.ui.component.ContainerImageTitleText;
import musicfun.ui.component.GridLayoutManager;
import musicfun.ui.layout.SizeConstraints;

public class MusicPlayerControl extends GridLayoutManager {
	private final ContainerImageTitleText containerTitleAndArtist = new ContainerImageTitleText(new SizeConstraints(60));
	private final ContainerButtons containerButtons = new ContainerButtons();
	private final SliderTime slider = new SliderTime();

	public MusicPlayerControl() {
		super();
		super.setExpand(false, true);
		containerButtons.setAlignment(Pos.CENTER);
		
		PlayerStateModel.addListenerCurrentSong((obs, oldSong, newSong) -> {
			this.containerTitleAndArtist.setArtist(newSong.getArtist());
			this.containerTitleAndArtist.setTitle(newSong.getTitle());
			this.containerTitleAndArtist.setImage(SongService.getImage(newSong.getCover()));
			this.slider.setMax(newSong.getDuration());
		});
		
		super.setContent("main", true, containerTitleAndArtist, containerButtons);
		super.setContent("footer", true, slider);
		super.getStyleClass().add("scene");
		super.setStyle("-fx-padding: 20px 100px;");
		super.setVgap(10);
	}
}
