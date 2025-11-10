package musicfun.ui.components;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

public class GridLayoutManager extends GridPane {
	protected StackPane headerContainer;
	protected StackPane leftContainer;
	protected StackPane mainContainer;
	protected StackPane footerContainer;
	protected StackPane rightContainer;
	protected boolean expandHMain;
	protected boolean expandVMain;

	private RowConstraints headerRowConstraints;
	private RowConstraints mainRowConstraints;
	private RowConstraints footerRowConstraints;
	private ColumnConstraints leftColumnConstraints;
	private ColumnConstraints mainColumnConstraints;
	private ColumnConstraints rightColumnConstraints;

	public GridLayoutManager() {
		super();
		initializeContainers();
	}

	private void initializeContainers() {
		// Crear contenedores para cada área
		this.headerContainer = new StackPane();
		this.footerContainer = new StackPane();
		this.leftContainer = new StackPane();
		this.rightContainer = new StackPane();
		this.mainContainer = new StackPane();
		this.expandVMain = true;
		this.expandHMain = true;
		super.setAlignment(Pos.CENTER);

		// node/col/row/col-expan/row-expan
		super.setConstraints(this.headerContainer, 0, 0, 3, 1);
		super.setConstraints(this.leftContainer, 0, 1);
		super.setConstraints(this.mainContainer, 1, 1);
		super.setConstraints(this.rightContainer, 2, 1);
		super.setConstraints(this.footerContainer, 0, 2, 3, 1);

		// Configurar constraints de filas para que se expandan.
		// Ver si se expanden en todos los casos
		this.headerRowConstraints = new RowConstraints();
		this.leftColumnConstraints = new ColumnConstraints();
		this.mainRowConstraints = new RowConstraints();
		this.mainColumnConstraints = new ColumnConstraints();
		this.rightColumnConstraints = new ColumnConstraints();
		this.footerRowConstraints = new RowConstraints();

		super.getRowConstraints().addAll(headerRowConstraints, mainRowConstraints, footerRowConstraints);
		super.getColumnConstraints().addAll(leftColumnConstraints, mainColumnConstraints, rightColumnConstraints);
		super.getChildren().addAll(this.headerContainer, this.leftContainer, this.mainContainer, this.rightContainer,
				this.footerContainer);

		// Configurar estilos mínimos
		// this.headerContainer.setStyle("-fx-background-color: #8c00ffff;");
		// this.leftContainer.setStyle("-fx-background-color: #37502cff;");
		// this.mainContainer.setStyle("-fx-background-color: #aeff00ff;");
		// this.rightContainer.setStyle("-fx-background-color: #2c3e50;");
		// this.footerContainer.setStyle("-fx-background-color: #3e345eff;");

		// Mostrar y ocultar contenedores.
		toShow("main");
		toHide("header");
		toHide("right");
		toHide("left");
		toHide("footer");
	}

