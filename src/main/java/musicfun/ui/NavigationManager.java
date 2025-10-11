package musicfun.ui;

import musicfun.model.SceneInfo;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

public class NavigationManager extends GridPane {
	private List<SceneInfo> availableScenes;

	private String currentRoute;
	private StackPane headerContainer;
	private StackPane footerContainer;
	private StackPane leftContainer;
	private StackPane rightContainer;
	private StackPane mainContainer;

	public NavigationManager() {
		this.availableScenes = new ArrayList<>();
		initializeContainers();
	}

	public NavigationManager(List<SceneInfo> scenes, String currentRoute) {
		this.availableScenes = scenes;
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
		super.setConstraints(this.headerContainer, 0, 0, 3, 1);
		super.setConstraints(this.leftContainer, 0, 1);
		super.setConstraints(this.mainContainer, 1, 1);
		super.setConstraints(this.rightContainer, 2, 1);
		super.setConstraints(this.footerContainer, 0, 2, 3, 1);

		// Configurar constraints de filas para que se expandan. Ver si se expanden en
		// todos los casos
		RowConstraints headerRow = new RowConstraints();
		headerRow.setPrefHeight(60);
		headerRow.setMinHeight(60);
		headerRow.setVgrow(Priority.NEVER);

		ColumnConstraints leftColumn = new ColumnConstraints();
		leftColumn.setPrefWidth(60);
		leftColumn.setMinWidth(50);
		leftColumn.setHgrow(Priority.NEVER);

		RowConstraints mainRow = new RowConstraints();
		ColumnConstraints mainColumn = new ColumnConstraints();
		mainRow.setVgrow(Priority.ALWAYS);
		mainColumn.setHgrow(Priority.ALWAYS);
		mainRow.setPrefHeight(400);
		mainRow.setMinHeight(300);
		mainColumn.setPrefWidth(700);
		mainColumn.setMinWidth(500);

		ColumnConstraints rightColumn = new ColumnConstraints();
		rightColumn.setPrefWidth(60);
		rightColumn.setMinWidth(50);
		rightColumn.setHgrow(Priority.NEVER);

		RowConstraints footerRow = new RowConstraints();
		footerRow.setPrefHeight(100);
		footerRow.setMinHeight(60);
		footerRow.setVgrow(Priority.NEVER);

		super.getRowConstraints().addAll(headerRow, mainRow, footerRow);
		super.getColumnConstraints().addAll(leftColumn, mainColumn, rightColumn);
		super.getChildren().addAll(this.headerContainer, this.leftContainer, this.mainContainer, this.rightContainer, this.footerContainer);
		// super.setHgrow(this, Priority.ALWAYS);
		// super.setVgrow(this, Priority.ALWAYS);

		// Configurar estilos mínimos
		this.headerContainer.setStyle("-fx-background-color: #8c00ffff;");
		this.leftContainer.setStyle("-fx-background-color: #37502cff;");
		this.mainContainer.setStyle("-fx-background-color: #aeff00ff;");
		this.rightContainer.setStyle("-fx-background-color: #2c3e50;");
		this.footerContainer.setStyle("-fx-background-color: #3e345eff;");
	}

	public void registerScene(SceneInfo sceneInfo) {
		availableScenes.add(sceneInfo);
	}

	public void navigateTo(String routeName) {
		SceneInfo targetScene = findSceneByRoute(routeName);
		if (targetScene != null) {
			this.mainContainer.getChildren().clear();
			this.mainContainer.getChildren().add(targetScene.getSceneLoader());
			this.currentRoute = routeName;
		}
	}

	public List<SceneInfo> getAvailableScenes() {
		return new ArrayList<>(availableScenes);
	}

	public String getCurrentRoute() {
		return currentRoute;
	}

	public void toShow(String container) {
		switch (container.toLowerCase()) {
			case "header":
				this.headerContainer.setVisible(true);
				this.headerContainer.setManaged(true);
				break;
			case "footer":
				this.footerContainer.setVisible(true);
				this.footerContainer.setManaged(true);
				break;
			case "left":
				this.leftContainer.setVisible(true);
				this.leftContainer.setManaged(true);
				break;
			case "right":
				this.rightContainer.setVisible(true);
				this.rightContainer.setManaged(true);
				break;
			case "main":
				this.mainContainer.setVisible(true);
				this.mainContainer.setManaged(true);
				break;
		}
	}

	public void toHide(String container) {
		switch (container.toLowerCase()) {
			case "header":
				this.headerContainer.setVisible(false);
				this.headerContainer.setManaged(false);
				break;
			case "footer":
				this.footerContainer.setVisible(false);
				this.footerContainer.setManaged(false);
				break;
			case "left":
				this.leftContainer.setVisible(false);
				this.leftContainer.setManaged(false);
				break;
			case "right":
				this.rightContainer.setVisible(false);
				this.rightContainer.setManaged(false);
				break;
			case "main":
				this.mainContainer.setVisible(false);
				this.mainContainer.setManaged(false);
				break;
		}
	}

	// public void toToggleSH(String container) {
	// 	switch (container.toLowerCase()) {
	// 		case "header":
	// 			this.headerContainer.setVisible();
	// 			this.headerContainer.setManaged(false);
	// 			break;
	// 		case "footer":
	// 			footerContainer.setVisible(false);
	// 			footerContainer.setManaged(false);
	// 			break;
	// 		case "left":
	// 			leftContainer.setVisible(false);
	// 			leftContainer.setManaged(false);
	// 			break;
	// 		case "right":
	// 			rightContainer.setVisible(false);
	// 			rightContainer.setManaged(false);
	// 			break;
	// 		case "main":
	// 			mainContainer.setVisible(false);
	// 			mainContainer.setManaged(false);
	// 			break;
	// 	}
	// }

	public StackPane getHeaderContainer() { return headerContainer; }
    public StackPane getFooterContainer() { return footerContainer; }
    public StackPane getLeftContainer() { return leftContainer; }
    public StackPane getRightContainer() { return rightContainer; }
    public StackPane getMainContainer() { return mainContainer; }

	private SceneInfo findSceneByRoute(String routeName) {
		return availableScenes.stream()
				.filter(scene -> scene.getRouteName().equals(routeName))
				.findFirst()
				.orElse(null);
	}
}