import java.util.ArrayList;
import java.util.Scanner;

/**
 * 1. CLASE PRODUCTO (El Molde)
 * Definimos qué datos queremos que guarde cada "caja" de nuestro inventario.
 */
class Producto {
    String nombre;
    int cantidad;
    double precio;

    /**
     * CONSTRUCTOR
     * Es la función que se ejecuta al hacer 'new Producto'. 
     * Sirve para meter los datos en las variables de la clase usando 'this'.
     */
    public Producto(String nombre, int cantidad, double precio) {
        this.nombre = nombre;     // "Este nombre de la clase = nombre que viene de fuera"
        this.cantidad = cantidad;
        this.precio = precio;
    }
}

public class gestorInventario {

    public static void main(String[] args) {
        // --- HERRAMIENTAS ---
        Scanner leer = new Scanner(System.in);
        // Lista dinámica que guardará objetos completos (Clase Producto) en vez de solo textos.
        ArrayList<Producto> inventario = new ArrayList<>();
        
        int opcion = 0;

        // --- BUCLE PRINCIPAL ---
        // Se repetirá hasta que el usuario elija la opción 4 (Salir).
        while (opcion != 4) {
            System.out.println("\n   ---- MENU ----");
            System.out.println("1. Registrar Producto");
            System.out.println("2. Ver Inventario");
            System.out.println("3. Calcular valor total");
            System.out.println("4. Salir");
            
            opcion = leer.nextInt();
            leer.nextLine(); // Limpieza de buffer (para poder leer el siguiente String sin fallos).

            switch (opcion) {
                case 1:
                    // REGISTRO DE DATOS
                    System.out.println("Nombre del producto:");
                    String nombre = leer.nextLine();

                    System.out.println("Cantidad del producto:");
                    int cantidad = leer.nextInt();

                    System.out.println("Precio del producto:");
                    double precio = leer.nextDouble();
                    leer.nextLine(); // Limpiamos el buffer tras el número

                    // CREACIÓN DEL OBJETO: Empaquetamos los 3 datos en un objeto Producto y lo añadimos a la lista.
                    inventario.add(new Producto(nombre, cantidad, precio));
                    System.out.println("Producto añadido correctamente.");
                    break;

                case 2:
                    // MOSTRAR DATOS
                    System.out.println("--- ESTE ES TU INVENTARIO ---");

                    if (inventario.isEmpty()) {
                        System.out.println("Inventario vacío.");
                    } else {
                        // Recorremos el ArrayList posición por posición.
                        for(int i = 0; i < inventario.size(); i++){
                            // Obtenemos el objeto Producto de la posición 'i' y lo llamamos 'mercancia'.
                            Producto mercancia = inventario.get(i);

                            // Accedemos a los datos internos del objeto usando el PUNTO (.).
                            System.out.println("Nombre: " + mercancia.nombre +
                                               " | Cantidad: " + mercancia.cantidad +
                                               " | Precio: " + mercancia.precio + "€");
                        }
                    }
                    break;

                case 3:
                    // CÁLCULO FINANCIERO
                    double totalGeneral = 0; // "La hucha" donde acumularemos los resultados.

                    for(int i = 0; i < inventario.size(); i++){
                        // Sacamos el producto para poder mirar su precio y cantidad.
                        Producto mercancia = inventario.get(i); 

                        // Calculamos el valor de este producto específico (precio * stock).
                        double valorMercancia = mercancia.precio * mercancia.cantidad;

                        // Lo sumamos a lo que ya teníamos en totalGeneral.
                        totalGeneral = totalGeneral + valorMercancia;
                    }

                    System.out.println(" --- VALOR DEL INVENTARIO ---");
                    System.out.println("El valor total es de: " + totalGeneral + "€");
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Entrada inválida, pruebe otra.");
                    break;
            }
        }
    }
}