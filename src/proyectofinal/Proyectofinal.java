/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import java.io.File;

/**
 *
 * @author Sergi
 */
public class Proyectofinal {

    private Palabra P;
    private int x, y, z;
    private int semilla = 0;

    private void inicio() {
        System.out.println("Esto es un programa analizador de ficheros de texto.");
        P = seleccionF();
        Tratar_texto T = new Tratar_texto(semilla, P);
        T.tratar();
        z = T.getCar();
        y = T.getPal();
        x = T.getLinea();
        Resultados1();
        char opc = ' ';
        while (opc != 's') {
            Presentacion();
            opc = menu();
        }
    }

    //Inicio del programa
    public static void main(String[] args) {
        (new Proyectofinal()).inicio();
    }

    //Menu usuario.Seleción de opción.
    private char menu() {
        Tratar_texto T = new Tratar_texto(semilla, P);
        char opc = Opc_menu();
        switch (opc) {
            case 'a':
                System.out.println(" ");
                T.Let_mas_rep();
                System.out.println("      ");
                break;

            case 'b':
                System.out.println(" ");
                T.resultfreqcar();
                System.out.println("      ");
                break;

            case 'c':
                System.out.println(" ");
                T.Pal_mas_rep();
                System.out.println("      ");
                break;
            case 'd':
                System.out.println(" ");
                System.out.print("Introduce la palabra que desea buscar: ");
                T.buscarpal();
                System.out.println("      ");
                break;

            case 'e':
                System.out.println(" ");
                System.out.print("Introduce el texto que desea buscar: ");
                T.buscar_texto();
                System.out.println("      ");
                break;

            case 'f':
                System.out.println(" ");
                T.freqPalSeg();
                System.out.println("      ");
                break;

            case 'g':
                System.out.println(" ");
                semilla = semilla();
                CodiFich F = new CodiFich(semilla, P);
                F.codificar();
                System.out.println("      ");
                break;

            case 'h'://Extra
                System.out.println(" ");
                T.resultpalrep();
                System.out.println("      ");
                break;

            case 'i': //Extra
                System.out.println(" ");
                T.imprimir();
                System.out.println("      ");
                break;
            case 'j':
                System.out.println(" ");
                semilla = semilla();
                break;

            case 's':
                System.out.println("Cerrando programa, Adiós.");
                break;
        }

        return opc;
    }

//Seleción de caracter correcto. Evitar errores de datos erroneos.
    private char Opc_menu() {
        LT tec = new LT();
        Character res = ' ';
        res = tec.llegirCaracter();
        if (res == null) {  //Si usuario no introduce ningun caracter.
            res = 'J';
        }
        //Si usuario introduce opción incorrecta.
        while ((res != 'a') && (res != 'b') && (res != 'c') && (res != 'd')
                && (res != 'e') && (res != 'f') && (res != 'g') && (res != 'h')
                && (res != 'i') && (res != 'j') && (res != 's')) {
            System.out.println(" No es una opción correcta.");
            res = tec.llegirCaracter();
            if (res == null) { //Si usuario no introduce ningun caracter.
                res = 0;
            }
        }
        return res;
    }

//Presentación del menú principal.
    private void Presentacion() {
        System.out.println("_________________________________________");
        System.out.println(" ");
        System.out.println("Selecione la opción que desee realizar.");
        System.out.println(" a ==> Mostrar la letra más repetida y su número de apariciones.");
        System.out.println(" b ==> Mostrar el número de apariciones de cada carácter.");
        System.out.println(" c ==> Mostrar la palabra más repetida y su número de apariciones.");
        System.out.println(" d ==> Buscar una palabra.");
        System.out.println(" e ==> Buscar un texto.");
        System.out.println(" f ==> Buscar las palabras repetidas seguidas.");
        System.out.println(" g ==> Codificar fichero.");
        System.out.println(" h ==> Buscar las palabras repetidas.");
        System.out.println(" i ==> Imprimir fichero.");
        System.out.println(" j ==> Selecionar semilla para tratar texto codificado.");
        System.out.println(" s ==> Salir del programa.");
        System.out.println("_________________________________________");
    }

//Da los resultados del fichero seleccionado por el usuario.
    private void Resultados1() {
        System.out.println(" ");
        System.out.println("En '" + String.valueOf(P) + "' hay: ");
        System.out.println("    " + z + " caracteres en total. "); // z = Núm de caracteres
        System.out.println("    " + y + " palabras en total. "); // y = Núm de palabras 
        System.out.println("    " + x + " lineas en el fichero.");// x = Núm de lineas
    }

//Selecion de fichero a analizar por el usuario
    private Palabra seleccionF() {
        File Arch = null;
        Palabra res = null;
        LT tec = new LT();
        while (Arch == null) {            
            System.out.println("_________________________________________");
            System.out.println(" ");
            System.out.println("¿Que archivo desea analizar? ");
            System.out.print("    Escriba el nombre del archivo: ");
            res = new Palabra(tec.llegirLiniaC());
            if (res != null) {
                Arch = new File(res.toString());
                if (!Arch.exists()) {
                    System.out.println("Este archivo no existe");
                    Arch = null;
                }
            } else {
                System.out.println("Este archivo no existe");
                Arch = null;
            }
        }
        return res;
    }

    //Elige la semilla a utilizar en el fichero de texto.
    private int semilla() {
        Integer res = -1;
        LT tec = new LT();
        while (res <= -1) {
            System.out.print("Seleccione la semilla que desee (1-64) ");
            res = tec.llegirSencer();
            if ((res == null) || (res <= 0) || (res > 65)) {
                System.out.println("Semilla incorrecta.");
                res = -1;
            }
        }
        return res;
    }

}
