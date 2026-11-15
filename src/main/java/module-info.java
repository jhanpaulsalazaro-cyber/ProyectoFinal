module uniquindio.edu.co.proyectofinalfx {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports uniquindio.edu.co.proyectofinalfx;
    exports uniquindio.edu.co.proyectofinalfx.model;
    exports uniquindio.edu.co.proyectofinalfx.controller;
    exports uniquindio.edu.co.proyectofinalfx.viewController;

    opens uniquindio.edu.co.proyectofinalfx.viewController
            to javafx.fxml;
}