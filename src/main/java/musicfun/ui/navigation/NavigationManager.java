package musicfun.ui.navigation;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import musicfun.ui.components.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public abstract class NavigationManager {
	protected GridLayoutManager rootScene;
	private List<SceneInfo<?>> availableScenes;
	private String currentRoute;

	public NavigationManager(GridLayoutManager gridLayoutManager) {
		this.rootScene = gridLayoutManager;
		this.availableScenes = new ArrayList<>();
	}

	public NavigationManager(GridLayoutManager gridLayoutManager, String currentRoute, SceneInfo<?>... scenes) {
		this.rootScene = gridLayoutManager;
		this.availableScenes = List.of(scenes);
		this.currentRoute = currentRoute;
		navigateTo(currentRoute);
	}

	public void registerScene(SceneInfo<Node> sceneInfo) {
		availableScenes.add(sceneInfo);
	}

	public void navigateTo(String routeName) {
		SceneInfo<?> targetScene = findSceneByRoute(routeName);
		if (targetScene != null) {
			this.rootScene.setContent("main", true, targetScene.getSceneLoader());
			this.currentRoute = routeName;
		}
	}

	public List<SceneInfo<?>> getAvailableScenes() {
		return new ArrayList<>(availableScenes);
	}

	public String getCurrentRoute() {
		return currentRoute;
	}

	public void toShow(String nameContainer) {
		this.rootScene.toShow(nameContainer);
	}

	public void toHide(String nameContainer) {
		this.rootScene.toHide(nameContainer);
	}

	public void setContent(String nameContainer, boolean deletePreviousContent, Node... content) {
		this.rootScene.setContent(nameContainer, deletePreviousContent, content);
	}

	public final ObservableList<String> getgetStyleClass(String nameContainer) {
		if(nameContainer.equals("root")) {
			return this.rootScene.getStyleClass();
		}
		return this.rootScene.getContainer(nameContainer).getStyleClass();
	}

	private SceneInfo<?> findSceneByRoute(String routeName) {
		return availableScenes.stream()
				.filter(scene -> scene.getRouteName().equals(routeName))
				.findFirst()
				.orElse(null);
	}
}