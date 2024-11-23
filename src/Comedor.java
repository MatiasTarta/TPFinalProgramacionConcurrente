import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Comedor {
    int totalMesas;
    int sillas;
    Semaphore semaforoMesas;
    BlockingQueue<String>[] mesas;

    public Comedor() {
        totalMesas = 10;// maximo de mesas
        sillas = 3;// constante,cantidad personas por mesa
        semaforoMesas = new Semaphore(totalMesas * sillas);

    }

    public boolean entrarComedor() throws InterruptedException {
        boolean resultado = false;
        if (semaforoMesas.tryAcquire(1)) {
            System.out.println(Thread.currentThread().getName() + " entro al comedor");
            resultado = true;// puede entrar al comedor
            semaforoMesas.acquire();
        } else {
            System.out.println(Thread.currentThread().getName() + " no pudo entrar al comedor porque estaba lleno");
        }
        return resultado;
    }

    public void sentarse() throws InterruptedException {

    }

    public void levantarse() {

    }

    public void salir() {
        System.out.println(Thread.currentThread().getName() + " salio del comedor");
        semaforoMesas.release();
    }
}
