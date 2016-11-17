package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.DoubleSummaryStatistics;

public class Main {

    public static void main(String[] args) throws IOException {
	String nombre;
    Double nota = 0.0;

        BufferedReader tec = new BufferedReader(new InputStreamReader(System.in));

        System.out.print(" Hola. Escriba su nombre: ");
        nombre = tec.readLine();

        try {System.out.print("Ponga su nota: ");
            nota = Double.parseDouble(tec.readLine());

        } catch (Exception E1) {

        }

        System.out.println("Su nombre es " + nombre);

        if (nota >= 91) {
            System.out.println("- Tiene A");
        } else if (nota >= 81) {
            System.out.println("- Tiene B");
        } else if (nota >=71) {
            System.out.println("- Tiene C");
        } else {
            System.out.println("- PELASTE");
        }
    }

}
