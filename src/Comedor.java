import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Comedor {
    int totalMesas;
    int sillas;
    Semaphore semaforoMesas;
    CyclicBarrier barreraMesa;

    public Comedor() {
        totalMesas = 5;// maximo de mesas
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
            barreraMesa.await(5, TimeUnit.SECONDS); // Esperar a que la mesa esté llena o que se agote el tiempo
        } catch (java.util.concurrent.TimeoutException e) {
            System.out.println("Los visitantes se cansaron de esperar y se fueron");
        } catch (Exception e) {
            System.out.println("Error en la sincronización de la mesa: " + e.getMessage());
        }
    }

    public void salir() {
        System.out.println(Thread.currentThread().getName() + " salio del comedor");
        semaforoMesas.release();
    }
}
