package musicfun.ui.navigation;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import musicfun.ui.components.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class NavigationManager {
	protected GridLayoutManager rootScene;
	private List<SceneInfo<?>> availableScenes; // Aqui podriamos colocar tambien un listener
	private StringProperty currentRoute = new SimpleStringProperty("");

	public NavigationManager(GridLayoutManager gridLayoutManager) {
		this.rootScene = gridLayoutManager;
		this.availableScenes = new ArrayList<>();
	}

	public NavigationManager(GridLayoutManager gridLayoutManager, String currentRoute, SceneInfo<?>... scenes) {
		this.rootScene = gridLayoutManager;
		this.availableScenes = List.of(scenes);
		this.currentRoute.set(currentRoute);
		navigateTo(currentRoute);
	}

	public void registerScene(SceneInfo<Node> sceneInfo) {
		availableScenes.add(sceneInfo);
	}
	public void removeScene(SceneInfo<Node> sceneInfo) {
		availableScenes.remove(sceneInfo);
	}

	public void navigateTo(String routeName) {
		SceneInfo<?> targetScene = findSceneByRoute(routeName);
		if (targetScene != null) {
			this.rootScene.setContent("main", true, targetScene.getSceneLoader());
			this.currentRoute.set(routeName);;
		}
	}

	public List<SceneInfo<?>> getAvailableScenes() {
		return new ArrayList<>(availableScenes);
	}

	public String getCurrentRoute() {
		return currentRoute.getValue();
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

	public final ObservableList<String> getStyleClass(String nameContainer) {
		if(nameContainer.equals("root")) {
			return this.rootScene.getStyleClass();
		}
		return this.rootScene.getContainer(nameContainer).getStyleClass();
	}

	public void addListener(Consumer<NavigationManager> listener) {
		this.currentRoute.addListener(__ -> {
			listener.accept(this);
		});
	}

	private SceneInfo<?> findSceneByRoute(String routeName) {
		return availableScenes.stream()
				.filter(scene -> scene.getRouteName().equals(routeName))
				.findFirst()
				.orElse(null);
	}
}