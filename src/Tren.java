import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Tren {
    private BlockingQueue<String> colaTren;

    public Tren(int capacidad) {
        colaTren = new ArrayBlockingQueue<>(capacidad);
    }
}
