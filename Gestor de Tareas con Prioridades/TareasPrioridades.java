import java.util.Scanner;
import java.util.ArrayList;

// CLASE TAREA: Es el molde que define qué datos tiene cada tarea
class Tarea {
    String descripcion;
    String fechaLimite;
    int prioridad;
    boolean terminado; // Estado de la tarea (true = hecha, false = pendiente)

    // CONSTRUCTOR: Se ejecuta al hacer "new Tarea(...)". 
    // Sirve para dar los valores iniciales al objeto.
    public Tarea(String descripcion, String fechaLimite, int prioridad) {
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.prioridad = prioridad;
        this.terminado = false; // Todas las tareas nacen sin terminar por defecto
    }
}

public class TareasPrioridades {

    public static void main(String[] args) {
        
        Scanner leer = new Scanner(System.in);
        // ARRAYLIST: Nuestro almacén central de objetos tipo Tarea
        ArrayList<Tarea> listaTarea = new ArrayList<>();

        int obcion = 0;

        // BUCLE PRINCIPAL: Se repite hasta que el usuario pulsa 5
        while (obcion != 5) {
            System.out.println("---- MENU ----");
            System.out.println("1. Añadir tarea");
            System.out.println("2. Ver tareas pendientes");
            System.out.println("3. Marcar tarea echa");
            System.out.println("4. Filtrar por prioridad");
            System.out.println("5. Salir");

            obcion = leer.nextInt();
            leer.nextLine(); // LIMPIEZA DE BUFFER: Evita que el siguiente nextLine() se salte

            switch (obcion) {
                case 1: // AÑADIR TAREA
                    System.out.println("¿Que tarea deseas añadir?");
                    String tarea = leer.nextLine();

                    System.out.println("¿Fecha limite?");
                    String fecha = leer.nextLine();

                    System.out.println("¿Nivel prioridad del 1-5?");
                    int necesidad = leer.nextInt();
                    leer.nextLine(); // Otra limpieza tras leer un número

                    // Comprobamos si la tarea ya existe para no duplicar
                    boolean repetido = false;
                    for(int i = 0; i < listaTarea.size(); i++){
                        // Usamos .descripcion para mirar dentro del objeto
                        if (tarea.equalsIgnoreCase(listaTarea.get(i).descripcion)) {
                            repetido = true;
                            break;
                        }
                    }

                    if (repetido) {
                        System.out.println("Ya tienes esta tarea añadida.");
                    } else {
                        // Creamos el objeto y lo metemos en la lista
                        listaTarea.add(new Tarea(tarea, fecha, necesidad));
                        System.out.println("Tarea añadida correctamente");
                    }
                    break;

                case 2: // VER PENDIENTES
                    System.out.println("--- Estas son tus Tareas pendientes ---");
                    if (listaTarea.isEmpty()) {
                        System.out.println("No tienes tareas pendientes");
                    } else {
                        for(int i = 0; i < listaTarea.size(); i++){
                            Tarea pendientes = listaTarea.get(i);
                            // FILTRO: Solo mostramos si el boolean 'terminado' es false
                            if (pendientes.terminado == false) { 
                                System.out.println("Descripcion: " + pendientes.descripcion +
                                                   " | Fecha Limite: " + pendientes.fechaLimite +
                                                   " | Prioridad " + pendientes.prioridad);
                            }
                        }
                    }
                    break;

                case 3: // MARCAR COMO COMPLETADA
                    System.out.println("Que tarea deseas marcar terminado.");
                    String quitar = leer.nextLine();
                    boolean encontrado = false;

                    for(int i = 0; i < listaTarea.size(); i++){
                        Tarea terminar = listaTarea.get(i); // "Agarramos" la tarea

                        if (terminar.descripcion.equalsIgnoreCase(quitar)) {
                            terminar.terminado = true; // Cambiamos su estado interno
                            encontrado = true;
                            System.out.println("Tarea " + terminar.descripcion + " marcado como terminado.");
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("No se encontro ninguna tarea con ese nombre");
                    }
                    break;

                case 4: // FILTRAR POR PRIORIDAD
                    System.out.println("¿Que taras deseas ver por prioridad del 1-5?");
                    int filtrar = leer.nextInt();
                
                    if (listaTarea.isEmpty()) {
                        System.out.println("No tienes ninga tarea asignada aun");
                    } else {
                        System.out.println("--- Sus tareas con esta prioridad son ---");
                        for(int i = 0; i < listaTarea.size(); i++){
                            Tarea priorida = listaTarea.get(i);
                            
                            // FILTRO DOBLE: Que tenga la prioridad Y que no esté terminada
                            if (priorida.prioridad >= filtrar && priorida.terminado == false) {
                                System.out.println("Tarea: " + priorida.descripcion);
                                System.out.println("Fecha: " + priorida.fechaLimite);
                                System.out.println("Prioridad: " + priorida.prioridad);
                                System.out.println("--------------------");
                            }
                        }
                    }
                    break;

                case 5: // SALIR
                    System.out.println("Saliendo...");
                    break;
        
                default: // ERROR DE OPCIÓN
                    System.out.println("Selección erronea, selecciona otra distinta");
                    break;
            }
        }
    }
}