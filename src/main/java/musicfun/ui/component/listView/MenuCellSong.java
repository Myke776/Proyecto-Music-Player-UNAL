package musicfun.ui.component.listView;


import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import musicfun.logic.LibraryLogic;
import musicfun.logic.PlaylistLogic;
import musicfun.model.SongModel;

public class MenuCellSong extends StackPane {
	private SongModel song;
	private double size;
	private String idPlaylist;

	public MenuCellSong(SongModel song, String idPlaylist, double size) {
		super();
		this.song = song;
		this.idPlaylist = idPlaylist;
		this.size = size;
		initializeUI(idPlaylist, size * 0.7);
		super.setMaxWidth(size);
		super.setMaxHeight(size);
	}

	public SongModel getSong() {
		return song;
	}

	public void setSong(SongModel song) {
		this.song = song;
	}

	public String getIdPlaylist() {
		return idPlaylist;
	}

	public void setIdPlaylist(String idPlaylist) {
		this.idPlaylist = idPlaylist;
		initializeUI(idPlaylist, this.size * 0.7);
	}

	private void initializeUI(String idPlaylist, double size) {
		super.getChildren().clear();
		if (idPlaylist != null) {
			createCompleteMenu(size);
		} else {
			createSimpleMenu(size);
		}
	}

	private void createSimpleMenu(double size) {
		Button addSongToPlaylist = new Button();
		ImageView imageView = new ImageView(new Image(
				getClass().getResourceAsStream("/icons/music-player-add-playlist-queue-round-outline-icon.png")));
		addSongToPlaylist.setGraphic(imageView);
		imageView.setFitWidth(size);
		imageView.setFitHeight(size);
		super.getChildren().add(addSongToPlaylist);


		addSongToPlaylist.setStyle("-fx-background-color:transparent;");
		addSongToPlaylist.setOnAction(__ -> {
			new AddSongToPlaylist(getSong());
		});
	}

	private void createCompleteMenu(double size) {
		MenuButton menu = new MenuButton();

		menu.setStyle("-fx-font-size: 18px; -fx-background-color: transparent;");

		MenuItem addSongToPlaylist = new MenuItem("Add song to playlist.",
				crearIcono("/icons/plus-11969.png", size));
		MenuItem removeSongFromPlaylist = new MenuItem("Remove song from the playlist.",
				crearIcono("/icons/plus-11969.png", size));
		MenuItem changePositionSongFromPlaylist = new MenuItem("Change the position of the music in the list.",
				crearIcono("/icons/transfer-3818.png", size));

		menu.getItems().addAll(addSongToPlaylist, removeSongFromPlaylist, changePositionSongFromPlaylist);
		menu.setGraphic(crearIcono("/icons/burger-menu-black-lines-24618.png", size));

		super.getChildren().add(menu);

		removeSongFromPlaylist.getGraphic().setRotate(45);
		addSongToPlaylist.setOnAction(__ -> {
			new AddSongToPlaylist(getSong());
		});
		removeSongFromPlaylist.setOnAction(__ -> {
			PlaylistLogic.removeSongFromPlaylist(idPlaylist, song);
		});
	}

	private ImageView crearIcono(String url, double size) {
		try {
			Image img = new Image(getClass().getResourceAsStream(url), size,size, true, true);
			return new ImageView(img);
		} catch (Exception e) {
			System.err.println("No se pudo cargar el icono: " + url);
			return new ImageView();
		}
	}
}
