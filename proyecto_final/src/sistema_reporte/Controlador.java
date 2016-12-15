package sistema_reporte;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;

/**
 * Created by
 * proyecto_final
 * Clase encargada de controlar los datos y eventode de las ventana de home y  ingreso
 * */
public class Controlador implements Initializable{

    //Declaracion de variables
    public  static TreeMap<String,String> Usuarios = new TreeMap<>();

    @FXML
    private TextField txt_usuario, text_pass,txt_nombre;
    @FXML
    private Label lb_estado;
    @FXML
    private Button  bt_login,bt_enviar,bt_salir;
    @FXML
    private String  nombre,password;
    @FXML
    private ComboBox cmb_categoria,cmb_calificacion;
    @FXML
    private TextArea txt_comt;

    /***
     * Metedo encargado de administrar los eventos de la ventana home y ingreso
     * @param e evento a realizarce
     * @throws IOException
     */
    public void eventos(ActionEvent e ) throws IOException {


        if (e.getSource() == bt_login ){
            // tomamos los datos ingresados en login
            nombre = txt_usuario.getText().trim();
            password = text_pass.getText().trim();

            lb_estado.setText("Ingresando.....");

//            System.out.println(nombre+"   p: "+password);

            // buscamos la contraseña del usuario en nuestro diccionario ya caragdo al inicia
            if ( password.equalsIgnoreCase( Usuarios.get(nombre) )) {
                lb_estado.setText("USUARIO CORRECTO");
                // si es admin cambiamos la vista
                if (nombre.equalsIgnoreCase("admin")) {
                    cambiar_Ventana("Informe", bt_login);
                }else {
                    cambiar_Ventana("Ingreso", bt_login);
                }
            }else {
                lb_estado.setText("USUARIO INCORRECTO");
                System.out.println("Acceso Denegado");
            }
        }else if (e.getSource() == bt_enviar){
            //Evento de la ventana de ingreso  que enviara el reporte
            guardar_datos( txt_nombre.getText(), cmb_categoria.getValue(), cmb_calificacion.getValue(), txt_comt.getText() );
        }else if (e.getSource() == bt_salir){
            //Evento para salir al login
            System.out.println("Saliendo de ingreso de datos");
            cambiar_Ventana("Home", bt_salir);
        }
    }

    /***
     * Metodo encargado de carga los usuarios que tengamos almacenado.
     * @throws IOException
     */
    private void carga_usuarios() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("usuarios"));
        String sCurrentLine;

        while ((sCurrentLine = br.readLine()) != null) {
            // separamos los datos
            String[] datos = sCurrentLine.split(",");
            // asignamos los datos
            String usuario= datos [0];
            String pass = datos[1];

            // agregamos el usuario al diccionario
            this.Usuarios.put(usuario,pass);
        }

    }

    /***
     * Metedono encaragdo de guardar  los datos del reporte en un archivo csv
     * @param nombre  nombre de la empresa a reportar
     * @param categorias cataegoria de los servicios.
     * @param calificaciones nivel de calificacion
     * @param comentario extra comentario al respecto
     * @throws NullPointerException manejo de errores
     * @throws IOException manejo de errores
     */
    private void guardar_datos(String nombre, Object categorias, Object calificaciones, String comentario) throws NullPointerException, IOException {
        System.out.print("Guardando los datos...");
        // eliminamos los espacion del las variables
        nombre = nombre.trim();
        comentario = comentario.trim();
        String categoria = (String) categorias;
        String calificacion = (String) calificaciones;

        //Validamos los datos antes de guardar
        if (!nombre.equalsIgnoreCase("") && !categoria.equalsIgnoreCase("sin asiganr") &&  !calificacion.equalsIgnoreCase("sin asiganr")){
            System.out.println(" Dato verificado");

            BufferedWriter archivo = new BufferedWriter(new FileWriter("Reportes.csv",true));

            archivo.write(nombre+","+categoria+","+calificacion+","+comentario+";");
            archivo.newLine();
            archivo.close();
            // notificamos que se envio el reporte
            System.out.println(" Reporte guardado con exito.");
            // limpiamos el formulario
            txt_nombre.setText("");
            txt_comt.setText("");
            cmb_categoria.setValue( cmb_categoria.getPromptText());
            cmb_calificacion.setValue( cmb_calificacion.getPromptText());
        }else{
            System.out.println("Verifique los datos porfavor.");
        }
    }

    /***
     * Metodo encargado de cambiar las ventanas del programa
     * @param nombre de la ventana que deseemos cambiar
     * @param bt_selecionado sera el boton que dispare la accion
     * @throws IOException manejo de excepciones
     */
    public  void cambiar_Ventana(String nombre, Button bt_selecionado) throws IOException {
        nombre +=".fxml";
        Stage stage = (Stage) bt_selecionado.getScene().getWindow();;
        Parent root =  FXMLLoader.load(getClass().getResource(nombre));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /***
     * Metodo encargado de inicalizar o caragr los usuarios que tenemos hasta el momento alamacenado en nuestro archivo.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            // invocamos la funcion para que carga los usuarios
            carga_usuarios();
//            System.out.println(" usuarios: "+Usuarios.get("admin")+ " pass: "+Usuarios.get("popo"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