	public void toShow(String nameContainer) {
		switch (nameContainer.toLowerCase()) {
			case "header":
				this.headerContainer.setVisible(true);
				this.headerContainer.setManaged(true);
				setRowConstraints(headerRowConstraints, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
				break;
			case "footer":
				this.footerContainer.setVisible(true);
				this.footerContainer.setManaged(true);
				setRowConstraints(this.footerRowConstraints, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
				break;
			case "left":
				this.leftContainer.setVisible(true);
				this.leftContainer.setManaged(true);
				setColumnConstraints(leftColumnConstraints, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
				break;
			case "right":
				this.rightContainer.setVisible(true);
				this.rightContainer.setManaged(true);
				setColumnConstraints(rightColumnConstraints, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
				break;
			case "main":
				this.mainContainer.setVisible(true);
				this.mainContainer.setManaged(true);
				if(this.expandHMain) {
					setColumnConstraints(this.mainColumnConstraints, 700, 500);
					// setRowConstraints(this.mainRowConstraints, 400, 300);
				}else {
					setColumnConstraints(this.mainColumnConstraints, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
					// setRowConstraints(this.mainRowConstraints, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
				}
				if(this.expandVMain) {
					setRowConstraints(this.mainRowConstraints, 400, 300);
				}else {
					setRowConstraints(this.mainRowConstraints, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
				}
				break;
		}
	}

	public void toHide(String nameContainer) {
		switch (nameContainer.toLowerCase()) {
			case "header":
				this.headerContainer.setVisible(false);
				this.headerContainer.setManaged(false);
				setRowConstraints(this.headerRowConstraints, 0, 0);
				break;
			case "footer":
				this.footerContainer.setVisible(false);
				this.footerContainer.setManaged(false);
				setRowConstraints(this.footerRowConstraints, 0, 0);
				break;
			case "left":
				this.leftContainer.setVisible(false);
				this.leftContainer.setManaged(false);
				setColumnConstraints(this.leftColumnConstraints, 0, 0);
				break;
			case "right":
				this.rightContainer.setVisible(false);
				this.rightContainer.setManaged(false);
				setColumnConstraints(this.rightColumnConstraints, 0, 0);
				break;
			case "main":
				this.mainContainer.setVisible(false);
				this.mainContainer.setManaged(false);
				setColumnConstraints(this.mainColumnConstraints, 0, 0);
				setRowConstraints(this.mainRowConstraints, 0, 0);
				break;
		}
	}

	public StackPane getContainer(String nameContainer) {
		switch (nameContainer.toLowerCase()) {
			case "header":
				return this.headerContainer;
			case "footer":
				return this.footerContainer;
			case "left":
				return this.leftContainer;
			case "right":
				return this.rightContainer;
			case "main":
				return this.mainContainer;
			default:
				System.err.println("El container " + nameContainer + " no existe");
				return null;
		}
	}

	public void setContent(String nameContainer, boolean deletePreviousContent, Node... content) {
		switch (nameContainer.toLowerCase()) {
			case "header":
				setContentToContainer(headerContainer, "header", deletePreviousContent, content);
				break;
			case "footer":
				setContentToContainer(footerContainer, "footer", deletePreviousContent, content);
				break;
			case "left":
				setContentToContainer(leftContainer, "left", deletePreviousContent, content);
				break;
			case "right":
				setContentToContainer(rightContainer, "right", deletePreviousContent, content);
				break;
			case "main":
				setContentToContainer(mainContainer, "main", deletePreviousContent, content);
				break;
			default:
				System.err.println("El container " + nameContainer + " no existe");
				break;
		}
	}

	public void setExpand(boolean v, boolean h) {
		this.expandHMain = h;
		this.expandVMain = v;
	}
	public void setExpand(boolean val) {
		this.setExpand(val, val);
	}

	private void setContentToContainer(StackPane container, String nameContainer, boolean deletePreviousContent,
			Node... content) {
		if (content != null) {
			if (deletePreviousContent) {
				container.getChildren().clear();
			}
			container.getChildren().addAll(content);
			toShow(nameContainer);
		}
	}

	private void setColumnConstraints(ColumnConstraints columnConstraints, double prefWidth, double minWidth) {
		columnConstraints.setPrefWidth(prefWidth);
		columnConstraints.setMinWidth(minWidth);
		if (prefWidth > 0 || minWidth > 0) {
			columnConstraints.setHgrow(Priority.ALWAYS);
		} else {
			columnConstraints.setHgrow(Priority.NEVER);
		}
	}

	private void setRowConstraints(RowConstraints rowConstraints, double prefHeight, double minHeight) {
		rowConstraints.setPrefHeight(prefHeight);
		rowConstraints.setMinHeight(minHeight);

		if (prefHeight > 0 || minHeight > 0) {
			rowConstraints.setVgrow(Priority.ALWAYS);
		} else {
			rowConstraints.setVgrow(Priority.NEVER);
		}
	}
}
