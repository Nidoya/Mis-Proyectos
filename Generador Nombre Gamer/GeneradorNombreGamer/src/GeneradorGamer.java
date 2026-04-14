import java.util.Scanner;


public class GeneradorGamer {

    public static void main(String[] args){

        Scanner leer = new Scanner(System.in);

        System.out.println("Dime tu nombre");
        String nombre = leer.nextLine();

        System.out.println("Dime tu apellido");
        String apellido = leer.nextLine();

        System.out.println("Dame tu año de nacimiento");
        String año = leer.nextLine();

        String parteNombre = nombre.substring(0,3);
        String parteApellido = apellido.substring(0,3);
        String parteAño = año.substring(2,4);

        String nombreFinal = (parteNombre + parteApellido +parteAño).toUpperCase();

        System.out.println("Tu nombre Gamer es: " + nombreFinal);
    }
}
