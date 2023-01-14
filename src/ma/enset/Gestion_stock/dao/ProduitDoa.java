package ma.enset.Gestion_stock.dao;

import ma.enset.Gestion_stock.dao.entites.Produit;

import java.sql.SQLException;
import java.util.List;

public interface ProduitDoa extends Dao<Produit>{
    void deleteAllProduits() throws SQLException;
    void DiminuerQuantite(int id,int quantite) throws SQLException;
    List<Produit> findProduitByMc(String MC) throws SQLException;
    List<Produit> findAllFournisseur(int id) throws SQLException;
    List<Produit> findProduitFournisseurByMc(int id,String MC) throws SQLException;
}
