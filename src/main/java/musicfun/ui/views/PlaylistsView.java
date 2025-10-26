package musicfun.ui.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import musicfun.App;
import musicfun.service.navigation.SceneInfo;

public class PlaylistsView extends SceneInfo<VBox> {

	public PlaylistsView() {
		super("PlayList", "list-6216.png", "playlist", true, false, new VBox());
		initializeUI();
	}

	private void initializeUI() {
		VBox scene = super.getSceneLoader();
		scene.setSpacing(20);
		scene.setPadding(new Insets(40));
		scene.setAlignment(Pos.CENTER);
		// scene.setStyle("-fx-background-color: #ffffff;");

		Label title = new Label("Esto es Playlist");
		title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

		Button button1 = new Button("Ir a settings");
		button1.setStyle(
				"-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20;");
		button1.setOnAction(event -> {
			App.getNavigation().navigateTo("settings");
			// App.navigation.navigateTo("settings");// probando navegacion
		});
		scene.getChildren().addAll(title, button1);
	}
}