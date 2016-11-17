import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Tarea1 {

    public static void main(String[] args) {
        //utilizando alternativa a BufferedReader
        Scanner tecla = new Scanner(System.in);

        System.out.println("TAREA # 1 \n");

        int velocidad=0, multa = 0, id =1, salir = 0;
        String clave, opc;

        Map<String,Integer> diccionario = new HashMap<String, Integer>();


        while (salir == 0) {
            try {
                clave = "velocidad_"+id;
                System.out.print("Ingrese las velocidades #"+id+": ");
                velocidad = tecla.nextInt();
                if (velocidad > 160) {
                    multa += 150;
                } else if (velocidad > 120) {
                    multa += 50;
                }else {
                    multa = multa;
                }
                System.out.println("Total de multa:"+multa);
                diccionario.put(clave, multa);

                System.out.print("\nDesea Cerrar el programa si= 1 / no= 0 ? \n---->" );
                salir = tecla.nextInt();

            } catch (Exception e) {
                System.out.print(" Porfavor solo numero.");
            }
            id++;
        }
      // guardamos los datos en el archivo
        try {
            BufferedWriter archivo = new BufferedWriter(new FileWriter("ATT.txt"));
            Iterator it = diccionario.keySet().iterator();
            while (it.hasNext()){
                String key = String.valueOf(it.next());
                int valor = diccionario.get(key);

                archivo.write(key+" Total Multa:"+valor);
                archivo.newLine();
            }
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
