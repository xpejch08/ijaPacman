module com.example.pacman {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.accessibility;


    opens com.example.pacman to javafx.fxml;
    exports com.example.pacman;
}