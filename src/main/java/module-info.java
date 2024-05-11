module com.esercizi.javafx.pongjava {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.esercizi.javafx.pongjava to javafx.fxml;
    exports com.esercizi.javafx.pongjava;
}