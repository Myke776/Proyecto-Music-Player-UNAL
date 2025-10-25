package musicfun.ui.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import musicfun.App;
import musicfun.service.navigation.SceneInfo;

public class Settings extends SceneInfo<VBox> {

	public Settings() {
		super("Configuración", "application-settings-gear-black-22536.png", "settings", true, false, new VBox());
		initializeUI();
	}

	private void initializeUI() {
		VBox scene = super.getSceneLoader();
		scene.setSpacing(20);
		scene.setPadding(new Insets(40));
		scene.setAlignment(Pos.CENTER);
		// scene.setStyle("-fx-background-color: #ffffff;");

		Label title = new Label("Esto es Settings");
		title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

		Button startButton = new Button("Ir a home");
		startButton.setStyle(
				"-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20;");
		startButton.setOnAction(event -> {
			App.getNavigation().navigateTo("home");// probando navegacion
		});
		scene.getChildren().addAll(title, startButton);
	}
}