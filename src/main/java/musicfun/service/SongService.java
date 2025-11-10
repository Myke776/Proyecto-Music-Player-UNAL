package musicfun.service;

import java.io.ByteArrayInputStream;

import javafx.scene.image.Image;

public class SongService {
	private static final Image DEFAULT_COVER = new Image(SongService.class.getResourceAsStream("/icons/no-image-14598.png"));

	public static String getFormattedDuration(long duration) {
		long seconds = duration / 1000;
		long minutes = seconds / 60;
		seconds = seconds % 60;
		return String.format("%02d:%02d", minutes, seconds);
	}

	public static Image getImage(byte[] cover) {
		if(cover != null) {
			Image image = new Image(new ByteArrayInputStream(cover));
			return image;
		};
		return DEFAULT_COVER;
	}
}
