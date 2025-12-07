package musicfun.ui.component.listView;

import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.input.MouseEvent;
import musicfun.ui.layout.SizeConstraints;

public class ParamsCell {
	private Orientation orientation;
	private SizeConstraints sizeCell;
	private SizeConstraints sizeImage;
	private EventHandler<? super MouseEvent> event;
	private String idPlaylist;

	public ParamsCell(Orientation orientation, SizeConstraints sizeCell, SizeConstraints siceImage,
			EventHandler<? super MouseEvent> event, String idPlaylist) {
		this.orientation = orientation;
		this.sizeCell = sizeCell;
		this.sizeImage = siceImage;
		this.event = event;
		this.idPlaylist = idPlaylist;
	}

	public ParamsCell(Orientation orientation, SizeConstraints sizeCell, SizeConstraints sizeImage) {
		this(orientation, sizeCell, sizeImage, null,  null);
	}

	public ParamsCell(Orientation orientation, SizeConstraints sizeCell) {
		this(orientation, sizeCell, new SizeConstraints(40, 40));
	}

	public ParamsCell() {
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

	public String getIdPlayList() {
		return this.idPlaylist;
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

	public void setIdPlayList(String idPlaylist) {
		this.idPlaylist = idPlaylist;
	}
}
