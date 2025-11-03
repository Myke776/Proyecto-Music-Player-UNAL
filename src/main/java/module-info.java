module musicfun {
    requires javafx.controls;
    requires javafx.fxml;
	requires jaudiotagger;

    opens musicfun to javafx.fxml;
    exports musicfun;
}
