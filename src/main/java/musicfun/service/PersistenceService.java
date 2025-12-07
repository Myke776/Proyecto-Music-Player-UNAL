package musicfun.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.collections.FXCollections;
import musicfun.logic.LibraryLogic;
import musicfun.model.PlaylistModel;
import musicfun.model.SongModel;

public class PersistenceService {
	private static final String RESOURCES_CSV_PLAYLISTS = "/persistence/playlists.csv";
	private static final String RESOURCES_CSV_FOLDERS = "/persistence/folders.csv";
	private static final String APP_DATA_DIR = ".musicfun";

	private static final String PLAYLISTS_FILE = "playlists.csv";
	private static final String FOLDERS_FILE = "folders.csv";

	public List<PlaylistModel> getPlaysLists() {
		List<PlaylistModel> playlists = new ArrayList<>();

		try {
			File source = getFile(RESOURCES_CSV_PLAYLISTS, PLAYLISTS_FILE);
			Scanner sc = new Scanner(source);

			if (sc != null) {
				while (sc.hasNext()) {
					String line = sc.nextLine();
					PlaylistModel playlist = createPlayList(line);
					playlists.add(playlist);
				}
				sc.close();

			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return playlists;
	}

	public List<String> getFolders() {
		List<String> folders = new ArrayList<>();
		try {
			File source = getFile(RESOURCES_CSV_FOLDERS, FOLDERS_FILE);
			Scanner sc = new Scanner(source);
			if (sc != null) {
				while (sc.hasNext()) {
					String line = sc.nextLine();
					folders.add(line);
				}
				sc.close();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return folders;
	}

	public void savePlaylists(List<PlaylistModel> playlists) throws IOException {
		Path targetPath = getAppDataPath(PLAYLISTS_FILE);

		try (BufferedWriter writer = Files.newBufferedWriter(targetPath)) {
			// writer.write("id,name,description,createdDate,modifiedDate,songIds\n");
			for (PlaylistModel playlist : playlists) {
				writer.write(playlistToCSVLine(playlist));
				writer.newLine();
			}
			writer.flush();
		}
	}

	public void saveFolders(List<String> folders) throws IOException{
		Path targetPath = getAppDataPath(FOLDERS_FILE);

		try (BufferedWriter writer = Files.newBufferedWriter(targetPath)) {
			// writer.write("id,name,description,createdDate,modifiedDate,songIds\n");
			for (String folder : folders) {
				writer.write(folder);
				writer.newLine();
			}
			writer.flush();
		}
	}

	private File getFile(String resourcePath, String fileName) throws IOException {
		Path targetPath = getAppDataPath(fileName);

		if (Files.exists(targetPath)) {
			return targetPath.toFile();
		}

		try (InputStream is = getClass().getResourceAsStream(resourcePath)) {
			if (is == null) {
				createEmptyCSVFile(targetPath);
			} else {
				Files.copy(is, targetPath, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.out.println(targetPath);
		return targetPath.toFile();
	}

	private PlaylistModel createPlayList(String line) {
		Scanner sc = new Scanner(line);
		sc.useDelimiter(";");

		String id = sc.next().trim();
		String name = sc.next().trim();
		String description = sc.next().trim();
		LocalDateTime createdDate = LocalDateTime.parse(sc.next().trim());
		LocalDateTime modifiedDate = LocalDateTime.parse(sc.next().trim());

		List<SongModel> songs = new ArrayList<>();

		while (sc.hasNext()) {
			String musicPath = sc.next();
			SongModel song = LibraryLogic.getSongsByFilePath(musicPath);
			songs.add(song);
		}

		sc.close();
		return new PlaylistModel(id, name, description, FXCollections.observableArrayList(songs), createdDate,
				modifiedDate);
	}

	private void createEmptyCSVFile(Path path) throws IOException {
		Files.write(path, "".getBytes());
	}

	private Path getAppDataPath(String fileName) {
		String userHome = System.getProperty("user.home");
		Path appDir = Paths.get(userHome, APP_DATA_DIR);

		try {
			if (!Files.exists(appDir)) {
				Files.createDirectories(appDir);
			}
		} catch (IOException e) {
			throw new RuntimeException("No se pudo crear el directorio de la aplicación", e);
		}

		return appDir.resolve(fileName);
	}

	private String playlistToCSVLine(PlaylistModel playlist) {
		StringBuilder sb = new StringBuilder();

		sb.append(playlist.getId()).append(";");
		sb.append(escapeCSV(playlist.getName())).append(";");
		sb.append(escapeCSV(playlist.getDescription())).append(";");
		sb.append(playlist.getCreatedDate()).append(";");
		sb.append(playlist.getModifiedDate());

		List<SongModel> songs = playlist.getSongs();
		if (songs.isEmpty()) {
			sb.append("");
		} else {
			for (SongModel songModel : songs) {
				sb.append(";");
				sb.append(songModel.getFilePath());
			}
		}

		return sb.toString();
	}

	private String escapeCSV(String value) {
		if (value == null)
			return "";
		if (value.contains(",") || value.contains("\"")) {
			return "\"" + value.replace("\"", "\"\"") + "\"";
		}
		return value;
	}
}
