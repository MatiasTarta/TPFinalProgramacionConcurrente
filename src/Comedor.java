import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Comedor {
    int totalMesas;
    int sillas;
    Semaphore semaforoMesas;
    BlockingQueue<String>[] mesas;

    public Comedor() {
        totalMesas = 10;// maximo de mesas
        sillas = 4;// constante,cantidad personas por mesa
        semaforoMesas = new Semaphore(totalMesas * sillas);
        mesas = new BlockingQueue[totalMesas];
        for (int i = 0; i < totalMesas; i++) {
            mesas[i] = new ArrayBlockingQueue<>(sillas);
        }
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
        for (int i = 0; i < totalMesas; i++) {
            if (mesas[i].remainingCapacity() > 0) { // Si hay espacio en la mesa
                mesas[i].put(Thread.currentThread().getName()); // El hilo se sienta
                System.out.println(Thread.currentThread().getName() + " se sentó en la mesa " + (i + 1));

                if (mesas[i].size() == sillas) {
                    System.out.println("Mesa " + (i + 1) + " está llena. Los hilos en esta mesa están comiendo.");
                }
                break; // Sale del bucle cuando se ha sentado en la mesa
            }
        }
    }

    public void levantarse() {
        // busca en que mesa estaba el hilo y liberarlo
        for (int i = 0; i < totalMesas; i++) {
            if (mesas[i].remove(Thread.currentThread().getName())) {
                System.out.println(Thread.currentThread().getName() + " se levantó de la mesa " + i + 1);
                break; // Una vez liberado el hilo de la mesa, salir del bucle
            }
        }
    }

    public void salir() {
        System.out.println(Thread.currentThread().getName() + " salio del comedor");
        semaforoMesas.release();
    }
}
