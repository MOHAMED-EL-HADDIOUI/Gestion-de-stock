package ma.enset.Gestion_stock.dao.entites;

import java.io.Serializable;

public class Fournisseur implements Serializable {
    private int id;
    private String nom;
    private String tel;
    private String adresse;
    private String gmail;
    private String password;

    public Fournisseur() {
    }

    public Fournisseur(int id, String nom, String tel, String adresse, String gmail, String password) {
        this.id = id;
        this.nom = nom;
        this.tel = tel;
        this.adresse = adresse;
        this.gmail = gmail;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getTel() {
        return tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getGmail() {
        return gmail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    @Override
    public String toString() {
        return nom;
    }
}
