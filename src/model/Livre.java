package model;

import CSVFileReaderWriter.CSVSerializable;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Livre implements CSVSerializable<Livre> {

    private String titre;
    private String auteur;
    private String categorie;
    private double prix;
    private Date datePublication;
    private int quantiteEnStock;
    private String ISBN;

    public Livre(String titre, String auteur, String categorie, double prix, Date datePublication, int quantiteEnStock, String ISBN) {
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
        this.prix = prix;
        this.datePublication = datePublication;
        this.quantiteEnStock = quantiteEnStock;
        this.ISBN = ISBN;
    }

    @Override
    public String toCSVString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return this.titre + "," + this.auteur + "," + this.categorie + "," + this.prix + "," + formatter.format(this.datePublication) + "," + this.quantiteEnStock + "," + this.ISBN;
    }

    @Override
    public Livre fromCSVString(String line) {
        String[] values = line.split(",");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        String titre = values[0];
        String auteur = values[1];
        String categorie = values[2];
        double prix = Double.parseDouble(values[3]);
        Date datePublication = null;
        try {
            datePublication = formatter.parse(values[4]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int quantiteEnStock = Integer.parseInt(values[5]);
        String ISBN = values[6];
        return new Livre(titre, auteur, categorie, prix, datePublication, quantiteEnStock, ISBN);
    }
}
