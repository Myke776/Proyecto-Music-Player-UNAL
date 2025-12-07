package musicfun.logic;

import java.io.File;
import java.util.List;

import javafx.stage.DirectoryChooser;
import musicfun.logic.LibraryLogic.SortType;
import musicfun.model.LibraryModel;
import musicfun.model.PlaylistModel;
import musicfun.model.SettingsModel;
import musicfun.model.SongModel;
import musicfun.service.MetadataService;
import musicfun.service.MultimediaSearch;
import musicfun.service.PersistenceService;

public class SettingsLogic {
	private static MetadataService metadataReader = new MetadataService();
	private static PersistenceService persistenceService = new PersistenceService();

	public static void addFolder() {
		DirectoryChooser directory = new DirectoryChooser();
		directory.setTitle("Select folder music");
		directory.setInitialDirectory(new File(System.getProperty("user.home")));
		File selectedDirectory = directory.showDialog(null);

		if (selectedDirectory != null) {
			String path = selectedDirectory.getAbsolutePath();
			SettingsModel.addFolder(path);
		}
	}

	public static void updateAll() {
		updateFolders();
		updateLibrary();
		updatePlayLists();
	}

	public static void updateFolders() {
		List<String> folders = persistenceService.getFolders();
		SettingsModel.setFolders(folders);
	}

	public static void updateLibrary() {
		List<String> folders = SettingsModel.getFolders();
		scanFolders(folders);
	}

	public static void updatePlayLists() {
		List<PlaylistModel> playlists = persistenceService.getPlaysLists();
		LibraryModel.setPlaylist(playlists);
	}

	public static void scanFolders(List<String> folders) {
		List<String> paths = MultimediaSearch.getWindowsAudioPaths(folders);

		for (String rute : paths) {
			SongModel song = metadataReader.readMetadata(rute);
			LibraryModel.addSong(song);
		}

		LibraryLogic.sortSongs(SortType.NAME, true);
	}

	public static void saveAll() {
		try {
			List<PlaylistModel> playlists = LibraryModel.getPlaylists();
			persistenceService.savePlaylists(playlists);
			List<String> folders = SettingsModel.getFolders();
			persistenceService.saveFolders(folders);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
