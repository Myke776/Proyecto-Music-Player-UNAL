package musicfun.service;

import java.io.ByteArrayInputStream;

import javafx.scene.image.Image;

public class SongService {
	private static final Image DEFAULT_COVER = new Image(SongService.class.getResourceAsStream("/icons/no-image-14598.png"));

	public static String getFormattedDuration(long totalSeconds) {
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        if (hours > 0) {
            return String.format("%d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%02d:%02d", minutes, seconds);
        }
	}

	public static Image getImage(byte[] cover) {
		if(cover != null) {
			Image image = new Image(new ByteArrayInputStream(cover));
			return image;
		};
		return DEFAULT_COVER;
	}
}
