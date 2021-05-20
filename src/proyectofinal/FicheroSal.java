/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sergi
 */
public class FicheroSal {

    private Palabra F;
    private FileWriter Fw;
    private BufferedWriter Bw;

    public FicheroSal(Palabra P) {
        F = P;
        try {
            Fw = new FileWriter(F.toString());
            Bw = new BufferedWriter(Fw);
        } catch (IOException ex) {
            System.out.println("Exception abriendo fichero:"
                    + " " + F + " error: " + ex);
        }
    }

//Escribe carácter a carácter.
    public void escribir(char c) {
        char[] x = new char[1];
        x[0] = c; // Escribir el primer carácter.
        try {
            Bw.write(x);
        } catch (IOException ex) {
            System.out.println("Exception escribiendo fichero:"
                    + " " + F + " error: " + ex);
        }
    }

    public void close() {
        try {
            Bw.flush();
            Bw.close();
            Fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                Fw.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
