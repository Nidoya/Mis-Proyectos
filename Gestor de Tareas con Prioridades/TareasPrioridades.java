import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

// CLASE TAREA: El molde de nuestros objetos. 
// Define qué atributos (datos) tiene cada tarea de nuestra lista.
class Tarea {
    String descripcion;
    String fechaLimite;
    int prioridad;
    boolean terminado; // Representa el estado: false (pendiente), true (hecha)

    // CONSTRUCTOR: El "nacimiento" del objeto.
    // Se usa para asignar los valores que el usuario escribe a las variables de la clase.
    public Tarea(String descripcion, String fechaLimite, int prioridad) {
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.prioridad = prioridad;
        this.terminado = false; // Por defecto, una tarea nueva no está terminada.
    }
}

public class TareasPrioridades {

    public static void main(String[] args) {
        
        Scanner leer = new Scanner(System.in);
        // ARRAYLIST: Nuestra base de datos en la memoria RAM mientras el programa corre.
        ArrayList<Tarea> listaTarea = new ArrayList<>();

        // --- FASE DE CARGA (INICIO DEL PROGRAMA) ---
        // Intentamos leer el archivo para recuperar las tareas guardadas anteriormente.
        try {
            File archivo = new File("mis_tareas.txt");
            
            // Verificamos si el archivo existe para evitar errores al intentar abrirlo.
            if (archivo.exists()) {
                Scanner lectorArchivo = new Scanner(archivo);
                
                // Mientras el archivo tenga líneas de texto por leer...
                while (lectorArchivo.hasNextLine()) {
                    String linea = lectorArchivo.nextLine();
                    
                    // .split(";") -> Rompe la línea de texto en trozos separados por el punto y coma.
                    // Ejemplo: "Lavar;Hoy;2;false" -> ["Lavar", "Hoy", "2", "false"]
                    String[] trozos = linea.split(";");
                    
                    // Convertimos los trozos de texto al tipo de dato que necesitan (int o boolean).
                    String desc = trozos[0];
                    String fecha = trozos[1];
                    int prio = Integer.parseInt(trozos[2]); // Texto a número
                    boolean hecho = Boolean.parseBoolean(trozos[3]); // Texto a verdadero/falso
                    
                    // Reconstruimos el objeto Tarea y lo metemos en la lista.
                    Tarea tareaa = new Tarea(desc, fecha, prio);
                    tareaa.terminado = hecho; // Le ponemos el estado exacto que tenía guardado.
                    listaTarea.add(tareaa);
                }
                lectorArchivo.close(); // Siempre cerramos el lector al terminar.
                System.out.println("--- Tareas cargadas correctamente desde el disco ---");
            }
        } catch (Exception e) {
            // Si el archivo está mal escrito o falta algún dato, salta aquí.
            System.out.println("Todavía no hay archivo de guardado o está corrupto.");
        }

        int obcion = 0;

        // BUCLE PRINCIPAL: Mantiene el programa vivo hasta que el usuario elija salir (5).
        while (obcion != 5) {
            System.out.println("---- MENU ----");
            System.out.println("1. Añadir tarea");
            System.out.println("2. Ver tareas pendientes");
            System.out.println("3. Marcar tarea echa");
            System.out.println("4. Filtrar por prioridad");
            System.out.println("5. Salir");

            obcion = leer.nextInt();
            leer.nextLine(); // Limpieza de buffer para no saltarse el próximo nextLine().

            switch (obcion) {
                case 1: // --- AÑADIR TAREA ---
                    System.out.println("¿Que tarea deseas añadir?");
                    String tarea = leer.nextLine();
                    System.out.println("¿Fecha limite?");
                    String fecha = leer.nextLine();
                    System.out.println("¿Nivel prioridad del 1-5?");
                    int necesidad = leer.nextInt();
                    leer.nextLine();

                    // Comprobación de duplicados (equalsIgnoreCase ignora mayúsculas/minúsculas).
                    boolean repetido = false;
                    for(int i = 0; i < listaTarea.size(); i++){
                        if (tarea.equalsIgnoreCase(listaTarea.get(i).descripcion)) {
                            repetido = true;
                            break;
                        }
                    }

                    if (repetido) {
                        System.out.println("Ya tienes esta tarea añadida.");
                    } else {
                        listaTarea.add(new Tarea(tarea, fecha, necesidad));
                        System.out.println("Tarea añadida correctamente");
                        
                        // GUARDADO EN MODO "APPEND" (Añadir al final):
                        try {
                            // El 'true' indica que no borre lo que ya hay, sino que añada abajo.
                            FileWriter escribirArchivo = new FileWriter("mis_tareas.txt", true);
                            PrintWriter imprimirEscrito = new PrintWriter(escribirArchivo);
                            // Escribimos los datos separados por ";" para que el split() pueda leerlos luego.
                            imprimirEscrito.println(tarea + ";" + fecha + ";" + necesidad + ";false");
                            imprimirEscrito.close();
                        } catch (IOException error) {
                            System.out.println("Error al guardar en el archivo " + error.getMessage());
                        }
                    }
                    break;

                case 2: // --- VER PENDIENTES ---
                    System.out.println("--- Estas son tus Tareas pendientes ---");
                    if (listaTarea.isEmpty()) {
                        System.out.println("No tienes tareas pendientes");
                    } else {
                        for(int i = 0; i < listaTarea.size(); i++){
                            Tarea pendientes = listaTarea.get(i);
                            // Solo mostramos tareas cuyo estado 'terminado' sea falso.
                            if (pendientes.terminado == false) { 
                                System.out.println("Descripcion: " + pendientes.descripcion +
                                                   " | Fecha Limite: " + pendientes.fechaLimite +
                                                   " | Prioridad " + pendientes.prioridad);
                            }
                        }
                    }
                    break;

                case 3: // --- MARCAR COMO HECHA ---
                    System.out.println("Que tarea deseas marcar terminado.");
                    String quitar = leer.nextLine();
                    boolean encontrado = false;

                    for(int i = 0; i < listaTarea.size(); i++){
                        Tarea terminar = listaTarea.get(i);
                        if (terminar.descripcion.equalsIgnoreCase(quitar)) {
                            terminar.terminado = true; // Cambiamos el estado en la memoria RAM.
                            encontrado = true;
                            System.out.println("Tarea " + terminar.descripcion + " marcado como terminado.");
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("No se encontro ninguna tarea con ese nombre");
                    } else {
                        // REESCRITURA TOTAL: Como un estado ha cambiado, borramos el archivo 
                        // y volvemos a escribir TODA la lista con los nuevos valores.
                        try {
                            // Al no poner 'true', el FileWriter limpia el archivo por completo.
                            FileWriter actualizar = new FileWriter("mis_tareas.txt"); 
                            PrintWriter imprimirEscrito = new PrintWriter(actualizar);

                            // Bucle For-Each: Por cada objeto 'tareaa' dentro de 'listaTarea'...
                            for (Tarea tareaa : listaTarea) {
                                imprimirEscrito.println(tareaa.descripcion + ";" + tareaa.fechaLimite + 
                                                        ";" + tareaa.prioridad + ";" + tareaa.terminado);
                            }
                            imprimirEscrito.close();
                            System.out.println("Archivo de guardado actualizado con éxito.");
                        } catch (IOException error) {
                            System.out.println("Error al actualizar el archivo: " + error.getMessage());
                        }
                    }
                    break;

                case 4: // --- FILTRO POR PRIORIDAD ---
                    System.out.println("¿Que taras deseas ver por prioridad del 1-5?");
                    int filtrar = leer.nextInt();
                    if (listaTarea.isEmpty()) {
                        System.out.println("No tienes ninga tarea asignada aun");
                    } else {
                        System.out.println("--- Sus tareas con esta prioridad son ---");
                        for(int i = 0; i < listaTarea.size(); i++){
                            Tarea priorida = listaTarea.get(i);
                            // Filtramos: Que sea igual o mayor al número pedido Y que no esté terminada.
                            if (priorida.prioridad >= filtrar && priorida.terminado == false) {
                                System.out.println("Tarea: " + priorida.descripcion);
                                System.out.println("Fecha: " + priorida.fechaLimite);
                                System.out.println("Prioridad: " + priorida.prioridad);
                                System.out.println("--------------------");
                            }
                        }
                    }
                    break;

                case 5:
                    System.out.println("Saliendo...");
                    break;
        
                default:
                    System.out.println("Selección erronea, selecciona otra distinta");
                    break;
            }
        }
    }
}