public class ParqueDiversiones {
    Comedor restaurant;
    JuegosDePremios juegos;
    Encargado encargados[] = new Encargado[5];

    public ParqueDiversiones() {
        restaurant = new Comedor();
        juegos = new JuegosDePremios();
        for (int i = 0; i < encargados.length; i++) {
            encargados[i] = new Encargado(juegos);
            encargados[i].start();
        }
    }

    public void usarComedor() throws InterruptedException {
        boolean exito;
        exito = restaurant.entrarComedor();
        if (exito) {
            restaurant.sentarse();
            // comen
            restaurant.salir();
        } else {
            // comedor lleno, intenta otra cosa
        }
    }

    public int jugarPorPremios() {
        int puntos = 0;
        try {
            puntos = juegos.jugar();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " obtuvo: " + puntos + " puntos.");
        return puntos;
    }
}
