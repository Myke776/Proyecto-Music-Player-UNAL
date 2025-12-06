package musicfun.ui.model;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Modal <Type extends Parent> extends Stage  {
	public Modal(String title, Type node) {
		super(StageStyle.TRANSPARENT);
		super.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
		super.initModality(Modality.APPLICATION_MODAL);
		super.setTitle(title);

		Scene scene = new Scene(node);
		scene.setFill(Color.TRANSPARENT);
		super.setScene(scene);
		super.setMinWidth(300);
		super.setMinHeight(200);

		String cssScene = getClass().getResource("/styles/scene.css").toExternalForm();
		node.getStylesheets().add(cssScene);
		node.getStyleClass().add("scene");
		node.setStyle("-fx-background-radius: 20");
	}

	public void showModal() {
		super.showAndWait();
		super.centerOnScreen();
	}
}
