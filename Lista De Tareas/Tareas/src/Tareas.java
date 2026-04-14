import java.util.ArrayList;
import java.util.Scanner;


class Tarea{

    String nombre;
    boolean completado;


    Tarea(String nombre){
        this.nombre = nombre;
        this.completado = false;
    }

}
public class Tareas{
    public static void main (String[]args){

        ArrayList<Tarea> listaDeTareas = new ArrayList<>();
        Scanner leer = new Scanner(System.in);

        int opcion = 0;

        while (opcion != 3) {
            System.out.println("\n--- GESTOR DE TAREAS ---");
            System.out.println("1. Añadir tarea");
            System.out.println("2. Ver tareas y completar");
            System.out.println("3. Salir");
            System.out.println("Elige una opcion");
            opcion = leer.nextInt();
            leer.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Añadir tarea");
                    String nombre = leer.nextLine();
                    listaDeTareas.add(new Tarea(nombre));
                    System.out.println("Tarea añadida");
                    break;

                case 2:
                    System.out.println("\n Tus tareas actuales");
                    for (int i= 0; i < listaDeTareas.size(); i++) {
                        Tarea tareaAcual = listaDeTareas.get(i);

                        String estado;

                        if (tareaAcual.completado == true) {
                            estado = "[HECHA]";
                        }
                        else{
                            estado = "[PENDIENTE]";
                        }
                        System.out.println(i + ". " + tareaAcual.nombre + " " + estado);
                    }

                    if (!listaDeTareas.isEmpty()){
                        System.out.println("\n¿Quieres marcar alguna tarea como echa y eliminarla? Si no marca -1");
                        int seleccionar = leer.nextInt();
                        if (seleccionar >= 0 && seleccionar < listaDeTareas.size()){
                            System.out.println("Eliminando tarea: " + listaDeTareas.get(seleccionar).nombre);
                            listaDeTareas.remove(seleccionar);
                            System.out.println("Tarea eliminada");
                        }

                    }
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Obcion no válida");
                    break;
            }

        }


    }

}