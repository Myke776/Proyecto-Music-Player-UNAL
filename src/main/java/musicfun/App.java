package musicfun;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import musicfun.model.NavigationManager;
import musicfun.ui.NavigationRoot;

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
		navigationRoot = new NavigationRoot();

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

	public static void navigateTo(String routeName) {
		if (navigationRoot != null) {
			navigationRoot.navigateTo(routeName);
		}
	}

	public static void showArea(String area) {
		if (navigationRoot != null) {
			navigationRoot.toShow(area);
		}
	}

	public static void hideArea(String area) {
		if (navigationRoot != null) {
			navigationRoot.toHide(area);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}