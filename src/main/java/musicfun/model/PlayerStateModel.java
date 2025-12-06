package musicfun.model;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;

public class PlayerStateModel {
	private final static ObjectProperty<SongModel> CURRENT_SONG = new SimpleObjectProperty<>();
	private final static BooleanProperty PLAYIN = new SimpleBooleanProperty(false);
	private final static LongProperty CURRENT_TIME = new SimpleLongProperty(0);
	private final static DoubleProperty VOLUME = new SimpleDoubleProperty(0.5);
	private final static ObjectProperty<RepeatMode> REPEAT_MODE = new SimpleObjectProperty<>(RepeatMode.ALL);
	private final static BooleanProperty shuffle = new SimpleBooleanProperty(false);

	public static enum RepeatMode {
		NONE, ONE, ALL
	}

	public static SongModel getCurrentSong() {
		return CURRENT_SONG.getValue();
	}

	public static void setCurrentSong(SongModel song) {
		CURRENT_SONG.set(song);
	}

	public static boolean isPlaying() {
		return PLAYIN.get();
	}

	public static void setPlaying(boolean value) {
		PLAYIN.set(value);
	}

	public static long getCurrentTime() {
		return CURRENT_TIME.get();
	}

	public static void setCurrentTime(long value) {
		CURRENT_TIME.set(value);
	}

	public static double getVolume() {
		return VOLUME.get();
	}

	public static void setVolume(double value) {
		VOLUME.set(Math.max(0.0, Math.min(1.0, value)));
	}

	public static RepeatMode getRepeatMode() {
		return REPEAT_MODE.get();
	}

	public static void setRepeatMode(RepeatMode value) {
		REPEAT_MODE.set(value);
	}

	public static boolean getShuffle() {
		return shuffle.get();
	}

	public static void setShuffle(boolean state) {
		shuffle.set(state);
	}

	public static void  addListenerCurrentSong(ChangeListener<SongModel> listener) {
		CURRENT_SONG.addListener(listener);
	}

	public static void addListenerPlaying(ChangeListener<Boolean> listener) {
		PLAYIN.addListener(listener);
	}

	public static void addListenerCurrentTime(ChangeListener<Number> listener) {
		CURRENT_TIME.addListener(listener);
	}

	public static void addListenerVolume(ChangeListener<Number> listener) {
		VOLUME.addListener(listener);
	}

	public static void addListenerRepeatMode(ChangeListener<RepeatMode> listener) {
		REPEAT_MODE.addListener(listener);
	}

	public static void addListenerShuffle(ChangeListener<Boolean> listner) {
		shuffle.addListener(listner);
	}

	@Override
	public String toString() {
		return String.format(
				"PlayerState{song=%s, PLAYIN=%s, time=%dms, VOLUME=%.2f, repeat=%s, shuffle=%s}",
				getCurrentSong() != null ? getCurrentSong().getTitle() : "null",
				isPlaying(),
				getCurrentTime(),
				getVolume(),
				getRepeatMode());
	}
}
