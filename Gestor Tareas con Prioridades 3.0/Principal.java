import java.util.Scanner;
import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        
        // CARGA INICIAL: Le pedimos al GestorArchivo que nos traiga los datos guardados
        ArrayList<Tarea> listaTarea = GestorArchivo.cargarTareas();

        int obcion = 0;
        // BUCLE PRINCIPAL: Mantiene la aplicación encendida
        while (obcion != 5) {
            System.out.println("\n---- MENU ----");
            System.out.println("1. Añadir tarea");
            System.out.println("2. Ver tareas pendientes");
            System.out.println("3. Marcar tarea echa");
            System.out.println("4. Filtrar por prioridad");
            System.out.println("5. Salir");

            obcion = leer.nextInt();
            leer.nextLine(); // Limpiar el buffer para evitar errores con nextLine()

            switch (obcion) {
                case 1: // --- LÓGICA DE AÑADIR ---
                    System.out.println("¿Que tarea deseas añadir?");
                    String tarea = leer.nextLine();
                    System.out.println("¿Fecha limite?");
                    String fecha = leer.nextLine();
                    System.out.println("¿Nivel prioridad del 1-5?");
                    int necesidad = leer.nextInt();
                    leer.nextLine();

                    // Validación de duplicados recorriendo la lista actual
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
                        // Creamos el objeto y lo añadimos a la RAM
                        listaTarea.add(new Tarea(tarea, fecha, necesidad));
                        System.out.println("Tarea añadida correctamente");
                        // Ordenamos al Gestor que actualice el archivo .txt
                        GestorArchivo.guardarTareas(listaTarea);
                    }
                    break;

                case 2: // --- LÓGICA DE VISUALIZACIÓN ---
                    System.out.println("--- Estas son tus Tareas pendientes ---");
                    if (listaTarea.isEmpty()) {
                        System.out.println("No tienes tareas pendientes");
                    } else {
                        for(Tarea pendientes : listaTarea){
                            // Filtramos para mostrar solo las que no están terminadas
                            if (!pendientes.isTerminado()) { 
                                System.out.println("Descripcion: " + pendientes.getDescripcion() +
                                                   " | Fecha: " + pendientes.getFechaLimite() +
                                                   " | Prioridad: " + pendientes.getPrioridad());
                            }
                        }
                    }
                    break;

                case 3: // --- LÓGICA DE COMPLETAR ---
                    System.out.println("Que tarea deseas marcar terminado.");
                    String quitar = leer.nextLine();
                    boolean encontrado = false;

                    for(Tarea terminar : listaTarea){
                        if (terminar.getDescripcion().equalsIgnoreCase(quitar)) {
                            // Cambiamos el estado mediante el Setter
                            terminar.setTerminado(true); 
                            encontrado = true;
                            System.out.println("Tarea completada.");
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("No se encontro ninguna tarea.");
                    } else {
                        // Importante: Guardamos tras el cambio para no perderlo al cerrar
                        GestorArchivo.guardarTareas(listaTarea);
                    }
                    break;

                case 4: // --- LÓGICA DE FILTRADO ---
                    System.out.println("¿Que taras deseas ver por prioridad del 1-5?");
                    int filtrar = leer.nextInt();
                    // Aquí iría el bucle for similar al Case 2 pero con el filtro de prioridad
                    break;
            }
        }
        System.out.println("¡Hasta luego!");
    }
}