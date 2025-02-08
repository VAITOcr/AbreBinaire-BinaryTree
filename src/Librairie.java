import java.util.Scanner;

import CSVFileReaderWriter.CSVReader;
import arbres.binaires.ArbreAVL;
import model.Livre;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class Librairie {
    private static ArbreAVL<Livre> arbreAVL;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Load the books from the CSV file
        chargerLivresDepuisCSV("src/file/csv-test.csv");

        while (true) {
            System.out.println("Que souhaitez-vous faire ?");
            System.out.println("1. Rechercher un livre par auteur et titre");
            System.out.println("2. Rechercher un livre par auteur et date de publication");
            System.out.println("3. Rechercher par catégorie, auteur et titre");
            System.out.println("4. Afficher les livres par categorie, auteur et titre");
            System.out.println("5. Afficher les livres en rupture de stock");
            System.out.println("6. Générer un rapport");
            System.out.println("7. Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    rechercherParAuteurEtPrefixe(scanner);
                    break;
                case 2:
                    rechercherParAuteurEtDate(scanner);
                    break;
                case 3:
                    rechercherParCategorieEtPrix(scanner);
                    break;
                case 4:
                    arbreAVL.afficherParCategorieAuteurTitre();
                    break;
                case 5:
                    arbreAVL.afficherRuptureStock();
                    break;
                case 6:
                    arbreAVL.genererRapport();
                    break;
                case 7:
                    System.out.println("Merci d'utiliser la Librairie Mamie !");
                    scanner.close();
                    return;
                default:
                    System.out.println("Option invalide, essayez à nouveau.");
            }
        }
    }

    private static void chargerLivresDepuisCSV(String nomFichier) throws Exception {
        CSVReader<Livre> reader = new CSVReader<Livre>(Livre.class);
        List<Livre> livres = reader.readEntity(nomFichier);

        arbreAVL = new ArbreAVL<>();

        for (Livre livre : livres) {
            arbreAVL.insert(livre);
        }

        System.out.println("Livres chargés depuis le fichier CSV.");
    }

    private static void rechercherParAuteurEtPrefixe(Scanner scanner) {
        System.out.print("Entrez l'auteur : ");
        String auteur = scanner.nextLine().trim();
        System.out.print("Entrez le titre : ");
        String titre = scanner.nextLine().trim();

        arbreAVL.searchByAuthorAndTitlePrefix(auteur, titre);
    }

    private static void rechercherParAuteurEtDate(Scanner scanner) throws ParseException {
        System.out.print("Entrez l'auteur : ");
        String auteur = scanner.nextLine().trim();
        System.out.print("Entrez la date de publication (jj/mm/aaaa) : ");
        String date = scanner.nextLine().trim();
        System.out.print("Entrez la date de fin (jj/mm/aaaa) : ");
        String dateFin = scanner.nextLine().trim();

        Date dateDebut = convertirDate(date);
        Date dateFinDebut = convertirDate(dateFin);

        arbreAVL.searchByAuthorAndDate(auteur, dateDebut, dateFinDebut);
    }

    private static Date convertirDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
        SimpleDateFormat formatterOut = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = formatter.parse(date);
        return Date.valueOf(formatterOut.format(parsed));
    }

    private static void rechercherParCategorieEtPrix(Scanner scanner) {
        System.out.print("Entrez la categorie : ");
        String categorie = scanner.nextLine().trim();
        System.out.print("Entrez le prix minimum : ");
        double prixMin = Double.parseDouble(scanner.next().replace(",", ".")); // Remplace la virgule par un point (ex:
                                                                               // 12,50 -> 12.50, categorie)
        System.out.print("Entrez le prix maximum : ");
        double prixMax = Double.parseDouble(scanner.next().replace(",", "."));
        arbreAVL.searchByCategoryAndPrice(categorie, prixMin, prixMax);
    }
}
