public class ParqueDiversiones {
    Comedor restaurant;

    public ParqueDiversiones() {
        restaurant = new Comedor();
    }

    public void usarComedor() throws InterruptedException {
        boolean exito;
        exito = restaurant.entrarComedor();
        if (exito) {
            restaurant.sentarse();
            // restaurant.comer();
        }
    }
}
