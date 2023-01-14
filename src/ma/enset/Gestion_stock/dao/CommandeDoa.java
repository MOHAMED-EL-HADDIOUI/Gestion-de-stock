package ma.enset.Gestion_stock.dao;

import ma.enset.Gestion_stock.dao.entites.Commande;
import ma.enset.Gestion_stock.dao.entites.Client;
import ma.enset.Gestion_stock.dao.entites.Commande;

import java.sql.SQLException;
import java.util.List;

public interface CommandeDoa extends Dao<Commande> {
    public List<Commande>  findCommandeClient(int id) throws SQLException;
    public List<Commande> findCommandeFournisseur(int id) throws SQLException;
}
