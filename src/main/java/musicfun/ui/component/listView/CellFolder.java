package musicfun.ui.component.listView;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import musicfun.model.SettingsModel;

public class CellFolder extends ListCell<String> {
	private HBox containerMain = new HBox();
	private Label path = new Label();


	public CellFolder() {
		Region spacer = new Region();
		Button remove = new Button("Remove");
		containerMain.getChildren().addAll(path, spacer, remove);
		HBox.setHgrow(spacer, Priority.ALWAYS);
		remove.setOnAction(__ -> {
			SettingsModel.removeFolder(super.getItem());
		});
	}

	@Override
	protected void updateItem(String folder, boolean empty) {
		super.updateItem(folder, empty);
		if (empty || folder == null) {
			super.setGraphic(null);
		} else {
			this.path.setText(folder);
			super.setGraphic(this.containerMain);
		}
	}
}
