
package uniquindio.edu.co.proyectofinalfx.viewController;

/**
 * Punto central de acceso para la comunicación
 * entre los controladores visuales y el modelo.
 *
 * La instancia del modelo debe ser única durante
 * la ejecución de la aplicación.
 */
public final class ModelFactoryController {

    private static ModelFactoryController instance;

    private Object cineController;

    private ModelFactoryController() {
    }

    public static synchronized ModelFactoryController getInstance() {

        if (instance == null) {
            instance = new ModelFactoryController();
        }

        return instance;
    }

    /**
     * Permite registrar el controlador de negocio
     * que ya existe en el proyecto.
     */
    public void setCineController(Object cineController) {

        if (cineController == null) {
            throw new IllegalArgumentException(
                    "El controlador de Cine no puede ser null."
            );
        }

        this.cineController = cineController;
    }

    public Object getCineController() {

        if (cineController == null) {
            throw new IllegalStateException(
                    "El CineController todavía no ha sido configurado."
            );
        }

        return cineController;
    }
}
