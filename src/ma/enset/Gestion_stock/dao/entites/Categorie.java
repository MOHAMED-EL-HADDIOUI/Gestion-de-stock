package ma.enset.Gestion_stock.dao.entites;

import java.io.Serializable;
import java.util.List;

public class Categorie implements Serializable {
    private int id;
    private String nom;
    private List<Produit>produits;

    public Categorie() {
    }

    public Categorie(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    @Override
    public String toString() {
        return  nom;
    }
}
