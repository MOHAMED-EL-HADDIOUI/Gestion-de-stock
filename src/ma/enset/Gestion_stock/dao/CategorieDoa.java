package ma.enset.Gestion_stock.dao;

import ma.enset.Gestion_stock.dao.entites.Categorie;

import java.sql.SQLException;

public interface CategorieDoa extends Dao<Categorie>{
    void deleteAllCategories() throws SQLException;

}
