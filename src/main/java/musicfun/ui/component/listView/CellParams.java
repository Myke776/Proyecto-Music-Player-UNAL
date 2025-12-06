package musicfun.ui.component.listView;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.input.MouseEvent;
import musicfun.ui.layout.SizeConstraints;

public class CellParams <TypeElement extends Object> {
	private Orientation orientation;
	private SizeConstraints sizeCell;
	private SizeConstraints sizeImage;
	private EventHandler<? super MouseEvent> event;
	private List<TypeElement> list;

	public CellParams(Orientation orientation, SizeConstraints sizeCell, SizeConstraints siceImage,
			EventHandler<? super MouseEvent> event, List<TypeElement> list) {
		this.orientation = orientation;
		this.sizeCell = sizeCell;
		this.sizeImage = siceImage;
		this.event = event;
		this.list = list;
	}

	public CellParams(Orientation orientation, SizeConstraints sizeCell, SizeConstraints sizeImage) {
		this(orientation, sizeCell, sizeImage, null, new ArrayList<>());
	}

	public CellParams(Orientation orientation, SizeConstraints sizeCell) {
		this(orientation, sizeCell, new SizeConstraints(40, 40));
	}

	public CellParams() {
		this(Orientation.HORIZONTAL, new SizeConstraints());
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public SizeConstraints getSizeCell() {
		return sizeCell;
	}

	public SizeConstraints getSizeImage() {
		return sizeImage;
	}

	public EventHandler<? super MouseEvent> getEvent() {
		return event;
	}

	public List<TypeElement> getList() {
		return list;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public void setSizeCell(SizeConstraints sizeCell) {
		this.sizeCell = sizeCell;
	}

	public void setSizeImage(SizeConstraints sizeImage) {
		this.sizeImage = sizeImage;
	}

	public void setEvent(EventHandler<? super MouseEvent> event) {
		this.event = event;
	}

	public void setList(List<TypeElement> list) {
		this.list = list;
	}
}
