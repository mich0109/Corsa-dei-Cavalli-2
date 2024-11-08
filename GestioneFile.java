import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GestioneFile {
    public static void salvaClassifica(String nomeFile, List<Cavallo> classifica) {
        try (FileWriter writer = new FileWriter(nomeFile, true)) { // Modalità append
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
