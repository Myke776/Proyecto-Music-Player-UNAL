package musicfun;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import musicfun.service.navigation.Navigation;
import musicfun.ui.NavigationRoot;

public class App extends Application {
	private static Scene scene;
	private static NavigationRoot navigationRoot;

	@Override
	public void init() {
		// Este se ejecuta junto con la clase Application o constructor, sirve para
		// validacion o carga de datos, no interfaz
	}

	@Override
	public void start(Stage stage) {
		// Aqui solamente elementos de la interfaz grafica
		navigationRoot = new NavigationRoot();

		scene = new Scene(navigationRoot.getRootScene(), Double.MAX_VALUE, Double.MAX_VALUE);
		// The static field App.navigationRoot should be accessed in static way
		stage.setScene(scene);
		stage.setTitle("Music fun");
		stage.show();
	}

	@Override
	public void stop() {
		// Se ejecuta cuando se cierra la app.
	}

	public static Navigation getNavigation() {
		return navigationRoot.getNavigation();
	}

	public static void main(String[] args) {
		launch(args);
	}

}