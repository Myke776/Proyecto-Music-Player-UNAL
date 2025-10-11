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

		// Configurar constraints de filas para que se expandan. Ver si se expanden en todos los casos
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
        
		this.getRowConstraints().addAll(headerRow, mainRow, footerRow);
		this.getColumnConstraints().addAll(leftColumn, mainColumn, rightColumn);
		this.getChildren().addAll(headerContainer, leftContainer, mainContainer, rightContainer, footerContainer);
        
        // Configurar estilos mínimos
        headerContainer.setStyle("-fx-background-color: #8c00ffff;");
        footerContainer.setStyle("-fx-background-color: #3e345eff;");
        leftContainer.setStyle("-fx-background-color: #37502cff;");
        rightContainer.setStyle("-fx-background-color: #2c3e50;");
        mainContainer.setStyle("-fx-background-color: #aeff00ff; width: 100%; min-height: 50px");
    }

	public void registerScene(SceneInfo sceneInfo) {
		availableScenes.add(sceneInfo);
	}

	public void navigateTo(String routeName) {
		SceneInfo targetScene = findSceneByRoute(routeName);
		if (targetScene != null) {
			this.mainContainer.getChildren().clear();
			this.mainContainer.getChildren().add(targetScene.getSceneLoader());
			currentRoute = routeName;
		}
	}

	public List<SceneInfo> getAvailableScenes() {
		return new ArrayList<>(availableScenes);
	}

	public String getCurrentRoute() {
		return currentRoute;
	}

	private SceneInfo findSceneByRoute(String routeName) {
		return availableScenes.stream()
				.filter(scene -> scene.getRouteName().equals(routeName))
				.findFirst()
				.orElse(null);
	}
}