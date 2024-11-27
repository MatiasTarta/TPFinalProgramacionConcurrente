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
            String horaFormateada = String.format("%02d:%02d", hora, minutos);
            etiquetaHora.setText(horaFormateada);
            minutos += 1;
            try {
                Thread.sleep(1500);// los ms son 1 minutos
            } catch (Exception e) {
                // TODO: handle exception
            }
            if (minutos >= 60) {
                minutos = 0;
                hora += 1;
                if (hora >= 24) {
                    hora = 0; // Resetea a la medianoche
                }
            }

        }
    }

    public int getHora() {
        return this.hora;
    }
}