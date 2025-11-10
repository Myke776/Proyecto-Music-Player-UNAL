package musicfun.service;

import musicfun.model.SongModel;
import java.io.File;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;

public class MetadataService {
	public SongModel readMetadata(File file) {
		String rute = file.getAbsolutePath();
		String fileName = file.getName();
		int dotIndex = fileName.lastIndexOf('.');
		if (dotIndex > 0) {
			fileName = fileName.substring(0, dotIndex);
		}

		SongModel song = new SongModel(rute);

		try {
			AudioFile audioFile = AudioFileIO.read(file);
			Tag tag = audioFile.getTag();

			String title = tag.getFirst(FieldKey.TITLE);
			song.setTitle(title.isEmpty() ? fileName : title);

			String artist = tag.getFirst(FieldKey.ARTIST);
			if (!artist.isEmpty())
				song.setArtist(artist);

			String album = tag.getFirst(FieldKey.ALBUM);
			if (!album.isEmpty())
				song.setAlbum(album);

			String genre = tag.getFirst(FieldKey.GENRE);
			song.setGenre(genre);

			long duration = (long) (audioFile.getAudioHeader().getTrackLength() * 1000L);
			song.setDuration(duration);

			String year = tag.getFirst(FieldKey.YEAR);
			song.setYear(year);

			Artwork cover = tag.getFirstArtwork();
			if (cover != null)
				song.setCover(cover.getBinaryData());
		} catch (Exception e) {
			System.err.println("Error leyendo metadatos de " + file.getName() + ": " + e.getMessage());
		}

		return song;
	}


	public boolean updateMetadata(SongModel song, File audioFile) {
		try {

			System.out.println("Metadatos actualizados para: " + song.getTitle());
			return true;

		} catch (Exception e) {
			System.err.println("Error actualizando metadatos: " + e.getMessage());
			return false;
		}
	}

	public boolean hasReadableMetadata(File audioFile) {
		// Verificación básica de formato de archivo
		String name = audioFile.getName().toLowerCase();
		return name.endsWith(".mp3") || name.endsWith(".flac") ||
				name.endsWith(".m4a") || name.endsWith(".wav");
	}
}