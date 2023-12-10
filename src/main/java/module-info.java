module com.example.hanukaproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hanukaproject to javafx.fxml;
    exports com.example.hanukaproject;
    exports com.example.hanukaproject.Entities;
    exports com.example.hanukaproject.Game;
    exports com.example.hanukaproject.Weapons;

}