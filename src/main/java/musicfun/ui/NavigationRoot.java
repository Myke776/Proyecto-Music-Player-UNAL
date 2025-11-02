package musicfun.ui;

import musicfun.ui.components.GridLayoutManager;
import musicfun.ui.components.NavBar;
import musicfun.ui.navigation.NavigationManager;
import musicfun.ui.views.HomeView;
import musicfun.ui.views.PlaylistsView;
import musicfun.ui.views.Settings;

public class NavigationRoot extends NavigationManager {
	public NavigationRoot(GridLayoutManager gridLayoutManager) {
		super(gridLayoutManager,"home",
				new HomeView(),
				new PlaylistsView(),
				new Settings()
		);
		
		super.setContent("left", true, new NavBar(this));
		String cssNav = getClass().getResource("/styles/navigation.css").toExternalForm();
		super.rootScene.getStylesheets().add(cssNav);

		String cssScene = getClass().getResource("/styles/scene.css").toExternalForm();
		super.rootScene.getStylesheets().add(cssScene);

		super.rootScene.getStyleClass().add("scene");
	}
}
