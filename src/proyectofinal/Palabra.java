/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

/**
 *
 * @author Sergi
 */
public class Palabra {

    public static final int max = 20;
    private int capaci = 20;
    private char[] letra;
    private int longitud;

    public Palabra() {
        letra = new char[capaci];
        longitud = 0;
    }

    public Palabra(char[] s) {
        capaci = s.length;
        letra = new char[capaci];
        longitud = 0;
        for (int i = 0; i < s.length; i++) {
            letra[longitud++] = s[i];
        }
    }

// Devuelve longitud de la palabra
    public int longitud() {
        return longitud;
    }

//Añadir letra a una palabra
    public void añadir(char c) {
        if (longitud == capaci) {
            char aux[] = new char[capaci + max];
            for (int i = 0; i < capaci; i++) {
                aux[i] = letra[i];
            }
            capaci = capaci + max;
            letra = aux;
        }
        letra[longitud++] = c;
    }

//Devuelve si una palabra contiene algo o no
    public boolean vacia() {
        return longitud == 0;
    }

//Convierte palabra a string
    public String toString() {
        String msg = "";
        for (int idx = 0; idx < longitud; idx++) {
            msg += letra[idx];
        }
        return msg;
    }

//Comprueba si dos palabras son iguales
    public boolean esIgualA(char[] c) {
        boolean iguals = longitud == c.length;
        for (int idx = 0; (idx < longitud) && iguals; idx++) {
            iguals = letra[idx] == c[idx];
        }
        return iguals;
    }

//Comprueba si dos palabras son iguales
    public boolean esIgualA(Palabra b) {
        boolean iguals = longitud == b.longitud;
        for (int idx = 0; (idx < longitud) && iguals; idx++) {
            iguals = letra[idx] == b.letra[idx];
        }
        return iguals;
    }

    //Comprueba si la palabra/frase pasada por parametro esta contenida en Palabra
    public boolean contienePalabra(char[] a) {
        //igual o mayor a la palabra pasada por parámetro
        if (longitud>=a.length) {
            for (int indice=0;indice<longitud;indice++) {
                if ((longitud-indice)>=a.length) {
                    boolean contenida=true;
                    for (int indice2=0;indice2<a.length;indice2++) {
                        if (letra[indice+indice2]!= a[indice2]) {
                            contenida=false;
                        }
                    }
                    //Sí contiene a la palabra dada por parámetro
                    if (contenida) {
                        return true;
                    }
                }
            }
            //No contiene la palabra dada por el parámetro
            return false;
        }
        else {
            ////No contiene la palabra dada por el parámetro
            return false;
        }
    }

}
