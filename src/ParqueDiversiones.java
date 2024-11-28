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
    Reloj relojControl;
    int capacidad, visitantes;
    SalaRealidadVirtual mundoRV;

    public ParqueDiversiones(int capacidad) {
        restaurant = new Comedor();
        juegos = new JuegosDePremios();
        molinete = new Semaphore(capacidad);
        locomotor = new Tren(10);
        this.capacidad = capacidad;
        visitantes = 0;
        for (int i = 0; i < encargados.length; i++) {
            encargados[i] = new Encargado(juegos);
            encargados[i].start();
        }
        maquina = new Maquinista(locomotor);
        maquina.start();
        mundoRV = new SalaRealidadVirtual(10);
    }

    public synchronized void entrar() throws InterruptedException {
        if (relojControl.getHora() <= 9) {
            while ((capacidad == visitantes)) {
                this.wait();
            }
            visitantes++;
            System.out.println(Thread.currentThread().getName() + " entro al parque");
        } else {
            System.out.println("El parque ya cerro vuelva mañana");
        }
    }

    public synchronized void salir() {
        System.out.println(Thread.currentThread().getName() + " se fue del parque");
        visitantes--;
        notify();
    }

    public void subirAlTren() throws InterruptedException {
        if (relojControl.getHora() <= 19) {
            locomotor.esperarTren();
            locomotor.bajarTren();
        } else {
            System.out.println("El tren ya cerró por hoy. Vuelva mañana.");
        }
    }

    public void usarComedor() throws InterruptedException {
        if (relojControl.getHora() <= 19) {
            boolean exito = restaurant.entrarComedor();
            if (exito) {
                restaurant.sentarse();
                // comen
                restaurant.salir();
            } else {
                System.out.println("El comedor está lleno. Intente más tarde.");
            }
        } else {
            System.out.println("El comedor ya cerró por hoy. Vuelva mañana.");
        }
    }

    public int jugarPorPremios() {
        int puntos = 0;
        if (relojControl.getHora() <= 19) {
            try {
                puntos = juegos.jugar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " obtuvo: " + puntos + " puntos.");
        } else {
            System.out.println("Los juegos ya cerraron por hoy. Vuelva mañana.");
        }
        return puntos;
    }

    public void usarSalaRV() {
        try {
            mundoRV.entrarASalaRV();
            Thread.sleep(5500);
            mundoRV.salirSalaRV();
        } catch (Exception e) {
            // TODO: handle exception
        }
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
        relojControl = reloj;
        reloj.start();
    }
}
