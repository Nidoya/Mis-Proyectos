import java.util.Scanner;


public class Vigilante {



public static void main(String[] args){

    Scanner leer = new Scanner(System.in);
    double saldo = 20.0;
    int opcion = 0;

    while (opcion != 4){
        System.out.println("---Qué quieres hacer---");
        System.out.println("1. Comprar Billete");
        System.out.println("2. Ver saldo actual");
        System.out.println("3. Recargar Tarjeta");
        System.out.println("4. Salir");
        opcion = leer.nextInt();
        leer.nextLine();

        switch (opcion){
            case 1:
                double precioBillete = 0;
                System.out.println("¿Precio del billete?");
                precioBillete = leer.nextDouble();
                leer.nextLine();

                if (precioBillete <= saldo){
                    saldo -= precioBillete;
                    System.out.println("Billete comprado. Nuevo saldo disponible: " + saldo + "€");
                }
                else{
                    System.out.println("Saldo insuficiente, por favor recargue su tarjeta.");
                }
                break;


            case 2:
                System.out.println("Su saldo actual es de: " + saldo + "€");
                break;


            case 3:
                double recarga = 0;
                System.out.println("¿Cuanto quieres recargar?");
                recarga = leer.nextDouble();
                leer.nextLine();

                saldo += recarga;

                System.out.println("¡Recarga exitosa!. Su nuevo saldo es: " + saldo + "€");
                break;


            default:
                System.out.println("Sección no valida");
                break;
        }



    }


}

}
