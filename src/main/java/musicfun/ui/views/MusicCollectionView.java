package musicfun.ui.views;

import javafx.scene.layout.VBox;
import musicfun.ui.model.navigation.SceneInfo;

public class MusicCollectionView extends SceneInfo<VBox> {
	public MusicCollectionView() {
		super("MusicCollection", "", "musicCollection", false, false, new VBox());
	}


	@Override
	protected void initializeUI(VBox scene) {
		// TODO Auto-generated method stub
		
	}
}
