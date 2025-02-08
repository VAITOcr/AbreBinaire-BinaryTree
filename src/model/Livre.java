package model;

import CSVFileReaderWriter.CSVSerializable;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Livre implements CSVSerializable<Livre>, Comparable<Livre> {

    private String titre;
    private String auteur;
    private String categorie;
    private double prix;
    private Date datePublication;
    private int quantiteEnStock;
    private String ISBN;

    public Livre(String titre, String auteur, String categorie, double prix, Date datePublication, int quantiteEnStock,
            String ISBN) {
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
        this.prix = prix;
        this.datePublication = datePublication;
        this.quantiteEnStock = quantiteEnStock;
        this.ISBN = ISBN;
    }

    public Livre(String[] data) {
        try {
            this.titre = data[0];
            this.auteur = data[1];
            this.categorie = data[2];
            this.prix = Double.parseDouble(data[3]);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            this.datePublication = formatter.parse(data[4]);
            this.quantiteEnStock = Integer.parseInt(data[5]);
            this.ISBN = data[6];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // getters and setters

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return this.auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getCategorie() {
        return this.categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public double getPrix() {
        return this.prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDatePublication() {
        return this.datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public int getQuantiteEnStock() {
        return this.quantiteEnStock;
    }

    public void setQuantiteEnStock(int quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toCSVString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return this.titre + "," + this.auteur + "," + this.categorie + "," + this.prix + ","
                + formatter.format(this.datePublication) + "," + this.quantiteEnStock + "," + this.ISBN;
    }

    @Override
    public Livre fromCSVString(String line) {
        return new Livre(line.split(","));
    }

    @Override
    public int compareTo(Livre other) {
        return this.ISBN.compareTo(other.getISBN());
    }

    @Override
    public String toString() {
        return "Livre: " + this.titre + ", " + this.auteur + ", " + this.categorie + ", " + this.prix + ", "
                + this.datePublication + ", " + this.quantiteEnStock + ", " + this.ISBN;
    }
}
