package musicfun.logic;

import musicfun.model.PlayerStateModel;
import musicfun.model.SongModel;

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

public class MusicPlayerLogic {
	private MediaPlayer mediaPlayer;
	private PlayerStateModel playerState;
	private ListProperty<SongModel> currentQueue = new SimpleListProperty<>();
	private int currentIndex;
	private Random random;

	public MusicPlayerLogic(PlayerStateModel playerState) {
		this.playerState = playerState;
		this.random = new Random();
		this.currentIndex = -1;
	}

	public void setQueue(List<SongModel> queue) {
		if (currentQueue == null || !currentQueue.equals(queue)) {
			this.currentQueue.set(FXCollections.observableArrayList(queue));
			this.currentIndex = -1;
		}
	}

	public void playSong(SongModel song) {
		this.stop();
		String songURI = new File(song.getFilePath()).toURI().toString();
		Media songMedia = new Media(songURI);
		this.mediaPlayer = new MediaPlayer(songMedia);
		mediaPlayer.play();
		playerState.setCurrentSong(song);
		playerState.setPlaying(true);
		mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
			playerState.setCurrentTime((long) newTime.toSeconds());
		});

		mediaPlayer.setOnEndOfMedia(() -> {
			handleSongEnd();
		});
		this.currentIndex = currentQueue.indexOf(song);
		LibraryLogic.markSongAsPlayed(song);
	}

	public SongModel playNext() {
		if (currentQueue == null || currentQueue.isEmpty()) {
			return null;
		}

		if (playerState.getShuffle()) {
			currentIndex = random.nextInt(currentQueue.size());
		} else {
			currentIndex = (currentIndex + 1) % currentQueue.size();
		}

		SongModel nextSong = currentQueue.get(currentIndex);
		playSong(nextSong);
		return nextSong;
	}

	public SongModel playPrevious() {
		if (currentQueue == null || currentQueue.isEmpty()) {
			return null;
		}

		if (playerState.getShuffle()) {
			currentIndex = random.nextInt(currentQueue.size());
		} else {
			currentIndex = (currentIndex - 1 + currentQueue.size()) % currentQueue.size();
		}

		SongModel previousSong = currentQueue.get(currentIndex);
		playSong(previousSong);
		return previousSong;
	}

	public void pause() {
		if (mediaPlayer != null) {
			mediaPlayer.pause();
			playerState.setPlaying(false);
		}
	}

	public void resume() {
		if (mediaPlayer != null) {
			mediaPlayer.play();
			playerState.setPlaying(true);
		}
	}

	public void stop() {
		if (mediaPlayer != null) {
			this.mediaPlayer.stop();
			playerState.setPlaying(false);
			playerState.setCurrentTime(0);
		}
	}

	public void setCurrentTime(long time) {
		if (mediaPlayer != null) {
			mediaPlayer.seek(Duration.seconds(time));
		}
	}

	public void toggleCycleRepeatMode() {
		switch (playerState.getRepeatMode()) {
			case NONE:
				playerState.setRepeatMode(PlayerStateModel.RepeatMode.ALL);
				break;
			case ALL:
				playerState.setRepeatMode(PlayerStateModel.RepeatMode.ONE);
				break;
			case ONE:
				playerState.setRepeatMode(PlayerStateModel.RepeatMode.NONE);
				break;
		}
	}

	public void toggleShuffleMode() {
		playerState.setShuffle(!playerState.getShuffle());
	}

	public SongModel handleSongEnd() {
		if (playerState.getRepeatMode() == PlayerStateModel.RepeatMode.ONE) {
			mediaPlayer.seek(Duration.ZERO);
			mediaPlayer.play();
			return playerState.getCurrentSong();
		} else {
			return playNext();
		}
	}

	// Getters
	public PlayerStateModel getPlayerState() {
		return playerState;
	}

	public ObservableList<SongModel> getCurrentQueue() {
		return currentQueue;
	}
	public int getCurrentIndex() {
		return currentIndex;
	}

	// public void addListenerCurrentQueue(ListChangeListener<SongModel> listener) {
	// 	this.currentQueue.addListener(listener);
	// }
}