import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserisci la lunghezza del percorso della gara in metri: ");
        int lunghezzaPercorso = scanner.nextInt();
        scanner.nextLine();

        GaraDiCavalli gara = new GaraDiCavalli(lunghezzaPercorso);

        System.out.print("Inserisci il numero di cavalli: ");
        int numeroCavalli = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numeroCavalli; i++) {
            System.out.print("Inserisci il nome del cavallo " + (i + 1) + ": ");
            String nomeCavallo = scanner.nextLine();
            System.out.print("Inserisci la velocità di " + nomeCavallo + " (metri per secondo): ");
            int velocitaCavallo = scanner.nextInt();
            scanner.nextLine();
            gara.aggiungiCavallo(nomeCavallo, velocitaCavallo);
        }

        gara.avviaGara();
        scanner.close();
    }
}
