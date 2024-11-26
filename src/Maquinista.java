public class Maquinista extends Thread {
    private Tren tren; // Instancia del Tren
    private int minutosPasados; // Contador interno de minutos

    public Maquinista(Tren tren) {
        this.tren = tren;
        this.minutosPasados = 0;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // tren.abordar();
                if (tren.getMinutos() == 5 && tren.vacia()) {
                    // si pasan los minutos parto el tren, cuando el tren se llena, la partida se
                    // hace desde la clase Tren
                    tren.hacerRecorrido();
                    tren.reiniciarReloj();
                }

                Thread.sleep(1500);// 1500 ms = 1 minuto en mi simulacion
                tren.incrementar();
            } catch (InterruptedException e) {
                System.out.println("El maquinista fue interrumpido: " + e.getMessage());
                break; // Salir del bucle si el hilo es interrumpido
            }
        }
    }

}
