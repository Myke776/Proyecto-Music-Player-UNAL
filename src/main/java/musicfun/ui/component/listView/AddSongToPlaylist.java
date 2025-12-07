package musicfun.ui.component.listView;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import musicfun.model.PlaylistModel;
import musicfun.model.SongModel;
import musicfun.logic.LibraryLogic;
import musicfun.logic.PlaylistLogic;
import musicfun.ui.model.Modal;

public class AddSongToPlaylist extends Modal {
	private VBox containerMain;

	public AddSongToPlaylist(SongModel song) {
		super("Add Song To Playlist");
		this.containerMain = new VBox();
		super.setNode(containerMain);
		containerMain.getStyleClass().add("scene");
		open(song);
	}

	private void initializeContent(SongModel song) {
		List<String> playlists = LibraryLogic.getAllPlayList();

		Label message = new Label();
		ChoiceBox<String> choice = new ChoiceBox<>();

		Label labelInput = new Label("Add a name to the playlist:");
		TextField input = new TextField();
		Button newPlaylist = new Button("New playlist");
		HBox containerNewPlaylist = new HBox(input, newPlaylist);

		CheckBox checkBox = new CheckBox("Create a new playlist?");

		message.setText("Where do you want to add: " + song.getTitle());
		choice.setItems(FXCollections.observableList(playlists));

		Button cancel = new Button("Cancel");
		Button ok = new Button("Continue");
		HBox containerButtons = new HBox(cancel, ok);

		containerMain.getChildren().addAll(message, choice, checkBox, labelInput, containerNewPlaylist,
				containerButtons);

		
		choice.setValue("Choose a playlist...");
		choice.setMinHeight(40);
		choice.setMinWidth(150);


		input.setMinHeight(40);
		HBox.setHgrow(input, Priority.ALWAYS);
		containerNewPlaylist.setSpacing(20);
		containerNewPlaylist.setAlignment(Pos.CENTER);

		checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
			if (isNowSelected) {
				containerNewPlaylist.setVisible(true);
				labelInput.setVisible(true);
			} else {
				containerNewPlaylist.setVisible(false);
				labelInput.setVisible(false);
			}
		});
		checkBox.setSelected(playlists.size() == 0);

		containerButtons.setAlignment(Pos.CENTER);
		containerButtons.setSpacing(50);
		containerButtons.setPadding(new Insets(50));

		containerMain.setPadding(new Insets(20));
		containerMain.setSpacing(20);

		cancel.setOnAction(__ -> super.close());
		ok.setOnAction(__ -> {
			String namePlaylist = choice.getValue();
			PlaylistModel playlist = PlaylistLogic.getPlaylistByName(namePlaylist);

			if (playlist != null) {
				PlaylistLogic.addSongToPlaylist(playlist, song);
				super.close();
			}
		});
		newPlaylist.setOnAction(__ -> {
			String namePlaylist = input.getText();
			if (!namePlaylist.isBlank()) {
				PlaylistLogic.createPlaylist(namePlaylist, List.of(song));
				super.close();
			}
		});
	}

	private void open(SongModel song) {
		initializeContent(song);
		super.open();
	}
}
