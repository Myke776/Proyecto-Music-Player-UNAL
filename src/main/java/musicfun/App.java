package musicfun;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import musicfun.service.navigation.Navigation;
import musicfun.service.navigation.NavigationManager;
import musicfun.ui.NavigationRoot;

public class App extends Application {
	private static Scene scene;
	private static NavigationManager navigationRoot;
	public static Navigation navigation;

	@Override
	public void init() {
		// Este se ejecuta junto con la clase Application o constructor, sirve para
		// validacion o carga de datos, no interfaz
	}

	@Override
	public void start(Stage stage) {
		// Aqui solamente elementos de la interfaz grafica
		navigationRoot = new NavigationRoot();
		navigation = new Navigation(navigationRoot);

		String cssPath = getClass().getResource("/styles/label.css").toExternalForm();
		scene = new Scene(navigationRoot, Double.MAX_VALUE, Double.MAX_VALUE);
		scene.getStylesheets().add(cssPath);
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