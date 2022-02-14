module hu.petrik.muzeumfrontendjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens hu.petrik.muzeumfrontendjavafx to javafx.fxml, com.google.gson;
    opens hu.petrik.muzeumfrontendjavafx.api to com.google.gson;
    opens hu.petrik.muzeumfrontendjavafx.classes to com.google.gson;
    exports hu.petrik.muzeumfrontendjavafx.classes;
    exports hu.petrik.muzeumfrontendjavafx.api;
    exports hu.petrik.muzeumfrontendjavafx;
    exports hu.petrik.muzeumfrontendjavafx.controllers;
    opens hu.petrik.muzeumfrontendjavafx.controllers to com.google.gson, javafx.fxml;
}