package musicfun.model;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class SceneInfo<TypeScene extends Node> {
	private String title;
	private String icon;
	private String routeName;
	private TypeScene sceneLoader;
	private boolean iconVisible;
	private boolean titleVisible;

	public SceneInfo(String title, String icon, String routeName, boolean iconVisible, boolean titleVisible,
			TypeScene sceneLoader) {
		this.title = title;
		this.icon = icon.startsWith("/") ? icon : "/icon/" + icon.trim();
		this.routeName = routeName;
		this.iconVisible = iconVisible;
		this.titleVisible = titleVisible;
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

	public TypeScene getSceneLoader() {
		return sceneLoader;
	}

	public boolean getIconVisible() {
		return iconVisible;
	}

	public boolean getTitleVisible() {
		return titleVisible;
	}

	public ImageView getIconAsImageView(double size) {
		try {
			Image image = new Image(getClass().getResourceAsStream(icon));
			ImageView imageView = new ImageView(image);
			imageView.setFitWidth(size);
			imageView.setFitHeight(size);
			imageView.setPreserveRatio(true);
			return imageView;
		} catch (Exception e) {
			System.err.println("No se pudo cargar el icono: " + icon);
			// Fallback: crear un ImageView vacío
			ImageView fallback = new ImageView();
			fallback.setFitWidth(size);
			fallback.setFitHeight(size);
			fallback.setStyle("-fx-background-color: #666; -fx-border-radius: 3;");
			return fallback;
		}
	}

	@Override
	public String toString() {
		return title;
	}
}