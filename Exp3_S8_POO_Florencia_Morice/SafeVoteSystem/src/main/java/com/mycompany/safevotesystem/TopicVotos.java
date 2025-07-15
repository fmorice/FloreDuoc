import java.util.LinkedList;
import java.util.Queue;

public class TopicVotos {
    private final Queue<String> votos = new LinkedList<>();
    private final int capacidadMaxima;

    public TopicVotos(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    // Método para que los productores publiquen un voto
    public synchronized void publicar(String voto) {
        while (votos.size() >= capacidadMaxima) {
            try {
                wait(); // Espera si la cola está llena
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        votos.add(voto);
        System.out.println("🗳️ Voto publicado: " + voto);
        notifyAll(); // Notifica a consumidores que hay datos
    }

    // Método para que los consumidores tomen un voto
    public synchronized String consumir() {
        while (votos.isEmpty()) {
            try {
                wait(); // Espera si la cola está vacía
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        String voto = votos.poll();
        notifyAll(); // Notifica a productores que hay espacio
        return voto;
    }

    // Consulta si la cola está vacía (con sincronización)
    public synchronized boolean estaVacia() {
        return votos.isEmpty();
    }
}
