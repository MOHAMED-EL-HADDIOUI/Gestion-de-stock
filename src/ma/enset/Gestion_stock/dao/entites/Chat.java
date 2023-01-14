package ma.enset.Gestion_stock.dao.entites;

import java.io.Serializable;
import java.util.Date;

public class Chat implements Serializable {
    private int id;
    private String message;
    private String user;
    private Date date;
    private Client client;
    private Fournisseur fournisseur;

    public Chat() {
    }

    public Chat(int id, String message, String user, Date date, Client client, Fournisseur fournisseur) {
        this.id = id;
        this.message = message;
        this.user = user;
        this.date = date;
        this.client = client;
        this.fournisseur = fournisseur;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public Client getClient() {
        return client;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", user='" + user + '\'' +
                ", date=" + date +
                ", client=" + client +
                ", fournisseur=" + fournisseur +
                '}';
    }
}
