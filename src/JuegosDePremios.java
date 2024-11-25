import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

public class JuegosDePremios {
    private final Exchanger<String> ficha;
    private final Semaphore semEntrada;

    public JuegosDePremios() {
        ficha = new Exchanger<String>();
        semEntrada = new Semaphore(0);// asegura que un visitante notifique a un encargado para cambiar fichas
    }

    public int jugar() throws InterruptedException {
        semEntrada.release(); // notifica al empleado para poder recibir una ficha
        ficha.exchange(Thread.currentThread().getName() + " cambia una ficha");
        int randomSleep = (int) (Math.random() * 10) * 1000;
        Thread.sleep(randomSleep); // simula tiempo de juego
        return randomSleep;
    }

    public void darFicha() throws InterruptedException {
        // metodo ejecutado por los encargados del area
        semEntrada.acquire();
        String pantalla = ficha.exchange("");
        System.out.println(pantalla);
    }
}