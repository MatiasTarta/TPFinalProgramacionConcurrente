public class Visitante extends Thread {
    private ParqueDiversiones parqueDeLaCosta;
    private int puntos;
    private int tipo; // Define qué actividad realizará el visitante

    public Visitante(String nombre, ParqueDiversiones parque, int tipo) {
        super(nombre);
        this.parqueDeLaCosta = parque;
        this.puntos = 0;
        this.tipo = tipo;
    }

    @Override
    public void run() {
        try {
            parqueDeLaCosta.entrar();

            switch (tipo) {
                case 1:
                    parqueDeLaCosta.subirAlTren();
                    break;
                case 2:
                    parqueDeLaCosta.usarComedor();
                    break;
                case 3:
                    puntos += parqueDeLaCosta.jugarPorPremios();
                    break;
                case 4:
                    parqueDeLaCosta.subirAlTren();
                    Thread.sleep(4100);
                    parqueDeLaCosta.usarComedor();
                    Thread.sleep(3000);
                    puntos += parqueDeLaCosta.jugarPorPremios();
                    break;
                default:
                    System.out.println(getName() + " no realizó ninguna actividad.");
            }

            parqueDeLaCosta.salir();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo básico de excepciones
        }
    }
}
