package musicfun.ui.views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import musicfun.model.MusicCollection;
import musicfun.service.SongService;
import musicfun.ui.components.listView.ListSong;
import musicfun.ui.model.navigation.SceneInfo;

public class MusicCollectionView extends SceneInfo<VBox> {
	private ImageView coverView;
	private Label name;
	private Label description;
	private ListSong songs;

	public MusicCollectionView() {
		super("MusicCollection", "", "musicCollection", false, false, new VBox());
	}

	@Override
	protected void initializeUI(VBox scene) {
		this.coverView = new ImageView();
		this.name = new Label("Name Music Collection");
		this.description = new Label();
		this.songs = new ListSong(null);

		GridPane grid = new GridPane(30, 20);
		GridPane.setConstraints(coverView, 0, 0, 1, 2);
		GridPane.setConstraints(name, 1, 0, 1, 1);
		GridPane.setConstraints(description, 1, 1, 1, 1);

		grid.getChildren().addAll(coverView, name, description);
		scene.getChildren().addAll(grid, songs);

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
			songs.setItems(musicCollection.getSongs());
		}
	}
}
