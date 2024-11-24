
public class App {
    public static void main(String[] args) throws Exception {

        ParqueDiversiones parqueDeLaCosta = new ParqueDiversiones(100);
        parqueDeLaCosta.reloj();
        Visitante visitante1 = new Visitante("Visitante-1", parqueDeLaCosta);
        Visitante visitante2 = new Visitante("Visitante-2", parqueDeLaCosta);
        Visitante visitante3 = new Visitante("Visitante-3", parqueDeLaCosta);
        Visitante visitante4 = new Visitante("Visitante-4", parqueDeLaCosta);
        Visitante visitante5 = new Visitante("Visitante-5", parqueDeLaCosta);

        // Iniciar los hilos
        visitante1.start();
        visitante2.start();
        visitante3.start();
        visitante4.start();
        visitante5.start();
    }
}
