import java.util.Random;

public class Cavallo extends Thread {
    private String nome;
    private int velocita; // Velocità in metri per secondo
    private int lunghezzaPercorso;
    private int metriPercorsi = 0;
    private boolean infortunato = false; // Stato di infortunio
    private Random random = new Random();

    public Cavallo(String nome, int velocita, int lunghezzaPercorso) {
        this.nome = nome;
        this.velocita = velocita;
        this.lunghezzaPercorso = lunghezzaPercorso;
    }

    public String getNome() {
        return nome;
    }

    public int getMetriPercorsi() {
        return metriPercorsi;
    }

    @Override
    public void run() {
        while (metriPercorsi < lunghezzaPercorso && !infortunato) {
            // Simula infortunio con una probabilità del 5%
            if (random.nextInt(100) < 5) {
                infortunato = true;
                System.out.println(nome + " si è infortunato e abbandona la gara!");
                break;
            }

            // Avanza in base alla velocità
            metriPercorsi += velocita;
            if (metriPercorsi > lunghezzaPercorso) {
                metriPercorsi = lunghezzaPercorso;
            }

            System.out.println(nome + " ha percorso " + metriPercorsi + " metri.");

            try {
                Thread.sleep(1000); // Pausa di 1 secondo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!infortunato) {
            System.out.println(nome + " ha completato la gara!");
        }
    }
}

