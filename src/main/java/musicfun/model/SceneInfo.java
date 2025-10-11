package musicfun.model;

import javafx.scene.Node;

public class SceneInfo {
	private String title;
	private String icon;
	private String routeName;
	private Node sceneLoader;

	public SceneInfo(String title, String icon, String routeName, Node sceneLoader) {
		this.title = title;
		this.icon = "/icon/" + icon.trim();
		this.routeName = routeName;
		this.sceneLoader = sceneLoader;
	}

	// Getters
	public String getTitle() {
		return title;
	}

	public String getIcon() {
		return icon;
	}

	public String getRouteName() {
		return routeName;
	}

	public Node getSceneLoader() {
		return sceneLoader;
	}

	@Override
	public String toString() {
		return title;
	}
}