import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario que seleccione el tipo de visitante
        System.out.println("Seleccione el tipo de visitante:");
        System.out.println("1: Uso del Tren");
        System.out.println("2: Uso del comedor");
        System.out.println("3: Jugar por premios");
        System.out.println("4: Hacer todas las actividades");

        int tipoVisitante = scanner.nextInt();

        // Solicitar al usuario la cantidad de visitantes por tipo
        System.out.println("Ingrese la cantidad de visitantes por tipo:");
        int cantidadVisitantes = scanner.nextInt();

        // Crear parque y comenzar el reloj
        ParqueDiversiones parqueDeLaCosta = new ParqueDiversiones(100);
        parqueDeLaCosta.reloj();

        // Crear los arrays de visitantes
        Visitante[] visitantes = new Visitante[cantidadVisitantes];

        // Lógica para crear visitantes y asignarles las actividades según el tipo
        // elegido
        switch (tipoVisitante) {
            case 1:
                for (int i = 0; i < visitantes.length; i++) {
                    visitantes[i] = new Visitante("Visitante-" + (i + 1), parqueDeLaCosta, 1);
                    visitantes[i].start();
                }
                break;
            case 2:
                for (int i = 0; i < visitantes.length; i++) {
                    visitantes[i] = new Visitante("Visitante-" + (i + 1), parqueDeLaCosta, 2);
                    visitantes[i].start();
                }
                break;
            case 3:
                for (int i = 0; i < visitantes.length; i++) {
                    visitantes[i] = new Visitante("Visitante-" + (i + 1), parqueDeLaCosta, 3);
                    visitantes[i].start();
                }
                break;
            case 4:
                for (int i = 0; i < visitantes.length; i++) {
                    visitantes[i] = new Visitante("Visitante-" + (i + 1), parqueDeLaCosta, 4);
                    visitantes[i].start();
                }
                break;
            default:
                System.out.println("Opción no válida");
        }

        // Cerrar el scanner
        scanner.close();
    }
}
