/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Sergi
 */
public class FicheroEn {

    private Palabra F;
    private BufferedReader br;
    private FileReader fr;
    private Character c;
    private int linea, columna;

    public FicheroEn(Palabra P) {
        F = P;
        try {
            fr = new FileReader(F.toString());
            br = new BufferedReader(fr);
            linea = columna = 1;
        } catch (FileNotFoundException ex) {
            System.out.println("Exception abriendo fichero:"
                    + " " + F + " error: " + ex);
        }
    }

// Método de lectura carácter a carácter del fichero. 
    public Character leer() {
        int x;
        Character ca = null;
        try {
            x = br.read();
            if (x == -1) {
                ca = null;
            } else {
                ca = (char) x;
                if (ca.charValue() == 10) { // valor 10 = salto de linea.
                    linea++;
                    columna = 1;
                } else {
                    if (ca.charValue() != 13) { // valor 13 = retorno de carro.
                        columna++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Exception leyendo fichero: " + F + " error: " + e);
        }
        return ca;
    }   
    
    //Leer una palabra del fichero.
    public Palabra leerP() {
        Palabra aux = new Palabra();
        saltarEsp();
        while ((c != null) && (c.charValue() >= 65)) {//valor 65 = A.
            aux.añadir(c);
            c = leer();
        }
        return aux;
    }

    //Leer una linea del fichero.
    public Palabra leerLin() {
        Palabra aux = new Palabra();
        c = leer();             //Valor 10 = salto de línea.
        while ((c != null) && (c.charValue() != 10)) {
            aux.añadir(c);
            c = leer();
        }
        return aux;
    }

    private void saltarEsp() {
        c = leer();
        while ((c != null) && (c.charValue() <= 32)) { //valor 32 = espacio.
            c = leer();
        }
    }

    //Devuelve el valor del número de columnas.
    public int getcol() {
        return columna;
    }

    //Devuelve el valor del número de líneas.
    public int getlin() {
        return linea;
    }

    public void close() {
        try {
            fr.close(); //Cerrar FileReader
            br.close(); // Cerrar BufferedReader
        } catch (Exception ex) {
            System.out.println("Exception cerrando fichero " + F + ": " + ex);
        } finally {
            try {
                fr.close();
            } catch (Exception e) {
                System.out.println("Exception cerrando fichero " + F + ": " + e);
            }
        }
    }
}
