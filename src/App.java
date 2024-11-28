import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione el tipo de visitante:");
        System.out.println("1: Uso del Tren");
        System.out.println("2: Uso del comedor");
        System.out.println("3: Jugar por premios");
        System.out.println("4: Hacer todas las actividades");
        System.out.println("5: Uso de la sala de realidad virtual");

        int tipoVisitante = scanner.nextInt();

        System.out.println("Ingrese la cantidad de visitantes por tipo:");
        int cantidadVisitantes = scanner.nextInt();

        ParqueDiversiones parqueDeLaCosta = new ParqueDiversiones(100);
        parqueDeLaCosta.reloj();

        Visitante[] visitantes = new Visitante[cantidadVisitantes];

        switch (tipoVisitante) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                for (int i = 0; i < visitantes.length; i++) {
                    visitantes[i] = new Visitante("Visitante-" + (i + 1), parqueDeLaCosta, tipoVisitante);
                    visitantes[i].start();
                }
                break;
            default:
                System.out.println("Opción no válida");
        }

        scanner.close();
    }
}
