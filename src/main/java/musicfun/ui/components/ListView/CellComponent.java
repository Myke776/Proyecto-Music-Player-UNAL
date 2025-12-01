package musicfun.ui.components.ListView;

import java.util.List;
import java.util.function.Consumer;

import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import musicfun.model.SongModel;
import musicfun.service.SongService;
import musicfun.ui.components.ContainerImageTitleArtist;

public class CellComponent <Element extends SongModel> extends ListCell<Element> {
	protected HBox contentMain = new HBox(10);
	protected ContainerImageTitleArtist containerTitleAndArtist = new ContainerImageTitleArtist();
	protected Text secondaryText = new Text();

	public CellComponent(List<Element> parentList, Consumer<List<Element>> fun) {
		initializeCell();

		setOnMouseClicked(event -> {
			if (!isEmpty() && getItem() != null) {
				fun.accept(parentList);
			}
		});
	}

	public CellComponent() {
		initializeCell();
	}

	@Override
	protected void updateItem(Element element, boolean empty) {
		super.updateItem(element, empty);
		if (empty || element == null) {
			super.setGraphic(null);
		} else {
			this.updateCellContent(element);
			super.setGraphic(this.contentMain);
		}
	}

	protected void initializeCell() {
		this.contentMain.getChildren().addAll(this.containerTitleAndArtist, this.secondaryText);
		this.secondaryText.getStyleClass().add("text-bold");
		HBox.setHgrow(containerTitleAndArtist, Priority.ALWAYS);
		HBox.setHgrow(secondaryText, Priority.ALWAYS);
	}

	protected void updateCellContent(Element element) {
        this.containerTitleAndArtist.setTitle(element.getTitle());
        this.containerTitleAndArtist.setArtist(element.getArtist());
        this.containerTitleAndArtist.setImage(SongService.getImage(element.getCover()));
        // this.secondaryText.setText(element.getFormattedDuration()); // agreagr para que se pueda ver diferentes parametros en paralelo.
    }
}
