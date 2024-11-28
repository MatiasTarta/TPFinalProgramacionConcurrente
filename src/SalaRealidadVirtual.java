public class SalaRealidadVirtual {
    int visores, manoplas, base, visoresEnUso, manoplasEnUso, basesEnUso;

    public SalaRealidadVirtual(int cantEquipos) {
        visores = cantEquipos;
        manoplas = 2 * cantEquipos;
        base = cantEquipos;
        visoresEnUso = 0;
        manoplasEnUso = 0;
        basesEnUso = 0;
    }

    public synchronized void entrarASalaRV() throws InterruptedException {
        while (visores == visoresEnUso && manoplas == manoplasEnUso && base == basesEnUso) {
            this.wait();
        }
        // System.out.println("Un set de equipo de RV es liberado.");
        System.out.println(Thread.currentThread().getName() + " entro al simulador");
        visoresEnUso++;
        manoplasEnUso += 2;
        basesEnUso++;
    }

    public synchronized void salirSalaRV() {
        System.out.println(Thread.currentThread().getName() + " salio de la sala de RV");
        visoresEnUso--;
        basesEnUso--;
        manoplasEnUso -= 2;
        this.notify();
    }

}
