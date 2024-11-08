import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class GaraDiCavalli {
    private int lunghezzaGara;
    private List<Cavallo> cavalli = new ArrayList<>();

    public GaraDiCavalli(int lunghezzaGara) {
        this.lunghezzaGara = lunghezzaGara;
    }

    public void aggiungiCavallo(String nome, int velocita) {
        cavalli.add(new Cavallo(nome, velocita, lunghezzaGara));
    }

    public void avviaGara() {
        System.out.println("I cavalli sono pronti! Partenza...");

        for (Cavallo cavallo : cavalli) {
            cavallo.start();
        }

        for (Cavallo cavallo : cavalli) {
            try {
                cavallo.join();
            } catch (InterruptedException e) {
                System.out.println("La gara è stata interrotta.");
            }
        }

        System.out.println("La gara è terminata!");
        calcolaClassifica();
    }

    private void calcolaClassifica() {
        List<Cavallo> classifica = new ArrayList<>(cavalli);
        classifica.sort(Comparator.comparingInt(Cavallo::getMetriPercorsi).reversed());

        System.out.println("Classifica finale:");
        for (int i = 0; i < Math.min(3, classifica.size()); i++) {
            Cavallo cavallo = classifica.get(i);
            System.out.println((i + 1) + ". " + cavallo.getNome() + " - " + cavallo.getMetriPercorsi() + " metri");
        }

        // Chiedi nome file per salvare la classifica
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci il nome del file per salvare i risultati: ");
        String nomeFile = scanner.nextLine();
        salvaClassifica(nomeFile, classifica);
        scanner.close();
    }

    private void salvaClassifica(String nomeFile, List<Cavallo> classifica) {
        try (FileWriter writer = new FileWriter(nomeFile, true)) { // 'true' per modalità append
            writer.write("Classifica della gara:\n");
            for (int i = 0; i < Math.min(3, classifica.size()); i++) {
                Cavallo cavallo = classifica.get(i);
                writer.write((i + 1) + ". " + cavallo.getNome() + " - " + cavallo.getMetriPercorsi() + " metri\n");
            }
            writer.write("\n");
            System.out.println("Classifica salvata nel file " + nomeFile);
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio della classifica.");
        }
    }
}
