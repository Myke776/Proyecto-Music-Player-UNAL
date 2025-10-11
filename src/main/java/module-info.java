module musicfun {
    requires javafx.controls;
    requires javafx.fxml;

    opens musicfun to javafx.fxml;
    exports musicfun;
}
