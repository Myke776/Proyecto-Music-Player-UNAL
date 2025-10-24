package musicfun.service.navigation;

import java.util.List;

public class Navigation {
	private final NavigationManager navigationManager;

	public Navigation(NavigationManager manager) {
		navigationManager = manager;
	}

	public void navigateTo(String routeName) {
		navigationManager.navigateTo(routeName);
	}

	public List<SceneInfo<?>> getAvailableScenes() {
		return navigationManager.getAvailableScenes();
	}

	public String getCurrentRoute() {
		return navigationManager.getCurrentRoute();
	}
}