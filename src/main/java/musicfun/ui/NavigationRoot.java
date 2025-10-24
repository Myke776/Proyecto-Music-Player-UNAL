package musicfun.ui;

import musicfun.service.navigation.NavigationManager;
import musicfun.ui.views.HomeView;
import musicfun.ui.views.Settings;

public class NavigationRoot extends NavigationManager {
	public NavigationRoot() {
		super("home",
				new HomeView(),
				new Settings());
	}
}
