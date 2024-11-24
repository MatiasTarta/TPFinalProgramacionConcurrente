import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class App {
    public static void main(String[] args) throws Exception {
        // al principio va el reloj del programa
        JFrame ventana = new JFrame("Reloj del Parque");
        JLabel etiquetaHora = new JLabel();
        etiquetaHora.setFont(new Font("Arial", Font.BOLD, 40));
        etiquetaHora.setHorizontalAlignment(SwingConstants.CENTER);

        ventana.add(etiquetaHora);
        ventana.setSize(300, 150);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);

        Reloj reloj = new Reloj(etiquetaHora);
        reloj.start();

        ParqueDiversiones parqueDeLaCosta = new ParqueDiversiones();
        Visitante visitante1 = new Visitante("Visitante-1", parqueDeLaCosta);
        Visitante visitante2 = new Visitante("Visitante-2", parqueDeLaCosta);
        Visitante visitante3 = new Visitante("Visitante-3", parqueDeLaCosta);
        Visitante visitante4 = new Visitante("Visitante-4", parqueDeLaCosta);
        Visitante visitante5 = new Visitante("Visitante-5", parqueDeLaCosta);

        // Iniciar los hilos
        visitante1.start();
        visitante2.start();
        visitante3.start();
        visitante4.start();
        visitante5.start();
    }
}
