import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Tren {
    private BlockingQueue<String> colaTren;
    private int pasajeros, max, minutos;
    private Semaphore semaforoTren, semaforoBajada;

    public Tren(int capacidad) {
        colaTren = new ArrayBlockingQueue<>(capacidad);
        this.pasajeros = 0;
        max = capacidad;
        minutos = 0;
        semaforoTren = new Semaphore(1);
        semaforoBajada = new Semaphore(0);
    }

    public int getMinutos() {
        return minutos;
    }

    public void reiniciarReloj() {
        minutos = 0;
    }

    public void incrementar() {
        minutos++;
    }

    public int getPasajeros() {
        return pasajeros;
    }

    public int getMax() {
        return max;
    }

    public void esperarTren() throws InterruptedException {
        colaTren.put("Agrega nuevo visitante");
        System.out.println(Thread.currentThread().getName() + " esta esperando el tren");

    }

    public void abordar() throws InterruptedException {
        if (!vacia() && pasajeros <= max) {
            // mientras que haya gente en la cola la sube al tren
            // con esa condicion evito que el hilo Maquinista quede en deadlock
            semaforoTren.acquire();
            colaTren.take();
            pasajeros++;
            System.out.println("Pasajero subio. pasajeros: " + pasajeros);
            semaforoTren.release();
        }
        if (pasajeros == max) {
            hacerRecorrido();// si el tren ya esta lleno hace un recorrido
        }
    }

    public void hacerRecorrido() throws InterruptedException {
        semaforoTren.acquire();
        System.out.println("El tren parte de la estacion");
        Thread.sleep(5000);
        // termina el recorrido.
        reiniciarReloj();// reinicia el cronometro de 5 minutos
        System.out.println("Finaliza el recorrido");
        semaforoTren.release();// libera el tren
        semaforoBajada.release();// libera la pajada para los pasajeros
    }

    public void bajarTren() throws InterruptedException {
        semaforoBajada.acquire();
        System.out.println(Thread.currentThread().getName() + " bajo del tren");
        pasajeros--;
        semaforoBajada.release();
    }

    public boolean trenLleno() {
        return pasajeros >= max;
    }

    public boolean vacia() {
        return colaTren.isEmpty();
    }
}
