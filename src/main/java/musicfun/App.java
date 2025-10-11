package musicfun;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import musicfun.ui.NavigationManager;

import java.io.IOException;

public class App extends Application {
    private static Scene scene;

	@Override
	public void init() {
		//Este se ejecuta junto con la clase Application o constructor, sirve para validacion o carga de datos
	}

    @Override
    public void start(Stage stage) {
		//Aqui solamente elementos de la interfaz grafica
		NavigationManager navigationRoot = new NavigationManager();

		String cssPath = getClass().getResource("/styles/label.css").toExternalForm();
        scene = new Scene(navigationRoot, 640, 480);
		scene.getStylesheets().add(cssPath);

        stage.setScene(scene);
		stage.setTitle("Music fun");
        stage.show();
    }

	@Override
	public void stop() {
		//Se ejecuta cuando se cierra la app.
	}

    static void setRoot(String fxml) throws IOException {
		//cambiar para rutas.
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

}