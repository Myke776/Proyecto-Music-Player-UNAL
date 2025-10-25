package musicfun.ui.components;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import musicfun.service.navigation.Navigation;
import musicfun.service.navigation.SceneInfo;

public class NavBar extends VBox {
	public NavBar(Navigation navigation){
		super();
		updateNavBar(navigation);
		navigation.getLeftContainer().getStyleClass().add("nv-left-container");
	}

	public void updateNavBar(Navigation navigation) {
		String currentRute = navigation.getCurrentRoute();
		List<SceneInfo<?>> scenes = navigation.getAvailableScenes();
		
		super.getChildren().clear();
		for (SceneInfo<?> sceneInfo : scenes) {
			super.getChildren().add(this.createButton(sceneInfo.getIcon(), currentRute));
		}

		super.setMaxHeight(Double.MIN_VALUE);
		super.setMaxWidth(Double.MIN_VALUE);
		super.getStyleClass().add("navbar");
	}

	private ImageView createButton(String routeImage, String routeName) {
		Image image = new Image(getClass().getResourceAsStream(routeImage));
		ImageView imageView= new ImageView(image);
		imageView.setFitWidth(25);
		imageView.setFitHeight(25);
		return imageView;
	}
}
