
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Comedor {
    int totalMesas;
    int sillas;
    Semaphore semaforoMesas;
    CyclicBarrier barreraMesa;

    public Comedor() {
        totalMesas = 10;// maximo de mesas
        sillas = 4;// constante,cantidad personas por mesa
        semaforoMesas = new Semaphore(totalMesas * sillas);
        barreraMesa = new CyclicBarrier(sillas, () -> System.out.println("La mesa se llena, todos empiezan a comer"));
    }

    public boolean entrarComedor() {
        boolean resultado = semaforoMesas.tryAcquire();
        if (resultado) {
            System.out.println(Thread.currentThread().getName() + " entró al comedor");
        } else {
            System.out.println(Thread.currentThread().getName() + " no pudo entrar al comedor porque estaba lleno");
        }
        return resultado;
    }

    public void sentarse() throws InterruptedException {
        try {
            System.out.println(Thread.currentThread().getName() + " se sentó en la mesa.");
            barreraMesa.await(); // Esperar a que la mesa esté llena
        } catch (Exception e) {
            System.out.println("Error en la sincronización de la mesa: " + e.getMessage());
        }
    }

    public void salir() {
        System.out.println(Thread.currentThread().getName() + " salio del comedor");
        semaforoMesas.release();
    }
}
