
public class Encargado extends Thread {
    private JuegosDePremios juegos;

    public Encargado(JuegosDePremios j) {
        juegos = j;
    }

    public void run() {
        while (true) {
            try {
                juegos.darFicha();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
