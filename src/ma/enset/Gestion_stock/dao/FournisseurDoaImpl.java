package ma.enset.Gestion_stock.dao;

import ma.enset.Gestion_stock.dao.entites.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDoaImpl implements FournisseurDoa{
    @Override
    public List<Fournisseur> findAll() throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("select * from fournisseurs");
        ResultSet rs = stm.executeQuery();
        List<Fournisseur> fournisseurs = new ArrayList<>();
        while(rs.next())
        {
            Fournisseur p =new Fournisseur();
            p.setId(rs.getInt("ID_FOURNISSEUR"));
            p.setNom(rs.getString("NOM_FOURNISSEUR"));
            p.setTel(rs.getString("TEL_FOURNISSEUR"));
            p.setAdresse(rs.getString("ADRESSE_FOURNISSEUR"));
            p.setGmail(rs.getString("GMAIL_FOURNISSEUR"));
            fournisseurs.add(p);
        }
        return fournisseurs;
    }

    @Override
    public Fournisseur findOne(int id) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("select * from fournisseurs where ID_FOURNISSEUR=?");
        stm.setInt(1,id);
        Fournisseur fournisseur=null;
        ResultSet rs = stm.executeQuery();
        if (rs.next())
        {
            fournisseur = new Fournisseur();

            fournisseur.setGmail(rs.getString("GMAIL_FOURNISSEUR"));
            fournisseur.setTel(rs.getString("TEL_FOURNISSEUR"));
            fournisseur.setNom(rs.getString("NOM_FOURNISSEUR"));
            fournisseur.setPassword(rs.getString("PASSWORD_FOURNISSEUR"));
            fournisseur.setAdresse(rs.getString("ADRESSE_FOURNISSEUR"));
            fournisseur.setId(rs.getInt("ID_FOURNISSEUR"));
        }
        return fournisseur;
    }
    @Override
    public Fournisseur findUsernamePassword(String username,String password) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("select * from fournisseurs where GMAIL_FOURNISSEUR=? and PASSWORD_FOURNISSEUR=? ");
        stm.setString(1,username);
        stm.setString(2,password);
        Fournisseur fournisseur=null;
        ResultSet rs = stm.executeQuery();
        if (rs.next())
        {
            fournisseur = new Fournisseur();

            fournisseur.setGmail(rs.getString("GMAIL_FOURNISSEUR"));
            fournisseur.setTel(rs.getString("TEL_FOURNISSEUR"));
            fournisseur.setNom(rs.getString("NOM_FOURNISSEUR"));
            fournisseur.setPassword(rs.getString("PASSWORD_FOURNISSEUR"));
            fournisseur.setAdresse(rs.getString("ADRESSE_FOURNISSEUR"));
            fournisseur.setId(rs.getInt("ID_FOURNISSEUR"));
        }
        return fournisseur;
    }

    @Override
    public Fournisseur save(Fournisseur o) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Fournisseur o) throws SQLException {
        return false;
    }

    @Override
    public Fournisseur update(Fournisseur o) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement pstm = connection.prepareStatement("update  fournisseurs set NOM_FOURNISSEUR=?,TEL_FOURNISSEUR=?,ADRESSE_FOURNISSEUR=?,PASSWORD_FOURNISSEUR=?,GMAIL_FOURNISSEUR=? where ID_FOURNISSEUR=?");
        pstm.setString(1,o.getNom());
        pstm.setString(2,o.getTel());
        pstm.setString(3,o.getAdresse());
        pstm.setString(4,o.getPassword());
        pstm.setString(5,o.getGmail());
        pstm.setInt(6,o.getId());
        pstm.executeUpdate();
        return o;
    }
}
