import java.util.Scanner;
import java.util.ArrayList;

public class Compras {

    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);
        ArrayList <String> cestaCompra = new ArrayList<>();

        int opcion = 0;

        while (opcion != 5) {
            System.out.println("--- Cesta de la compra ---");
            System.out.println("1. Añadir producto");
            System.out.println("2. Ver cesta completa");
            System.out.println("3. Buscar producto");
            System.out.println("4. Quitar producto");
            System.out.println("5. Salir");
            opcion = leer.nextInt();
            leer.nextLine();


            switch (opcion) {
                case 1:
                    System.out.println("Producto para añadir");
                    String producto = leer.nextLine();
                    
                    boolean repetido = false;

                    for (int i = 0; i < cestaCompra.size(); i++) {
                        if (producto.equals(cestaCompra.get(i))) {
                            
                            repetido = true;
                            break;
                        }
                    }

                    
                    if (repetido) {
                        System.out.println("Ya tienes este producto en tu cesta");
                    }
                    else{

                        cestaCompra.add(producto);

                        System.out.println("Producto añadido");

                    }                 
                    break;

                    
                case 2:
                    System.out.println("--- Contenido de la cesta ---");

                    if (cestaCompra.isEmpty()) {
                        System.out.println("Cesta vacia lo siento.");
                    }
                    else{
                        for (int i = 0; i < cestaCompra.size(); i++) {
                            String cesta = cestaCompra.get(i);
                            System.out.println(i + ". " + cesta);                   
                        }
                    }
                    
                    break;
                case 3:
                    System.out.println("¿Que quieres buscar?");
                    String buscar = leer.nextLine();

                    boolean encontrado = false;

                    for (int i= 0; i <cestaCompra.size(); i++){
                        if (buscar.equalsIgnoreCase(cestaCompra.get(i))) {
                            encontrado = true;
                            break;
                        }
                    }

                    if (encontrado) {
                        System.out.println("Se encontra en su cesta el producto: " + buscar);
                        
                    }
                    else{
                        System.out.println("No tiene en su cesta ese producto disculpa.");
                    }
                    
                    break;
                case 4:
                    System.out.println("¿Que deseas quitar de la cesta?");
                    String quitar = leer.nextLine();
                    
                    if (cestaCompra.remove(quitar)) {
                        System.out.println("¡" + quitar + " ha sido eliminado con exito!");
                        
                    }
                    else{
                        System.out.println("No se puedo quitar " + quitar + " no existe en su cesta");
                    }
                    break;
                case 5:
                    System.out.println("Salir");
                    break;
            
                default:
                    System.out.println("Error inesperado pruebe otra opcion gracias");
                    break;
            }
        }




    }
}