public class ConsumidorVotos implements Runnable {
    private final TopicVotos votos;

    public ConsumidorVotos(TopicVotos votos) {
        this.votos = votos;
    }

    @Override
    public void run() {
        while (true) {
            String voto = votos.consumir();
            if (voto != null) {
                System.out.println("✅ Voto procesado: " + voto);
            }
        }
    }
}