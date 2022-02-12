module hu.petrik.muzeumfrontendjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens hu.petrik.muzeumfrontendjavafx to javafx.fxml;
    exports hu.petrik.muzeumfrontendjavafx;
}