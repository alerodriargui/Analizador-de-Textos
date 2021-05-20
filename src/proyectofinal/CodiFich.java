/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.io.File;
import java.util.Random;

/**
 *
 * @author Sergi
 */
public class CodiFich {

    private int semilla;
    private Palabra archivo;
    private char[] normal;
    private char[] cod;

    public CodiFich(int a, Palabra F) {
        semilla = a;
        archivo = F;
        normal = "ABCDEFGHIJKLMNOPQRSTUVWXYZÇÑabcdefghijklmnopqrstuvwxyzçñ·,.?:&%$+*".toCharArray();
        cod = new char[normal.length];
        Random ran = new Random(semilla);
        for (int i = 0; i < normal.length; i++) {
            cod[i] = 0;
        }
        for (int i = 0; i < normal.length; i++) {
            int ind = ran.nextInt(normal.length);
            while (cod[ind] != 0) {
                ind = ran.nextInt(normal.length);
            }
            cod[ind] = normal[i];
        }
    }

    //Codifica el fichero.
    public void codificar() {
        CodArc(normal, cod);
    }

    //Decodifica el fichero,cuando ha leido programa codificado.
    public void decodificar() {
        CodArcCod(cod, normal);
    }

    //Codificar el fichero,cuando ha leido programa codificado.
    public void cod() {
        CodArcCod(normal, cod);
    }

    //Tratar archivo cuando sin codificar.
    private void CodArc(char a[], char[] b) {
        FicheroEn Fen = new FicheroEn(archivo);
        FicheroSal Fsal = new FicheroSal(new Palabra((archivo.toString() + ".cod.txt").toCharArray()));
        Character c = Fen.leer();
        char car;
        int ind;
        while (c != null) {
            ind = 0;
            while ((ind < a.length) && (a[ind] != c)) {
                ind++;
            }
            if (ind == a.length) {
                car = c;
            } else {
                car = b[ind];
            }
            Fsal.escribir(car);
            c = Fen.leer();
        }
        Fen.close();
        Fsal.close();
    }

    //Tratar archivo cuando este codificado.
    private void CodArcCod(char a[], char[] b) {
        FicheroEn Fen = new FicheroEn(archivo);
                        //Creamos un fichero temporal, que luego será el definitivo.
        FicheroSal Fsal = new FicheroSal(new Palabra("ficherotemporal".toCharArray()));
        Character c = Fen.leer();
        char car;
        int ind;
        while (c != null) {
            ind = 0;
            while ((ind < a.length) && (a[ind] != c)) {
                ind++;
            }
            if (ind == a.length) {
                car = c;
            } else {
                car = b[ind];
            }
            Fsal.escribir(car);
            c = Fen.leer();
        }
        Fen.close();
        Fsal.close();
        //El fichero temporal creado anteriormente pasa a ser el definitivo.
        File fich = new File(archivo.toString());
        fich.delete(); 
        File fich2 = new File("ficherotemporal");
        fich2.renameTo(fich);
    }

}
