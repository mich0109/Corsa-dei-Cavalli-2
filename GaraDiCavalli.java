import java.util.ArrayList;
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

        // Avvio di ogni cavallo
        for (Cavallo cavallo : cavalli) {
            cavallo.start();
        }

        // Attende la fine di tutti i cavalli
        for (Cavallo cavallo : cavalli) {
            try {
                cavallo.join();
            } catch (InterruptedException e) {
                System.out.println("La gara è stata interrotta.");
            }
        }

        System.out.println("La gara è terminata!");
        stampaClassifica();
    }

    private void stampaClassifica() {
        cavalli.sort((c1, c2) -> Integer.compare(c2.getMetriPercorsi(), c1.getMetriPercorsi()));

        System.out.println("Classifica finale:");
        for (int i = 0; i < Math.min(3, cavalli.size()); i++) {
            Cavallo cavallo = cavalli.get(i);
            System.out.println((i + 1) + ". " + cavallo.getNome() + " - " + cavallo.getMetriPercorsi() + " metri");
        }

        // Salva la classifica nel file
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci il nome del file per salvare i risultati: ");
        String nomeFile = scanner.nextLine();
        GestioneFile.salvaClassifica(nomeFile, cavalli);
        scanner.close();
    }
}
