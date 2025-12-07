package musicfun.app;

import musicfun.logic.SettingsLogic;

public class InitApp {

	public static void loadAll() {
		SettingsLogic.updateLibrary();
		loadFolders();
		loadPlayLists();
	}

	private static void loadFolders() {

	}

	private static void loadPlayLists() {

	}
}
