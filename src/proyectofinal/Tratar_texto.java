/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.util.Arrays;

/**
 *
 * @author Sergi
 */
public class Tratar_texto {

    //Máximo de palabras diferentes que un fichero contendrá
    private static final int MAXPAL = 500;
    //Máximo de carácteres diferentes que un fichero contendrá
    private static final int MAXCAR = 500;

    // Palabras diferentes encontradas.
    private Palabra[] encontradas;
    private Palabra archivo;
    // Número de veces que se repite una palabra encontrada.
    private int[] apariciones;
    // Número de veces que se repite un carácter encontrado.
    private int[] freq;
    // Número de palabras, caracteres, lineas y columnas.
    private int numPal, numCar, linea, columna;
    private int numero;
    private int numE;
    // Caracteres diferentes encontrados.
    private char[] simbolos;
    private Character c;
    private int semilla;

    public Tratar_texto(int sem, Palabra P) {
        encontradas = new Palabra[MAXPAL];
        apariciones = new int[MAXPAL];
        freq = new int[MAXCAR];
        simbolos = new char[MAXCAR];
        numPal = 0;
        numCar = 0;
        linea = 0;
        numero = 0;
        numE = 0;
        columna = 0;
        semilla = sem;
        archivo = P;
    }

    public void tratar() {
        CodiFich cf = new CodiFich(semilla,archivo);
        if(semilla!=0){           
            cf.decodificar();
        }        
        FicheroEn Fen = new FicheroEn(archivo);
        c = Fen.leer(); //Leer primer carácter
        Character lp = ' ';
        while (c != null) {
            numCar++;             // valor 65 = A   valor 10 = salto de linea
            if ((c.charValue() >= 65) && (lp == ' ' || lp.charValue() == 10)) {
                numPal++;
            }
            lp = c;
            c = Fen.leer(); //leer siguiente carácter
        }
        linea = Fen.getlin(); // número de lineas que hay
        Fen.close();
         if(semilla!=0){            
            cf.cod();
        } 
    }

    //Devuelve el valor del número de palabras
    public int getPal() {
        return numPal;
    }

    //Devuelve el valor del número de carácteres
    public int getCar() {
        return numCar;
    }

    //Devuelve el valor del número de lineas
    public int getLinea() {
        return linea;
    }

    //Devuelve el valor del número de columnas
    private int getColumna() {
        return columna;
    }

    //Búsqueda de una palabra del fichero a través del clase Palabra
    public void buscarpal() {
        CodiFich cf = new CodiFich(semilla,archivo);
        if(semilla!=0){           
            cf.decodificar();
        }
        char[] frase;
        boolean contiene = false; // verificar si la palabra buscada esta en el el archivo
        FicheroEn fi = new FicheroEn(archivo);
        Palabra aux = fi.leerP();
        LT tec = new LT();
        frase = tec.llegirLiniaC();
        while (!aux.vacia()) {
            if (aux.esIgualA(frase)) {
                contiene = true;
                System.out.println("Encontrada en la línea "
                        + fi.getlin() + ", y columna " + (fi.getcol() - aux.longitud()));
            }
            aux = fi.leerP();
        }
        if (!contiene) {
            System.out.println(" No se ha encontrado '" + String.valueOf(frase) + "' en el archivo.");
        }
        fi.close();
        if(semilla!=0){            
            cf.cod();
        }
    }

    //Búsqueda de un texto del fichero a través del clase Palabra
    public void buscar_texto() {
        CodiFich cf = new CodiFich(semilla,archivo);
        if(semilla!=0){           
            cf.decodificar();
        }
        char[] frase;
        boolean contiene = false; // verificar si la palabra buscada esta en el archivo
        FicheroEn fi = new FicheroEn(archivo);
        Palabra aux = fi.leerLin();//leer una linea
        LT tec = new LT();
        frase = tec.llegirLiniaC();//leer frase usuario
        while (!aux.vacia()) { //mientras no llegue al final
            if (aux.contienePalabra(frase)) {//si la línea contiene el texto buscado
                contiene = true;
                System.out.println("Se encuentra en la línea "
                        + fi.getlin() + ", y columna " + (aux.longitud() - frase.length));
            }
            aux = fi.leerLin();
        }
        if (!contiene) {
            System.out.println(" No se ha encontrado '" + String.valueOf(frase) + "' en el archivo.");
        }
        fi.close();
        if(semilla!=0){            
            cf.cod();
        }
    }

    //Muestra la frecuencia de aparición de cada una de las letras del texto
    private void frecuencialetras() {
        CodiFich cf = new CodiFich(semilla,archivo);
        if(semilla!=0){           
            cf.decodificar();
        }
        FicheroEn fi = new FicheroEn(archivo);
        c = fi.leer();
        while ((c != null)) {
            if (c.charValue() >= 32) { // Valor 32 = espacio en blanco
                actualizar(c);
            }
            c = fi.leer();
        }
        fi.close();
        if(semilla!=0){            
            cf.cod();
        }
    }

