
public class Visitante extends Thread {
    ParqueDiversiones parqueDeLaCosta;
    private int puntos;

    public Visitante(String nombre, ParqueDiversiones parque) {
        super(nombre);
        parqueDeLaCosta = parque;
        puntos = 0;
    }

    public void run() {
        try {
            parqueDeLaCosta.entrar();
            parqueDeLaCosta.subirAlTren();
            // parqueDeLaCosta.usarComedor();
            // puntos += parqueDeLaCosta.jugarPorPremios();
            // parqueDeLaCosta.salir();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo básico de excepciones
        }
    }
}
