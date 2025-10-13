package musicfun.ui;

import musicfun.model.NavigationManager;

public class NavigationRoot extends NavigationManager {

	public NavigationRoot() {
		super("home",
				new Home(),
				new Settings());
	}
}