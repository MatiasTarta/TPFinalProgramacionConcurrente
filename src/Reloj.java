import javax.swing.JLabel;

public class Reloj extends Thread {
    private int hora;
    private int minutos;
    private JLabel etiquetaHora;

    public Reloj(JLabel etiquetaHora) {
        this.hora = 9;
        this.minutos = 0;
        this.etiquetaHora = etiquetaHora;
    }

    public void run() {
        while (true) {
            // Formatea la hora en formato HH:mm
            String horaFormateada = String.format("%02d:%02d", hora, minutos);
            etiquetaHora.setText(horaFormateada);

            // Avanza el tiempo
            minutos += 1;
            if (minutos >= 60) {
                minutos = 0;
                hora += 1;
                if (hora >= 24) {
                    hora = 0; // Resetea a la medianoche
                }
            }

            try {
                Thread.sleep(333); // (20 segundos / 60 minutos) ≈ 333 ms por minuto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}