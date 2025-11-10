package musicfun.ui;

import musicfun.ui.components.GridLayoutManager;
import musicfun.ui.components.NavBarRoot;
import musicfun.ui.components.playerControl.MusicPlayerControl;
import musicfun.ui.navigation.NavigationManager;
import musicfun.ui.views.HomeView;
import musicfun.ui.views.MyMusic;
import musicfun.ui.views.PlaylistsView;
import musicfun.ui.views.Settings;

public class NavigationRoot extends NavigationManager {
	public NavigationRoot(GridLayoutManager gridLayoutManager) {
		super(gridLayoutManager,"home",
				new HomeView(),
				new PlaylistsView(),
				new MyMusic(),
				new Settings()
		);
		
		super.setContent("left", true, new NavBarRoot(this));
		super.setContent("footer", true, new MusicPlayerControl());
		String cssNav = getClass().getResource("/styles/navigation.css").toExternalForm();
		super.rootScene.getStylesheets().add(cssNav);

		String cssScene = getClass().getResource("/styles/scene.css").toExternalForm();
		super.rootScene.getStylesheets().add(cssScene);

		super.rootScene.getStyleClass().add("scene");
	}
}
