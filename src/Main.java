import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class Main {
    /*
    Utilitzant un fitxer aleatori, realitza un programa que mostre a l’usuari un menú amb les opcions següents:

1. Afegir números de tipus double al principi del fitxer.
2. Afegir números de tipus double al final del fitxer.
3. Mostrar el fitxer creat.
4. Substituir un número indicat per l’usuari per un altre número que també us indique l’usuari.
Nota: Un double a JAVA ocupa 8 bytes.


     */

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        try {
            RandomAccessFile fichero = new RandomAccessFile("prueba.bin", "rw");

            while (true) {
                System.out.println("Menu");
                System.out.println("1. Añadir numero al principio");
                System.out.println("2. Añadir numeros al final");
                System.out.println("3. Mostrar el fichero creado");
                System.out.println("4. Sustituir un numero");
                System.out.println("5. Salir");
                System.out.println("");
                System.out.print("Elige la opcion que quieres mostrar por pantalla: ");
                int opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        ArrayList<Double> Numeros = new ArrayList<>();

                        System.out.println("Añade un numero al principio.");
                        double a = sc.nextDouble();
                        try {
                            fichero.seek(0);
                            while (true) {
                                Numeros.add(fichero.readDouble());
                            }
                        } catch (IOException e) {
                        }
                        Numeros.addFirst(a);
                        fichero.setLength(0);
                        for (Double dou : Numeros) {
                            fichero.writeDouble(dou);
                        }

                        break;


                    case 2:
                        System.out.println("Añade un numero al final.");
                        double num = sc.nextDouble();
                        fichero.seek(fichero.length());
                        fichero.writeDouble(num);
                        break;


                    case 3:
                        fichero.seek(0);
                        try {
                            while (true) {
                                System.out.println(fichero.readDouble());
                            }
                        } catch (IOException e) {
                            System.out.println("Se acabo guapo");
                        }
                        break;


                    case 4:
                        System.out.println("dime el numero que quieres sustituir");
                        double numeroAntiguo = sc.nextDouble();
                        System.out.println("dime el numero que quieres poner nuevo");
                        double numeroNuevo = sc.nextDouble();
                        fichero.seek(0);

                        while (true) {
                            if (fichero.readDouble() == numeroAntiguo) {
                                fichero.seek(fichero.getFilePointer() - 8);
                                fichero.writeDouble(numeroNuevo);
                                System.out.println("Número sustituido.");

                            }

                            break;

                        }

                        case 5:
                                System.out.println("Se acabo llego a sufin serafin");
                                fichero.close();
                                System.exit(0);



                            default:
                                System.out.println("La opcion no esta en el menu lo siento");

                }


            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

