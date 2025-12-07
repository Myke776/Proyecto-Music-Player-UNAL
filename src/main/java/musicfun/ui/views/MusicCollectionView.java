package musicfun.ui.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import musicfun.model.AlbumModel;
import musicfun.model.ArtistModel;
import musicfun.model.MusicCollectionModel;
import musicfun.model.PlaylistModel;
import musicfun.service.SongService;
import musicfun.ui.component.listView.ListMusicCollection;
import musicfun.ui.component.listView.ListSong;
import musicfun.ui.component.listView.ParamsCell;
import musicfun.ui.model.navigation.SceneInfo;

public class MusicCollectionView extends SceneInfo<ScrollPane> {
	private ImageView coverView;
	private Label name;
	private Label description;
	private ObservableList<AlbumModel> listAlbums;
	private ParamsCell cellParams;
	private StackPane containerSongs;

	private VBox containerAlbums;

	public MusicCollectionView() {
		super("MusicCollection", "", "musicCollection", false, false, new ScrollPane());
	}

	@Override
	protected void initializeUI(ScrollPane scene_p) {
		VBox scene = new VBox();
		this.listAlbums = FXCollections.observableArrayList();

		this.coverView = new ImageView();
		this.name = new Label("Name Music Collection");
		this.description = new Label();
		this.cellParams = new ParamsCell();
		this.containerSongs = new StackPane();

		ListMusicCollection<AlbumModel> albums = new ListMusicCollection<>(listAlbums);
		albums.setOrientation(Orientation.HORIZONTAL);

		Label labelAlbum = new Label("Albums");
		this.containerAlbums = new VBox(labelAlbum, albums);

		GridPane grid = new GridPane(30, 20);
		GridPane.setConstraints(coverView, 0, 0, 1, 2);
		GridPane.setConstraints(name, 1, 0, 1, 1);
		GridPane.setConstraints(description, 1, 1, 1, 1);

		grid.getChildren().addAll(coverView, name, description);
		scene.getChildren().addAll(grid, containerSongs, containerAlbums);

		coverView.setFitWidth(300);
		coverView.setFitHeight(300);
		scene.setPadding(new Insets(20));
		scene_p.setContent(scene);
		scene_p.setFitToWidth(true);
	}

	@Override
	public <TypeParam> void setParams(TypeParam param) {
		if (param instanceof MusicCollectionModel) {
			MusicCollectionModel musicCollection = (MusicCollectionModel) param;
			coverView.setImage(SongService.getImage(musicCollection.getCover()));
			name.setText(musicCollection.getClass().getSimpleName().replaceAll("Model", "") + ": "
					+ musicCollection.getName());
			description.setText(musicCollection.getDescription());

			if (musicCollection instanceof ArtistModel) {
				ArtistModel artist = (ArtistModel) musicCollection;
				listAlbums.setAll(artist.getAlbums());
				containerAlbums.setVisible(true);
				containerAlbums.setMaxHeight(120);
			} else {
				containerAlbums.setVisible(false);
				containerAlbums.setMaxHeight(0);
			}

			cellParams.setIdPlayList(null);
			if (musicCollection instanceof PlaylistModel) {
				PlaylistModel playlist = (PlaylistModel) musicCollection;
				cellParams.setIdPlayList(playlist.getId());
			}

			int sizeList = containerSongs.getChildren().size();
			boolean isSame = false;

			if (sizeList > 0) {
				ListSong songsC = (ListSong) containerSongs.getChildren().getFirst();
				isSame = musicCollection.getSongs().equals(songsC.getItems());
			}

			if (!isSame) {
				ListSong songs = new ListSong(musicCollection.getSongs(), cellParams);
				containerSongs.getChildren().clear();
				containerSongs.getChildren().add(songs);
			}
		}
	}
}
