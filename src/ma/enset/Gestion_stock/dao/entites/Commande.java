package ma.enset.Gestion_stock.dao.entites;

import java.io.Serializable;
import java.util.Date;

public class Commande implements Serializable  {
    private int id;
    private Client client;
    private Produit produit;
    private int quantite;
    private Date date;

    public Commande() {
    }

    public Commande(int id, Client client, Produit produit, int quantite, Date date) {
        this.id = id;
        this.client = client;
        this.produit = produit;
        this.quantite = quantite;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public Produit getProduit() {
        return produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
