package musicfun.model;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SettingsModel {
	private static ObservableList<String> folders = FXCollections.observableArrayList();;

	public static ObservableList<String> getFolders() {
		return folders;
	}

	public static void setFolders(List<String> folders) {
		SettingsModel.folders.setAll(folders);
	}

	public static void addFolder(String folder) {
		SettingsModel.folders.add(folder);
	}

	public static void removeFolder(String folder) {
		SettingsModel.folders.remove(folder);
	}
}
