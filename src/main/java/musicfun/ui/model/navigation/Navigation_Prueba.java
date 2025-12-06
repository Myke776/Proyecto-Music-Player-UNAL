package musicfun.ui.model.navigation;

import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

public abstract class Navigation_Prueba {
	protected GridPane rootScene;
	private List<SceneInfo<?>> availableScenes;
	private String currentRoute;

	private StackPane headerContainer;
	private StackPane leftContainer;
	private StackPane mainContainer;
	private StackPane footerContainer;
	private StackPane rightContainer;

	private RowConstraints headerRowConstraints;
	private RowConstraints mainRowConstraints;
	private RowConstraints footerRowConstraints;
	private ColumnConstraints leftColumnConstraints;
	private ColumnConstraints mainColumnConstraints;
	private ColumnConstraints rightColumnConstraints;

	public Navigation_Prueba() {
		this.rootScene = new GridPane();
		this.availableScenes = new ArrayList<>();
		initializeContainers();
	}

	public Navigation_Prueba(String currentRoute, SceneInfo<?>... scenes) {
		this.rootScene = new GridPane();
		this.availableScenes = List.of(scenes);
		this.currentRoute = currentRoute;
		initializeContainers();
		navigateTo(currentRoute);
	}

	private void initializeContainers() {
		// Crear contenedores para cada área
		this.headerContainer = new StackPane();
		this.footerContainer = new StackPane();
		this.leftContainer = new StackPane();
		this.rightContainer = new StackPane();
		this.mainContainer = new StackPane();

		// node/col/row/col-expan/row-expan
		GridPane.setConstraints(this.headerContainer, 0, 0, 3, 1);
		GridPane.setConstraints(this.leftContainer, 0, 1);
		GridPane.setConstraints(this.mainContainer, 1, 1);
		GridPane.setConstraints(this.rightContainer, 2, 1);
		GridPane.setConstraints(this.footerContainer, 0, 2, 3, 1);

		// Configurar constraints de filas para que se expandan.
		// Ver si se expanden en todos los casos
		this.headerRowConstraints = new RowConstraints();
		this.leftColumnConstraints = new ColumnConstraints();
		this.mainRowConstraints = new RowConstraints();
		this.mainColumnConstraints = new ColumnConstraints();
		this.rightColumnConstraints = new ColumnConstraints();
		this.footerRowConstraints = new RowConstraints();

		this.rootScene.getRowConstraints().addAll(headerRowConstraints, mainRowConstraints, footerRowConstraints);
		this.rootScene.getColumnConstraints().addAll(leftColumnConstraints, mainColumnConstraints, rightColumnConstraints);
		this.rootScene.getChildren().addAll(this.headerContainer, this.leftContainer, this.mainContainer, this.rightContainer,
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

	public void registerScene(SceneInfo<Region> sceneInfo) {
		availableScenes.add(sceneInfo);
	}

	public void navigateTo(String routeName) {
		SceneInfo<?> targetScene = findSceneByRoute(routeName);
		if (targetScene != null) {
			setContentToContainer(this.mainContainer, "main", true, targetScene.getSceneLoader());
			this.currentRoute = routeName;
		}
	}

	public List<SceneInfo<?>> getAvailableScenes() {
		return new ArrayList<>(availableScenes);
	}

	public String getCurrentRoute() {
		return currentRoute;
	}

	public void toShow(String nameContainer) {
		switch (nameContainer.toLowerCase()) {
			case "header":
				this.headerContainer.setVisible(true);
				this.headerContainer.setManaged(true);
				setHeightConstraints(headerRowConstraints, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
				break;
			case "footer":
				this.footerContainer.setVisible(true);
				this.footerContainer.setManaged(true);
				setHeightConstraints(this.footerRowConstraints, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
				break;
			case "left":
				this.leftContainer.setVisible(true);
				this.leftContainer.setManaged(true);
				setWidthConstraints(leftColumnConstraints, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
				break;
			case "right":
				this.rightContainer.setVisible(true);
				this.rightContainer.setManaged(true);
				setWidthConstraints(rightColumnConstraints, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
				break;
			case "main":
				this.mainContainer.setVisible(true);
				this.mainContainer.setManaged(true);
				setWidthConstraints(this.mainColumnConstraints, 700, 500);
				setHeightConstraints(this.mainRowConstraints, 400, 300);
				break;
		}
	}

	public void toHide(String nameContainer) {
		switch (nameContainer.toLowerCase()) {
			case "header":
				this.headerContainer.setVisible(false);
				this.headerContainer.setManaged(false);
				setHeightConstraints(this.headerRowConstraints, 0, 0);
				break;
			case "footer":
				this.footerContainer.setVisible(false);
				this.footerContainer.setManaged(false);
				setHeightConstraints(this.footerRowConstraints, 0, 0);
				break;
			case "left":
				this.leftContainer.setVisible(false);
				this.leftContainer.setManaged(false);
				setWidthConstraints(this.leftColumnConstraints, 0, 0);
				break;
			case "right":
				this.rightContainer.setVisible(false);
				this.rightContainer.setManaged(false);
				setWidthConstraints(this.rightColumnConstraints, 0, 0);
				break;
			case "main":
				this.mainContainer.setVisible(false);
				this.mainContainer.setManaged(false);
				setWidthConstraints(this.mainColumnConstraints, 0, 0);
				setHeightConstraints(this.mainRowConstraints, 0, 0);
				break;
		}
	}

	public StackPane getHeaderContainer() {
		return headerContainer;
	}

	public StackPane getFooterContainer() {
		return footerContainer;
	}

	public StackPane getLeftContainer() {
		return leftContainer;
	}

	public StackPane getRightContainer() {
		return rightContainer;
	}

	public StackPane getMainContainer() {
		return mainContainer;
	}

	public void setContent(String nameContainer, boolean deletePreviousContent, Node... content) {
		switch (nameContainer) {
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
				System.err.println("El container " +  nameContainer +" no existe");
				break;
		}
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

	private void setWidthConstraints(ColumnConstraints columnConstraints, double prefWidth, double minWidth) {
		columnConstraints.setPrefWidth(prefWidth);
		columnConstraints.setMinWidth(minWidth);
		if (prefWidth > 0 || minWidth > 0) {
			columnConstraints.setHgrow(Priority.ALWAYS);
		} else {
			columnConstraints.setHgrow(Priority.NEVER);
		}
	}

	private void setHeightConstraints(RowConstraints rowConstraints, double prefHeight, double minHeight) {
		rowConstraints.setPrefHeight(prefHeight);
		rowConstraints.setMinHeight(minHeight);

		if (prefHeight > 0 || minHeight > 0) {
			rowConstraints.setVgrow(Priority.ALWAYS);
		} else {
			rowConstraints.setVgrow(Priority.NEVER);
		}
	}

	private SceneInfo<?> findSceneByRoute(String routeName) {
		return availableScenes.stream()
				.filter(scene -> scene.getRouteName().equals(routeName))
				.findFirst()
				.orElse(null);
	}

	public Navigation_Prueba getNavigation() {
		return this;
	}
}