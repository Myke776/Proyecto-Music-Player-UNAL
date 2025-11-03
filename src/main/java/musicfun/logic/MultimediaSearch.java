package musicfun.logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MultimediaSearch {

	public static List<String> getWindowsAudioPaths(List<String> folders) {
		String command = "Get-ChildItem -Path %s -Include *.mp3,*.wav,*.flac,*.aac,*.ogg -Recurse -ErrorAction SilentlyContinue | Select-Object FullName";
		String console = "powershell.exe";
		List<String> files = new ArrayList<>();
		try {
			for (String rute : folders) {
				files.addAll(executeCommand(console, String.format(command, rute)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return files;
	}

	private static List<String> executeCommand(String console, String command) throws Exception {
		List<String> result = new ArrayList<>();
		ProcessBuilder builder = new ProcessBuilder(console, command);
		Process process = builder.start();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (!line.isBlank()) {
					result.add(line.trim());
				}
			}
		}

		result.remove(0);
		result.remove(0);
		process.waitFor();
		return result;
	}

}