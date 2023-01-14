package ma.enset.Gestion_stock.dao;

import ma.enset.Gestion_stock.dao.entites.Categorie;
import ma.enset.Gestion_stock.dao.entites.Client;
import ma.enset.Gestion_stock.dao.entites.Fournisseur;

import java.sql.SQLException;

public interface ClientDoa extends Dao<Client>{
    void deleteAllClients() throws SQLException;
    public Client findUsernamePasswordClient(String username, String password) throws SQLException;
}
