public class Visitante extends Thread {
    ParqueDiversiones parqueDeLaCosta;

    public Visitante(ParqueDiversiones parque) {
        parqueDeLaCosta = parque;
    }

    public void run() {
        try {
            parqueDeLaCosta.usarComedor();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
