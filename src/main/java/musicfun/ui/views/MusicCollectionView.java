package musicfun.ui.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import musicfun.model.ArtistModel;
import musicfun.model.MusicCollection;
import musicfun.model.SongModel;
import musicfun.service.SongService;
import musicfun.ui.components.listView.ListSong;
import musicfun.ui.model.navigation.SceneInfo;

public class MusicCollectionView extends SceneInfo<VBox> {
	private ImageView coverView;
	private Label name;
	private Label description;
	private ObservableList<SongModel> listSongs;
	private ObservableList<String> listAlbums;

	private VBox containerAlbums;


	public MusicCollectionView() {
		super("MusicCollection", "", "musicCollection", false, false, new VBox());
	}

	@Override
	protected void initializeUI(VBox scene) {
		this.listSongs = FXCollections.observableArrayList();
		this.listAlbums = FXCollections.observableArrayList();

		this.coverView = new ImageView();
		this.name = new Label("Name Music Collection");
		this.description = new Label();

		ListSong songs = new ListSong(listSongs);
		ListView<String> albums = new ListView<>(listAlbums);
		albums.setOrientation(Orientation.HORIZONTAL);

		Label labelAlbum = new Label("Albums");
		this.containerAlbums = new VBox(labelAlbum, albums);

		GridPane grid = new GridPane(30, 20);
		GridPane.setConstraints(coverView, 0, 0, 1, 2);
		GridPane.setConstraints(name, 1, 0, 1, 1);
		GridPane.setConstraints(description, 1, 1, 1, 1);

		grid.getChildren().addAll(coverView, name, description);
		scene.getChildren().addAll(grid, songs, containerAlbums);

		coverView.setFitWidth(300);
		coverView.setFitHeight(300);
		scene.setPadding(new Insets(20));
	}

	@Override
	public <TypeParam> void setParams(TypeParam param) {
		if(param instanceof MusicCollection) {
			MusicCollection musicCollection = (MusicCollection)param;
			coverView.setImage(SongService.getImage(musicCollection.getCover()));
			name.setText(musicCollection.getClass().getSimpleName().replaceAll("Model", "") + ": " + musicCollection.getName());
			description.setText(musicCollection.getDescription());
			listSongs.setAll(musicCollection.getSongs());

			if(musicCollection instanceof ArtistModel) {
				ArtistModel artist = (ArtistModel) musicCollection;
				listAlbums.setAll(artist.getAlbums());
				containerAlbums.setVisible(true);
			}else{
				containerAlbums.setVisible(false);
			}
		}
	}
}
