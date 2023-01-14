package ma.enset.Gestion_stock.dao;

import ma.enset.Gestion_stock.dao.entites.Categorie;
import ma.enset.Gestion_stock.dao.entites.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieDoaImpl implements CategorieDoa{

    @Override
    public List<Categorie> findAll() throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("select * from categories");
        ResultSet rs = stm.executeQuery();
        List<Categorie> categories = new ArrayList<>();
        while(rs.next())
        {
            Categorie p =new Categorie();
            p.setId(rs.getInt("ID_CATEGORIE"));
            p.setNom(rs.getString("NOM_CATEGORIE"));
            categories.add(p);
        }
        return categories;
    }

    @Override
    public Categorie findOne(int id) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("select * from categories where ID_CATEGORIE=?");
        stm.setInt(1,id);
        Categorie p=null;
        ResultSet rs = stm.executeQuery();
        if (rs.next())
        {
            p=new Categorie();
            p.setId(rs.getInt("ID_CATEGORIE"));
            p.setNom(rs.getString("NOM_CATEGORIE"));
        }
        return p;
    }

    @Override
    public Categorie save(Categorie o) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("insert into categories(NOM_CATEGORIE) values (?)");
        stm.setString(1,o.getNom());
        stm.executeUpdate();
        return o;
    }

    @Override
    public boolean delete(Categorie o) {

        try {
            Connection connection=SingletonConnexionBD.getConnection();
            PreparedStatement pstm = pstm = connection.prepareStatement("delete from categories where ID_CATEGORIE=?");
            pstm.setInt(1,o.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    @Override
    public Categorie update(Categorie o) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement pstm = connection.prepareStatement("update  categories set NOM_CATEGORIE=? where ID_CATEGORIE =?");
        pstm.setString(1,o.getNom());
        pstm.setInt(2,o.getId());
        pstm.executeUpdate();
        return o;
    }

    @Override
    public void deleteAllCategories() throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement pstm  = connection.prepareStatement("delete from categories");
        pstm.executeUpdate();
    }
}
