import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;



public class Principal{

    // MÉTODO GUARDAR: El "robot" que escribe todo en el archivo .txt
    public static void guardarTareas(ArrayList<Tarea> lista){
        try {
            // Abrimos el archivo (sin el 'true' para que borre lo viejo y actualice todo)
            FileWriter escribirArchivo = new FileWriter("mis_tareas.txt");
            PrintWriter imprimirArchivo = new PrintWriter(escribirArchivo);

            // Recorremos la lista y escribimos cada tarea usando sus GETTERS
            for(Tarea tareaa : lista){
                imprimirArchivo.println(tareaa.getDescripcion() + ";" + 
                                       tareaa.getFechaLimite() + ";" + 
                                       tareaa.getPrioridad() + ";" + 
                                       tareaa.isTerminado());
            }
            imprimirArchivo.close(); 
            System.out.println("Copia de seguridad aztualizada.");
            
        } catch (IOException error) {
            System.out.println("Error al guardar.");
        }
    }

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        ArrayList<Tarea> listaTarea = new ArrayList<>();

        // --- FASE DE CARGA: Recuperamos los datos al arrancar ---
        try {
            File archivo = new File("mis_tareas.txt");
            if (archivo.exists()) {
                Scanner lectorArchivo = new Scanner(archivo);
                while (lectorArchivo.hasNextLine()) {
                    String linea = lectorArchivo.nextLine();
                    String[] trozos = linea.split(";"); // Cortamos por el punto y coma
                    
                    // Creamos la tarea con los trozos leídos
                    Tarea tareaa = new Tarea(trozos[0], trozos[1], Integer.parseInt(trozos[2]));
                    // Usamos el SETTER para poner si estaba terminada o no
                    tareaa.setTerminado(Boolean.parseBoolean(trozos[3])); 
                    listaTarea.add(tareaa);
                }
                lectorArchivo.close();
                System.out.println("--- Tareas cargadas correctamente desde el disco ---");
            }
        } catch (Exception e) {
            System.out.println("Todavía no hay archivo de guardado o está corrupto.");
        }

        int obcion = 0;
        // BUCLE DEL MENÚ: Se repite hasta que obcion sea 5
        while (obcion != 5) {
            System.out.println("---- MENU ----");
            System.out.println("1. Añadir tarea");
            System.out.println("2. Ver tareas pendientes");
            System.out.println("3. Marcar tarea echa");
            System.out.println("4. Filtrar por prioridad");
            System.out.println("5. Salir");

            obcion = leer.nextInt();
            leer.nextLine(); 

            switch (obcion) {
                case 1: // --- AÑADIR ---
                    System.out.println("¿Que tarea deseas añadir?");
                    String tarea = leer.nextLine();
                    System.out.println("¿Fecha limite?");
                    String fecha = leer.nextLine();
                    System.out.println("¿Nivel prioridad del 1-5?");
                    int necesidad = leer.nextInt();
                    leer.nextLine();

                    // Comprobar si ya existe
                    boolean repetido = false;
                    for(int i = 0; i < listaTarea.size(); i++){
                        if (tarea.equalsIgnoreCase(listaTarea.get(i).getDescripcion())) {
                            repetido = true;
                            break;
                        }
                    }

                    if (repetido) {
                        System.out.println("Ya tienes esta tarea añadida.");
                    } else {
                        listaTarea.add(new Tarea(tarea, fecha, necesidad));
                        System.out.println("Tarea añadida correctamente");
                        guardarTareas(listaTarea); // Guardamos automáticamente
                    }
                    break;

                case 2: // --- VER PENDIENTES ---
                    System.out.println("--- Estas son tus Tareas pendientes ---");
                    if (listaTarea.isEmpty()) {
                        System.out.println("No tienes tareas pendientes");
                    } else {
                        for(int i = 0; i < listaTarea.size(); i++){
                            Tarea pendientes = listaTarea.get(i);
                            // Usamos el GETTER 'isTerminado' para ver el estado
                            if (pendientes.isTerminado() == false) { 
                                System.out.println("Descripcion: " + pendientes.getDescripcion() +
                                                   " | Fecha Limite: " + pendientes.getFechaLimite() +
                                                   " | Prioridad " + pendientes.getPrioridad());
                            }
                        }
                    }
                    break;

                case 3: // --- COMPLETAR ---
                    System.out.println("Que tarea deseas marcar terminado.");
                    String quitar = leer.nextLine();
                    boolean encontrado = false;

                    for(int i = 0; i < listaTarea.size(); i++){
                        Tarea terminar = listaTarea.get(i);
                        if (terminar.getDescripcion().equalsIgnoreCase(quitar)) {
                            // Usamos el SETTER para cambiar el estado a true
                            terminar.setTerminado(true); 
                            encontrado = true;
                            System.out.println("Tarea " + terminar.getDescripcion() + " marcado como terminado.");
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("No se encontro ninguna tarea con ese nombre");
                    } else {
                        guardarTareas(listaTarea); // Actualizamos el archivo
                    }
                    break;

                case 4: // --- FILTRAR ---
                    System.out.println("¿Que taras deseas ver por prioridad del 1-5?");
                    int filtrar = leer.nextInt();
                    // ... resto de lógica con los Getters correspondientes
                    break;
            }
        }
    }
}