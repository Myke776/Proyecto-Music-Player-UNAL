module musicfun {
    requires javafx.controls;
    requires javafx.fxml;
	requires jaudiotagger;
	requires javafx.media;

    opens musicfun to javafx.fxml;
    exports musicfun;
}
