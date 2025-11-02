package musicfun;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import musicfun.ui.NavigationRoot;
import musicfun.ui.components.GridLayoutManager;
import musicfun.ui.navigation.NavigationManager;

public class App extends Application {
	private static Scene scene;
	private static NavigationManager navigationRoot;

	@Override
	public void init() {
		// Este se ejecuta junto con la clase Application o constructor, sirve para
		// validacion o carga de datos, no interfaz
	}

	@Override
	public void start(Stage stage) {
		// Aqui solamente elementos de la interfaz grafica
		GridLayoutManager gridLayoutManager = new GridLayoutManager();
        navigationRoot = new NavigationRoot(gridLayoutManager);

		scene = new Scene(gridLayoutManager, Double.MAX_VALUE, Double.MAX_VALUE);
		// The static field App.navigationRoot should be accessed in static way
		stage.setScene(scene);
		stage.setTitle("Music fun");
		stage.show();
	}

	@Override
	public void stop() {
		// Se ejecuta cuando se cierra la app.
	}

	public static NavigationManager getNavigation() {
		return navigationRoot;
	}

	public static void main(String[] args) {
		launch(args);
	}

}