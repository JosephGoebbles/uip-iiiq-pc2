
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Main {

    public static void main(String[] args) {
        BufferedReader tecla = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> dicMascota = new HashMap<String, Integer>();

        Tarea2 mascotas;
        String opc = null, opc2;
        int salir=0, salir2 = 0;
        String nombre;

        System.out.println("TAREA #2");
        try {
            do {
                System.out.println("Seleccion  Mascota:");
                System.out.println("\t 1- Perro");
                System.out.println("\t 2- Gato");
                System.out.println("\t 3- Salir");
                System.out.print("-----> ");
                opc = tecla.readLine();

                int vidas = 0;
                switch (opc) {
                    case "1":
                        //Perro
                        System.out.print("Nombre de la mascota: ");
                        nombre = tecla.readLine();
                        // instanciamos una mascota
                        mascotas = new Tarea2("perro", nombre);

                        System.out.println();
                        do {
                            System.out.println("ACCIONES A REALIZAR AL PERRO " + mascotas.getNombre());
                            System.out.println("\t 1- Ladrar");
                            System.out.println("\t 2- Comer");
                            System.out.println("\t 3- Correr");
                            System.out.println("\t 4- Terminar");
                            System.out.print("-----> ");
                            opc2 = tecla.readLine();

                            switch (opc2) {
                                case "1":
                                    mascotas.acciones(mascotas.getMascota(), "ladrar");
                                    break;
                                case "2":
                                    mascotas.acciones(mascotas.getMascota(), "comer");
                                    break;
                                case "3":
                                    mascotas.acciones(mascotas.getMascota(), "correr");
                                    break;
                                case "4":
                                    System.out.println("Bye " + mascotas.getMascota()+" "+ mascotas.getNombre());
                                    salir2=4;
                                    break;
                                default:
                                    System.out.println("Accion Selecciona incorrecta!");
                                    break;
                            }
                            vidas = mascotas.getPunto_vida();
                            // agregamos la mascato y su vida al diccionario
                            dicMascota.put(mascotas.getNombre(), vidas);

                            // verificamos las vidas de la mascota
                            if (vidas<=0){
                                salir2 = 4;
                                System.out.println("\n Mascota " + mascotas.getNombre() + " Muerto :( \n");
                            }else {
                                System.out.println("vidas Disponible:" + vidas + "\n");
                                salir2 =Integer.parseInt(opc2);
                            }
                        } while (salir2 < 4);

                        break;
                    case "2":
                        //Gato
                        System.out.print("Nombre de la mascota: ");
                        nombre = tecla.readLine();
                        mascotas = new Tarea2("gato", nombre);
                        vidas = 0;

                        do {
                            System.out.println("ACCIONES A REALIZAR AL GATO " + mascotas.getNombre());
                            System.out.println("\t 1- Domir");
                            System.out.println("\t 2- Comer");
                            System.out.println("\t 3- Caminar");
                            System.out.println("\t 4- Terminar");
                            System.out.print("-----> ");
                            opc2 = tecla.readLine();

                            switch (opc2) {
                                case "1":
                                    mascotas.acciones(mascotas.getMascota(), "dormir");
                                    break;
                                case "2":
                                    mascotas.acciones(mascotas.getMascota(), "comer");
                                    break;
                                case "3":
                                    mascotas.acciones(mascotas.getMascota(), "caminar");
                                    break;
                                case "4":
                                    System.out.println("Bye " + mascotas.getMascota() +" "+ mascotas.getNombre());
                                    salir2 = 4;
                                    break;
                                default:
                                    System.out.println("Accion Selecciona incorrecta!");
                                    break;
                            }
                            vidas = mascotas.getPunto_vida();
                            dicMascota.put(mascotas.getNombre(), vidas);

                            if (vidas <= 0){
                                salir2=4;
                                System.out.println("\n Mascota " + mascotas.getNombre() + " Muerto :( \n");
                            }else {
                                System.out.println("vidas Disponible:" + vidas + "\n");
                                salir2 =Integer.parseInt(opc2);
                            }
                        } while (salir2 < 4);
                        break;
                    case "3":
                        System.out.println("Saliendo del progrma. ");
                        break;
                    default:
                        System.out.println("Opcion incorrecta!");
                        break;
                }
                salir = Integer.parseInt(opc);
            } while (salir < 3);

        }catch (Exception e ) {
            System.out.println("Exception : "+e.getMessage());
        }
        //Guardaremos la informacion en el archivo
        try {
            BufferedWriter archivo = new BufferedWriter(new FileWriter("Mascota.txt"));
            Iterator it = dicMascota.keySet().iterator();
            while (it.hasNext()){
                String key = String.valueOf(it.next());
                int valor = dicMascota.get(key);
                archivo.write("Mascota: "+key+" Vidas:"+valor);
                archivo.newLine();
            }
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
