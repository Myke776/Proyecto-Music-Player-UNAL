package musicfun.ui.views;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import musicfun.logic.SettingsLogic;
import musicfun.model.SettingsModel;
import musicfun.ui.component.listView.ListFolder;
import musicfun.ui.model.navigation.SceneInfo;

public class Settings extends SceneInfo<VBox> {

	public Settings() {
		super("Configuración", "application-settings-gear-black-22536.png", "settings", true, false, new VBox());
		// initializeUI();
	}

	@Override
	protected void initializeUI(VBox scene) {
		scene.setSpacing(20);
		scene.setPadding(new Insets(40));
		// scene.setAlignment(Pos.CENTER);

		Label title = new Label("Settings");
		title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #ffffffff;");

		ListFolder folders = new ListFolder(SettingsModel.getFolders());
		Button addfolder = new Button("Add folder");

		addfolder.setOnAction(__ -> {
			SettingsLogic.addFolder();
		});

		scene.getChildren().addAll(title, folders, addfolder);
	}
}