    private void actualizar(char c) {
        int i = 0;
        simbolos[numero] = c;
        while (c != simbolos[i]) {
            i++;
        }
        if (i < numero) {
            freq[i]++;
        } else {
            freq[numero] = 1;
            numero++;
        }
    }

    public void freqPalSeg() {
        CodiFich cf = new CodiFich(semilla,archivo);
        if(semilla!=0){           
            cf.decodificar();
        }
        Palabra primera;
        Palabra anterior = new Palabra(); //Palabra precendente.
        int num = 0;
        FicheroEn fi = new FicheroEn(archivo);
        primera = fi.leerP();
        while (!primera.vacia()) {
            if (primera.esIgualA(anterior)) { // Si la palabra es igual a la precedente
                System.out.println("'" + primera + "' se repetite en la linea "
                        + fi.getlin() + " y columna " + (fi.getcol() - primera.longitud()));
                num++;
            }
            anterior = primera;
            primera = fi.leerP();
        }
        if (num == 0) {
            System.out.println("No se han encontrado palabras repetidas seguidas");
        }
        fi.close();
        if(semilla!=0){            
            cf.cod();
        }
    }

    //Muestra la frecuencia de aparición de cada una de las palabras del texto
    private void frecuenciaPal() {
        CodiFich cf = new CodiFich(semilla,archivo);
        if(semilla!=0){           
            cf.decodificar();
        }
        FicheroEn fi = new FicheroEn(archivo);
        Palabra aux = fi.leerP();
        while (!aux.vacia()) {
            int i = 0;
            while ((i < numE) && (!aux.esIgualA(encontradas[i]))) {
                i++;
            }
            if (i != numE) {
                apariciones[i]++;
            } else {
                encontradas[numE] = aux;
                apariciones[numE] = 1;
                numE++;
            }
            aux = fi.leerP();
        }
        fi.close();
        if(semilla!=0){            
            cf.cod();
        }
    }

    //Muestra la palabra más repetida del fichero y su número de apariciones
    public void Pal_mas_rep() {
        frecuenciaPal();
        int max = 0;
        Palabra pal = null;
        Palabra otrapal = null;
        for (int id = 0; id < numE; id++) {
            while (apariciones[id] > max) { //Mientras no tenga más apariciones 
                max = apariciones[id];      // que la anterior, significa que
                pal = encontradas[id];      // esa palabra es la más repetida.
            }
        }
        if (pal != null) { //Mientras las palabras no excendan el máximo de palabras permitidas.
            System.out.println("La palabra '" + pal + "' se repite: " + max + " veces.");
            //Si hay empate de aparciones.
            for (int i = 0; i < numE; i++) {
                if (apariciones[i] == max) {
                    otrapal = encontradas[i];
                    if (!otrapal.esIgualA(pal)) {
                        System.out.println("La palabra '" + otrapal + "' se repite: " + max + " veces.");
                    }
                }
            }
        } else {
            System.out.println("Se ha excedido el número de palabras diferentes.");
        }
    }

    //Muestra la letra más repetida del fichero y su número de apariciones
    public void Let_mas_rep() {
        frecuencialetras();
        int max = 0;
        Character simb = 0;
        char car = 0;
        for (int i = 0; i < numero; i++) {
            simb = simbolos[i]; // 65 = A, 90 = Z, 97 = a, 122 = z
            while (freq[i] > max && ((simb.charValue() >= 65 && simb.charValue() <= 90)
                    || (simb.charValue() >= 97 && simb.charValue() <= 122))) {
                max = freq[i];
                car = simbolos[i];
            }
        }
        System.out.println("El caracter más repetetido es '" + car + "' y aparece " + max
                + " veces.");
    }

//Resultados de frecuencialetras, muestra el caracter junto a su número de apariciones. 
    public void resultfreqcar() {
        frecuencialetras();
        System.out.println(" ");
        for (int i = 0; i < numero; i++) {
            System.out.println("El caracter '" + simbolos[i] + "' aparece " + freq[i]
                    + " veces.");
        }
    }

//Resultados de Palabras repetidas en el texto, muestra la palabra junto 
//    a su numero de apariciones.
    public void resultpalrep() {
        frecuenciaPal();
        System.out.println(" ");
        for (int id = 0; id < numE; id++) {
            if (apariciones[id] > 1) {
                System.out.println("La palabra '" + encontradas[id] + "' se repite: "
                        + apariciones[id]);
            }
        }
    }

    //Imprime el dichero analizado.
    public void imprimir() {
        CodiFich cf = new CodiFich(semilla,archivo);
        if(semilla!=0){           
            cf.decodificar();
        }
        FicheroEn fi = new FicheroEn(archivo);
        c = fi.leer();
        while (c != null) {
            System.out.print(c);
            c = fi.leer();
        }
        fi.close();
        if(semilla!=0){            
            cf.cod();
        }
    }
}
