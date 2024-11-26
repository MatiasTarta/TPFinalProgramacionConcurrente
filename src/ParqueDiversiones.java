import java.awt.Font;
import java.util.concurrent.Semaphore;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ParqueDiversiones {
    Comedor restaurant;
    JuegosDePremios juegos;
    Encargado encargados[] = new Encargado[5];
    Semaphore molinete;
    Maquinista maquina;
    Tren locomotor;

    public ParqueDiversiones(int capacidad) {
        restaurant = new Comedor();
        juegos = new JuegosDePremios();
        molinete = new Semaphore(capacidad);
        locomotor = new Tren(3);
        for (int i = 0; i < encargados.length; i++) {
            encargados[i] = new Encargado(juegos);
            encargados[i].start();
        }
        maquina = new Maquinista(locomotor);
        maquina.start();
    }

    public void entrar() throws InterruptedException {
        molinete.acquire();
        System.out.println(Thread.currentThread().getName() + " Entro al parque de atracciones");
    }

    public void salir() {
        System.out.println(Thread.currentThread().getName() + " se fue del parque");
        molinete.release();
    }

    public void subirAlTren() throws InterruptedException {
        boolean bandera = false;
        locomotor.esperarTren();
        bandera = locomotor.abordar();
        locomotor.bajarTren(bandera);
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

    public void reloj() {
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
    }
}
