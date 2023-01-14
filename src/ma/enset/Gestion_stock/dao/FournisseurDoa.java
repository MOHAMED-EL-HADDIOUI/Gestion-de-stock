package ma.enset.Gestion_stock.dao;

import ma.enset.Gestion_stock.dao.entites.Fournisseur;

import java.sql.SQLException;

public interface FournisseurDoa extends Dao<Fournisseur>{
    public Fournisseur findUsernamePassword(String username,String password) throws SQLException;
}
