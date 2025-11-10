package musicfun.model;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;

public class PlayerStateModel {
	private final ObjectProperty<SongModel> currentSong = new SimpleObjectProperty<>();
	private final BooleanProperty playing = new SimpleBooleanProperty(false);
	private final LongProperty currentTime = new SimpleLongProperty(0);
	private final DoubleProperty volume = new SimpleDoubleProperty(0.5);
	private final ObjectProperty<RepeatMode> repeatMode = new SimpleObjectProperty<>(RepeatMode.ALL);

	public enum RepeatMode {
		NONE, ONE, ALL, RANDOM
	}

	public enum Variable {
		SongModel, PLAYING, CURRENT_TIME, REPEAT_MODE
	}

	public SongModel getCurrentSong() {
		return currentSong.getValue();
	}

	public void setCurrentSong(SongModel song) {
		this.currentSong.set(song);
	}

	public boolean isPlaying() {
		return playing.get();
	}

	public void setPlaying(boolean value) {
		this.playing.set(value);
	}

	public long getCurrentTime() {
		return currentTime.get();
	}

	public void setCurrentTime(long value) {
		this.currentTime.set(value);
	}

	public double getVolume() {
		return volume.get();
	}

	public void setVolume(double value) {
		this.volume.set(Math.max(0.0, Math.min(1.0, value)));
	}

	public RepeatMode getRepeatMode() {
		return repeatMode.get();
	}

	public void setRepeatMode(RepeatMode value) {
		this.repeatMode.set(value);
	}

	public void  addListenerCurrentSong(ChangeListener<SongModel> listener) {
		this.currentSong.addListener(listener);
	}

	public void addListenerPlaying(ChangeListener<Boolean> listener) {
		this.playing.addListener(listener);
	}

	public void addListenerCurrentTime(ChangeListener<Number> listener) {
		this.currentTime.addListener(listener);
	}

	public void addListenerVolume(ChangeListener<Number> listener) {
		this.volume.addListener(listener);
	}

	public void addListenerRepeatMode(ChangeListener<RepeatMode> listener) {
		this.repeatMode.addListener(listener);
	}

	@Override
	public String toString() {
		return String.format(
				"PlayerState{song=%s, playing=%s, time=%dms, volume=%.2f, repeat=%s, shuffle=%s}",
				getCurrentSong() != null ? getCurrentSong().getTitle() : "null",
				isPlaying(),
				getCurrentTime(),
				getVolume(),
				getRepeatMode());
	}
}
