package musicfun.logic;

import java.util.List;

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


	public static void updateAll() {
		// new Thread(() -> {
			//Agregar aqui escena de carga(Probar con cosas basicas primero)
			// javafx.application.Platform.runLater(() -> {
			updateFolders();
			updateLibrary();
			updatePlayLists();
			// });
		// }).start();
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
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
