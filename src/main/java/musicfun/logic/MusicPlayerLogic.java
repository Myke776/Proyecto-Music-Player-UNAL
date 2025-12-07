package musicfun.logic;

import java.io.File;
import java.util.List;
import java.util.Random;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import musicfun.model.PlayerStateModel;
import musicfun.model.SongModel;

public class MusicPlayerLogic {
	private static MediaPlayer mediaPlayer;
	// private static PlayerStateModel playerState = new PlayerStateModel();
	private static ListProperty<SongModel> currentQueue = new SimpleListProperty<>();
	private static int currentIndex = -1;
	private static Random random = new Random();

	// public MusicPlayerLogic(PlayerStateModel playerState) {
	// 	this.playerState = playerState;
	// 	this.random = new Random();
	// 	this.currentIndex = -1;
	// }

	public static void setQueue(List<SongModel> queue) {
		if (currentQueue == null || !currentQueue.equals(queue)) {
			currentQueue.set(FXCollections.observableArrayList(queue));
			currentIndex = -1;
		}
	}

	public static void playSong(SongModel song) {
		stop();
		String songURI = new File(song.getFilePath()).toURI().toString();
		Media songMedia = new Media(songURI);
		mediaPlayer = new MediaPlayer(songMedia);
		mediaPlayer.play();
		PlayerStateModel.setCurrentSong(song);
		PlayerStateModel.setPlaying(true);
		mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
			PlayerStateModel.setCurrentTime((long) newTime.toSeconds());
		});

		mediaPlayer.setOnEndOfMedia(() -> {
			handleSongEnd();
		});
		currentIndex = currentQueue.indexOf(song);
		LibraryLogic.markSongAsPlayed(song);
	}

	public static SongModel playNext() {
		if (currentQueue == null || currentQueue.isEmpty()) {
			return null;
		}

		if (PlayerStateModel.getShuffle()) {
			currentIndex = random.nextInt(currentQueue.size());
		} else {
			currentIndex = (currentIndex + 1) % currentQueue.size();
		}

		SongModel nextSong = currentQueue.get(currentIndex);
		playSong(nextSong);
		return nextSong;
	}

	public static SongModel playPrevious() {
		if (currentQueue == null || currentQueue.isEmpty()) {
			return null;
		}

		if (PlayerStateModel.getShuffle()) {
			currentIndex = random.nextInt(currentQueue.size());
		} else {
			currentIndex = (currentIndex - 1 + currentQueue.size()) % currentQueue.size();
		}

		SongModel previousSong = currentQueue.get(currentIndex);
		playSong(previousSong);
		return previousSong;
	}

	public static void pause() {
		if (mediaPlayer != null) {
			mediaPlayer.pause();
			PlayerStateModel.setPlaying(false);
		}
	}

	public static void resume() {
		if (mediaPlayer != null) {
			mediaPlayer.play();
			PlayerStateModel.setPlaying(true);
		}
	}

	public static void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			PlayerStateModel.setPlaying(false);
			PlayerStateModel.setCurrentTime(0);
		}
	}

	public static void setCurrentTime(long time) {
		if (mediaPlayer != null) {
			mediaPlayer.seek(Duration.seconds(time));
		}
	}

	public static void toggleCycleRepeatMode() {
		switch (PlayerStateModel.getRepeatMode()) {
			case NONE:
				PlayerStateModel.setRepeatMode(PlayerStateModel.RepeatMode.ALL);
				break;
			case ALL:
				PlayerStateModel.setRepeatMode(PlayerStateModel.RepeatMode.ONE);
				break;
			case ONE:
				PlayerStateModel.setRepeatMode(PlayerStateModel.RepeatMode.NONE);
				break;
		}
	}

	public static void toggleShuffleMode() {
		PlayerStateModel.setShuffle(!PlayerStateModel.getShuffle());
	}

	public static SongModel handleSongEnd() {
		if (PlayerStateModel.getRepeatMode() == PlayerStateModel.RepeatMode.ONE) {
			mediaPlayer.seek(Duration.ZERO);
			mediaPlayer.play();
			return PlayerStateModel.getCurrentSong();
		} else {
			return playNext();
		}
	}

	// Getters
	// public static PlayerStateModel getPlayerState() {
	// 	return playerState;
	// }

	public static ObservableList<SongModel> getCurrentQueue() {
		return currentQueue;
	}
	
	public static int getCurrentIndex() {
		return currentIndex;
	}

	// public void addListenerCurrentQueue(ListChangeListener<SongModel> listener) {
	// 	this.currentQueue.addListener(listener);
	// }
}