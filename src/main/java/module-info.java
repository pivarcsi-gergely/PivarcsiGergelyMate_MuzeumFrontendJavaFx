module hu.petrik.muzeumfrontendjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens hu.petrik.muzeumfrontendjavafx to javafx.fxml, com.google.gson;
    exports hu.petrik.muzeumfrontendjavafx;
}