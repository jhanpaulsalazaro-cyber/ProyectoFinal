package uniquindio.edu.co.proyectofinalfx.viewController;

import uniquindio.edu.co.proyectofinalfx.controller.CineController;

public class ModelFactoryController {

    private static ModelFactoryController instance;

    private CineController cineController;

    private ModelFactoryController() {
        cineController = new CineController();
    }

    public static ModelFactoryController getInstance() {
        if (instance == null) {
            instance = new ModelFactoryController();
        }

        return instance;
    }

    public CineController getCineController() {
        return cineController;
    }
}