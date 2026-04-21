import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorArchivo {

    // MÉTODO GUARDAR: Recibe la lista de la memoria y la escribe en el disco
    public static void guardarTareas(ArrayList<Tarea> lista) {
        try {
            // FileWriter sin 'true' para sobrescribir el archivo con los datos nuevos
            FileWriter escribirArchivo = new FileWriter("mis_tareas.txt");
            PrintWriter imprimirArchivo = new PrintWriter(escribirArchivo);
            
            // Recorremos la lista y convertimos cada objeto en una línea de texto
            for (Tarea tareaa : lista) {
                imprimirArchivo.println(tareaa.getDescripcion() + ";" + 
                                       tareaa.getFechaLimite() + ";" + 
                                       tareaa.getPrioridad() + ";" + 
                                       tareaa.isTerminado());
            }
            imprimirArchivo.close(); // Cerramos el flujo para asegurar el guardado
            System.out.println("Copia de seguridad aztualizada.");
        } catch (IOException error) {
            System.out.println("Error técnico: No se pudo guardar el archivo.");
        }
    }

    // MÉTODO CARGAR: Lee el disco y resucita los datos convirtiéndolos en objetos
    public static ArrayList<Tarea> cargarTareas() {
        ArrayList<Tarea> listaTemporal = new ArrayList<>();
        
        try {
            File archivo = new File("mis_tareas.txt");
            if (archivo.exists()) {
                Scanner lector = new Scanner(archivo);
                // Mientras haya líneas, las troceamos y creamos objetos Tarea
                while (lector.hasNextLine()) {
                    String[] trozos = lector.nextLine().split(";");
                    Tarea t = new Tarea(trozos[0], trozos[1], Integer.parseInt(trozos[2]));
                    // Restauramos el estado de 'terminado' que estaba guardado
                    t.setTerminado(Boolean.parseBoolean(trozos[3]));
                    listaTemporal.add(t);
                }
                lector.close();
            }
        } catch (Exception e) {
            System.out.println("Aviso: No se pudo cargar el archivo (puede que aún no exista).");
        }
        return listaTemporal; // Devolvemos la lista lista para que el Main la use
    }
}