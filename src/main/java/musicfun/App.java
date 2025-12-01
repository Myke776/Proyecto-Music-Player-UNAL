package musicfun;


import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import musicfun.logic.LibraryLogic;
import musicfun.logic.MusicPlayerLogic;
import musicfun.model.PlayerStateModel;
import musicfun.ui.NavigationRoot;
import musicfun.ui.components.GridLayoutManager;
import musicfun.ui.navigation.NavigationManager;

public class App extends Application {
	private static Scene scene;
	private static NavigationManager navigationRoot;
	private static MusicPlayerLogic musicPlayerLogic;

	@Override
	public void init() {
		// Este se ejecuta junto con la clase Application o constructor, sirve para
		// validacion o carga de datos, no interfaz

		musicPlayerLogic = new MusicPlayerLogic(new PlayerStateModel());
		LibraryLogic.setFolders(List.of(
			"C:\\Users\\Oscar\\Downloads"
		// , "C:\\Users\\Oscar\\Documents"
		));
		LibraryLogic.scanFolder();
	}

	@Override
	public void start(Stage stage) {
		// Aqui solamente elementos de la interfaz grafica
		GridLayoutManager gridLayoutManager = new GridLayoutManager();
        navigationRoot = new NavigationRoot(gridLayoutManager);

		scene = new Scene(gridLayoutManager, Double.MAX_VALUE, Double.MAX_VALUE);
		stage.setScene(scene);
		stage.setTitle("Music fun");
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/musical-notes-10191.png")));
		stage.show();
		stage.centerOnScreen();
	}

	@Override
	public void stop() {
		// Se ejecuta cuando se cierra la app.
	}

	public static NavigationManager getNavigation() {
		return navigationRoot;
	}

	public static MusicPlayerLogic getMusicPlayer() {
		return musicPlayerLogic;
	}

	public static void main(String[] args) {
		launch(args);
	}

}