package musicfun.ui.components;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import musicfun.service.navigation.Navigation;
import musicfun.service.navigation.SceneInfo;

public class NavBar extends VBox {
	public NavBar(Navigation navigation) {
		super();
		updateNavBar(navigation);
		navigation.getLeftContainer().getStyleClass().add("nv-left-container");
	}

	public void updateNavBar(Navigation navigation) {
		String currentRute = navigation.getCurrentRoute();
		List<SceneInfo<?>> scenes = navigation.getAvailableScenes();

		super.getChildren().clear();
		for (SceneInfo<?> sceneInfo : scenes) {
			Button btn = this.createButton(sceneInfo, currentRute);
			btn.setOnAction(e -> {
				navigation.navigateTo(sceneInfo.getRouteName());
				updateNavBar(navigation);
			});
			super.getChildren().add(btn);
		}

		super.setMaxHeight(Double.MIN_VALUE);
		super.setMaxWidth(Double.MIN_VALUE);
		super.getStyleClass().add("navbar");
	}

	private Button createButton(SceneInfo<?> sceneInfo, String currentRute) {
		Button button = new Button();
		ImageView image = sceneInfo.getIconAsImageView(25);
		button.setGraphic(image);
		button.getStyleClass().add("btn-nav");
		if (currentRute.equals(sceneInfo.getRouteName())) {
			button.getStyleClass().add("btn-nav-active");
		}
		return button;
	}
}
