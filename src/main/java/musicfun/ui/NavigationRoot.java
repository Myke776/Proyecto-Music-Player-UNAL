package musicfun.ui;

import javafx.scene.layout.GridPane;
import musicfun.service.navigation.Navigation;
import musicfun.ui.components.NavBar;
import musicfun.ui.views.HomeView;
import musicfun.ui.views.PlaylistsView;
import musicfun.ui.views.Settings;

public class NavigationRoot extends Navigation {
	public NavigationRoot() {
		super("home",
				new HomeView(),
				new PlaylistsView(),
				new Settings()
		);
		
		super.setContent("left", true, new NavBar(super.getNavigation()));
		String cssNav = getClass().getResource("/styles/navigation.css").toExternalForm();
		super.rootScene.getStylesheets().add(cssNav);

		String cssScene = getClass().getResource("/styles/scene.css").toExternalForm();
		super.rootScene.getStylesheets().add(cssScene);

		super.rootScene.getStyleClass().add("scene");
	}

	public GridPane getRootScene() {
		return super.rootScene;
	}
}
