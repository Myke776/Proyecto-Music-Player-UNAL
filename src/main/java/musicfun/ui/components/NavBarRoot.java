package musicfun.ui.components;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import musicfun.ui.model.navigation.NavigationManager;
import musicfun.ui.model.navigation.SceneInfo;

public class NavBarRoot extends VBox {
	public NavBarRoot(NavigationManager navigation) {
		super();
		updateNavBar(navigation);
		navigation.getStyleClass("left").add("nv-left-container");
		navigation.addListener((nav) -> this.updateNavBar(nav));
	}

	public void updateNavBar(NavigationManager navigation) {
		String currentRute = navigation.getCurrentRoute();
		List<SceneInfo<?>> scenes = navigation.getAvailableScenes();

		super.getChildren().clear();
		for (SceneInfo<?> sceneInfo : scenes) {
			if(sceneInfo.getIconVisible()) {
				Button btn = this.createButton(sceneInfo, currentRute);
				btn.setOnAction(e -> {
					navigation.navigateTo(sceneInfo.getRouteName());
				});
				super.getChildren().add(btn);
			}
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
