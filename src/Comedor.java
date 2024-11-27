import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Comedor {
    int totalMesas;
    int sillas;
    Semaphore semaforoMesas;
    CyclicBarrier[] barrerasMesas;
    int[] asientosOcupados; // Contador de asientos ocupados por mesa
    ReentrantLock lockMesas; // Para garantizar acceso seguro al arreglo asientosOcupados

    public Comedor() {
        totalMesas = 5; // Máximo de mesas
        sillas = 4; // Constante, cantidad de personas por mesa
        semaforoMesas = new Semaphore(totalMesas * sillas);
        barrerasMesas = new CyclicBarrier[totalMesas];
        asientosOcupados = new int[totalMesas];
        lockMesas = new ReentrantLock();

        // Inicializamos las barreras, una para cada mesa
        for (int i = 0; i < totalMesas; i++) {
            int mesaId = i + 1; // Para identificar la mesa
            barrerasMesas[i] = new CyclicBarrier(sillas,
                    () -> System.out.println("La mesa " + mesaId + " se llena, todos empiezan a comer"));
        }
    }

    public boolean entrarComedor() {
        boolean resultado = semaforoMesas.tryAcquire();
        if (!resultado) {
            System.out.println(Thread.currentThread().getName() + " no pudo entrar al comedor porque estaba lleno");
        }
        return resultado;
    }

    public void sentarse() throws InterruptedException {
        boolean sentado = false;

        // Intentar encontrar una mesa disponible
        while (!sentado) {
            lockMesas.lock();
            try {
                for (int i = 0; i < totalMesas; i++) {
                    if (asientosOcupados[i] < sillas) { // Si hay lugar en esta mesa
                        asientosOcupados[i]++; // Ocupa un asiento
                        System.out.println(Thread.currentThread().getName() + " se sentó en la mesa " + (i + 1));
                        sentado = true;

                        // Sincronizar con la barrera de la mesa
                        try {
                            barrerasMesas[i].await(5, TimeUnit.SECONDS);
                        } catch (java.util.concurrent.TimeoutException e) {
                            System.out.println(
                                    "Los visitantes de la mesa " + (i + 1) + " se cansaron de esperar y se fueron");
                        } catch (Exception e) {
                            System.out.println(
                                    "Error en la sincronización de la mesa " + (i + 1) + ": " + e.getMessage());
                        } finally {
                            asientosOcupados[i]--; // Liberar el asiento cuando termine
                        }
                        break;
                    }
                }
            } finally {
                lockMesas.unlock();
            }

            if (!sentado) {
                // Espera un momento antes de intentar de nuevo
                Thread.sleep(100);
            }
        }
    }

    public void salir() {
        System.out.println(Thread.currentThread().getName() + " salió del comedor");
        semaforoMesas.release();
    }
}
