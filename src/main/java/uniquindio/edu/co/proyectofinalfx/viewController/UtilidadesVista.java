package uniquindio.edu.co.proyectofinalfx.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import uniquindio.edu.co.proyectofinalfx.model.Asiento;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

/** Métodos de apoyo compartidos por las vistas de Inicio, Operador y Administrador. */
public final class UtilidadesVista {

    public static final NumberFormat MONEDA = NumberFormat.getCurrencyInstance(Locale.of("es", "CO"));
    public static final DateTimeFormatter FECHA_HORA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private UtilidadesVista() {
    }

    /** Reemplaza la escena actual por otra vista FXML, aplicando la hoja de estilos de CINEMAUQ. */
    public static void cambiarEscena(Node origen, String rutaFxml, String titulo) {
        try {
            Parent root = new FXMLLoader(UtilidadesVista.class.getResource(rutaFxml)).load();
            Stage stage = (Stage) origen.getScene().getWindow();
            Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
            scene.getStylesheets().add(UtilidadesVista.class.getResource("/cinemauq.css").toExternalForm());
            stage.setTitle(titulo);
            stage.setScene(scene);
        } catch (IOException e) {
            aviso("No se pudo abrir la vista " + rutaFxml + ": " + e.getMessage());
        }
    }

    /** Muestra solo la sección elegida del menú lateral y marca su botón como activo. */
    public static void mostrarSeccion(ScrollPane visible, Button botonActivo,
                                      ScrollPane[] secciones, Button[] botones) {
        for (ScrollPane seccion : secciones) {
            boolean mostrar = seccion == visible;
            seccion.setVisible(mostrar);
            seccion.setManaged(mostrar);
        }
        for (Button boton : botones) {
            boton.getStyleClass().remove("nav-active");
        }
        botonActivo.getStyleClass().add("nav-active");
    }

    /** Define el texto que muestra un ComboBox para los objetos del modelo. */
    public static <T> void mostrarCon(ComboBox<T> combo, Function<T, String> texto) {
        combo.setConverter(new StringConverter<>() {
            @Override public String toString(T objeto) { return objeto == null ? "" : texto.apply(objeto); }
            @Override public T fromString(String s) { return null; }
        });
    }

    /** Reemplaza los elementos de un ComboBox conservando la selección actual. */
    public static <T> void recargar(ComboBox<T> combo, List<T> elementos) {
        T actual = combo.getValue();
        combo.setItems(FXCollections.observableArrayList(elementos));
        if (elementos.contains(actual)) combo.setValue(actual);
    }

    public static SimpleStringProperty texto(String valor) {
        return new SimpleStringProperty(valor);
    }

    public static String nombreAsiento(Asiento asiento) {
        return asiento == null ? "" : asiento.getFila() + asiento.getNumero();
    }

    public static void aviso(String texto) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("CINEMAUQ");
        a.setHeaderText(null);
        a.setContentText(texto);
        a.showAndWait();
    }
}
