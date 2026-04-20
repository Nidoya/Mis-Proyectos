import java.io.*; // Importamos "Input/Output" (Entrada/Salida)
import java.util.Scanner;

public class PruebaArchivo {
    public static void main(String[] args) {
        try {
            // 1. Creamos o abrimos el archivo
            FileWriter escribirArchivo = new FileWriter("mis_datos.txt", true); 
            PrintWriter imprimirArchivo = new PrintWriter(escribirArchivo);

            // 2. Escribimos dentro
            imprimirArchivo.println("Esta es una línea guardada en el disco duro.");
            imprimirArchivo.println("¡Si cierro el programa, esto seguirá aquí!");

            // 3. ¡IMPORTANTE! Cerramos el grifo
            imprimirArchivo.close();

            File archivo = new File("mis_datos.txt");
            Scanner leer = new Scanner(archivo);
            leer.close();

            while (leer.hasNextLine()) {
                String linea = leer.nextLine();
                System.out.println("He leído del archivo: " + linea);
                
            }

            System.out.println("Archivo guardado con éxito.");

        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir el archivo.");
        }
    }
}