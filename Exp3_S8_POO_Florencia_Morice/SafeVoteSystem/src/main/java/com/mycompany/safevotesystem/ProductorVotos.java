public class ProductorVotos implements Runnable {
    private final TopicVotos votos;
    private final String[] votantes;

    public ProductorVotos(TopicVotos votos, String[] votantes) {
        this.votos = votos;
        this.votantes = votantes;
    }

    @Override
    public void run() {
        for (String voto : votantes) {
            votos.publicar(voto);
            try {
                Thread.sleep(1000); // Simula retraso
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
