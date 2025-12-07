package musicfun.ui.component.listView;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class ListFolder extends ListView<String> {
	public ListFolder(ObservableList<String> folders) {
		super(folders);

		super.setCellFactory(__ -> new CellFolder());
	}
}
