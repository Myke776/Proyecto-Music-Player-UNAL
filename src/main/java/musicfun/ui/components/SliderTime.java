package musicfun.ui.components;

import java.util.Locale;

import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import musicfun.App;
import musicfun.service.SongService;

public class SliderTime extends HBox {
	private Slider slider;
	private Text currentTime;
	private Text finalTime;

	public SliderTime() {
		super();
		this.slider = new Slider();
		this.currentTime = new Text("00:00");
		this.finalTime = new Text("00:00");

		super.getChildren().addAll(currentTime, slider, finalTime);

		slider.setOnMouseReleased(event -> {
			App.getMusicPlayer().resume();
		});

		this.slider.valueProperty().addListener((obs, oldValue, newValue) -> {
			if (this.slider.isPressed()) {
				App.getMusicPlayer().pause();
				App.getMusicPlayer().setCurrentTime(newValue.longValue());
			}
			double percentage = 100.0 * newValue.doubleValue() / slider.getMax();
			String style = String.format(Locale.US,
					"-fx-background-color: linear-gradient(to right, " +
							"#ffffffff 0%%, " +
							"#ffffffff %1$.1f%%, " +
							"#ffffff51 %1$.1f%%, " +
							"#ffffff51 100%%);",
					percentage);
			slider.setStyle(style);
		});

		App.getMusicPlayer().getPlayerState().addListenerCurrentTime((obs, oldTime, newTime) -> {
			slider.setValue(newTime.longValue());
			this.currentTime.setText(SongService.getFormattedDuration(newTime.longValue()));
		});
		App.getMusicPlayer().getPlayerState().addListenerCurrentSong((obs, oldSong, newSong) -> {
			this.finalTime.setText(newSong.getFormattedDuration());
			this.slider.setMax(newSong.getDuration());
		});

		HBox.setHgrow(slider, Priority.ALWAYS);
		super.getStyleClass().add("container-slider-time");
		String cssRute = getClass().getResource("/styles/slider.css").toExternalForm();
		super.getStylesheets().add(cssRute);
	}

	public void setMax(double value) {
		slider.setMax(value);
	}
}
