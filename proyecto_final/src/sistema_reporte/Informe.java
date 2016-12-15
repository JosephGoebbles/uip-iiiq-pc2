package sistema_reporte;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by
 * proyecto_final
 * Clase encargada del manejo de reporte express
 */
public class Informe  implements Initializable {
    @FXML
    private Button bt_salir;
    @FXML
    private ListView<String> lista_informe= new ListView<>();
    @FXML
    public void eventos(ActionEvent e ) throws IOException {
        if (e.getSource() == bt_salir){
            System.out.println("Saliendo al inicio.");
            Stage stage = (Stage) bt_salir.getScene().getWindow();;
            Parent root =  FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    public void carga_reporte() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Reportes.csv"));
        String sCurrentLine;
        ObservableList<String> datos_reportes = FXCollections.observableArrayList();
        String[] datos;
        while ((sCurrentLine = br.readLine()) != null) {
            // separamos los datos
            datos = sCurrentLine.split(",");
            // asignamos los datos
            String nombre= datos [0];
            String categoria = datos[1];
            String calificacion = datos[2];
            String comentario = datos[3].replace(';',' ');
            datos_reportes.add("Reportado por "+nombre+" Categoria: "+
                    categoria+ " Calificacion: "+ calificacion+" Comentarios: "+ comentario
            );
        }
        lista_informe.setItems(datos_reportes);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            carga_reporte();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
