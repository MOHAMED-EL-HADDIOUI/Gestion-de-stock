package ma.enset.Gestion_stock.dao;

import ma.enset.Gestion_stock.dao.entites.Categorie;
import ma.enset.Gestion_stock.dao.entites.Fournisseur;
import ma.enset.Gestion_stock.dao.entites.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDaoImpl implements ProduitDoa{

    @Override
    public List<Produit> findAll() throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT F.NOM_FOURNISSEUR,F.TEL_FOURNISSEUR,F.GMAIL_FOURNISSEUR,F.ADRESSE_FOURNISSEUR,F.ID_FOURNISSEUR,P.ID_PRODUIT,P.NOM_PRPDUIT,P.DESCRIPTION_PRODUIT,P.PRIX_PRODUIT,P.QUANTITE_STOCK,P.ID_CATEGORIE,C.NOM_CATEGORIE from produits P,categories C,fournisseurs F where P.ID_CATEGORIE=C.ID_CATEGORIE and P.ID_FOURNISSEUR=F.ID_FOURNISSEUR;");
        ResultSet rs = stm.executeQuery();
        List<Produit> produits = new ArrayList<>();
        while(rs.next())
        {
            Produit p =new Produit();
            p.setId(rs.getInt("ID_PRODUIT"));
            p.setNom(rs.getString("NOM_PRPDUIT"));
            p.setDescription(rs.getString("DESCRIPTION_PRODUIT"));
            p.setPrix(rs.getFloat("PRIX_PRODUIT"));
            p.setQuantite(rs.getInt("QUANTITE_STOCK"));

            Categorie categorie =new Categorie();
            categorie.setId(rs.getInt("ID_CATEGORIE"));
            categorie.setNom(rs.getString("NOM_CATEGORIE"));
            p.setCategorie(categorie);

            Fournisseur fournisseur =new Fournisseur();
            fournisseur.setId(rs.getInt("ID_CATEGORIE"));
            fournisseur.setTel(rs.getString("TEL_FOURNISSEUR"));
            fournisseur.setGmail(rs.getString("GMAIL_FOURNISSEUR"));
            fournisseur.setAdresse(rs.getString("ADRESSE_FOURNISSEUR"));
            fournisseur.setNom(rs.getString("NOM_FOURNISSEUR"));
            p.setFournisseur(fournisseur);

            produits.add(p);
        }
        return produits;
    }

    @Override
    public Produit findOne(int id) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("select F.NOM_FOURNISSEUR,F.TEL_FOURNISSEUR,F.GMAIL_FOURNISSEUR,F.ADRESSE_FOURNISSEUR,P.ID_FOURNISSEUR,P.ID_PRODUIT,P.NOM_PRPDUIT,P.DESCRIPTION_PRODUIT,P.PRIX_PRODUIT,P.QUANTITE_STOCK,P.ID_CATEGORIE,C.NOM_CATEGORIE from produits P,categories C,fournisseurs F where P.ID_CATEGORIE=C.ID_CATEGORIE and P.ID_FOURNISSEUR=F.ID_FOURNISSEUR and P.ID_PRODUIT=?");
        stm.setInt(1,id);
        Produit produit=null;
        ResultSet rs = stm.executeQuery();
        if (rs.next())
        {
            produit = new Produit();
            produit.setId(rs.getInt("ID_PRODUIT"));
            produit.setNom(rs.getString("NOM_PRPDUIT"));
            produit.setDescription(rs.getString("DESCRIPTION_PRODUIT"));
            produit.setPrix(rs.getFloat("PRIX_PRODUIT"));
            produit.setQuantite(rs.getInt("QUANTITE_STOCK"));

            Categorie categorie =new Categorie();
            categorie.setId(rs.getInt("ID_CATEGORIE"));
            categorie.setNom(rs.getString("NOM_CATEGORIE"));

            produit.setCategorie(categorie);

            Fournisseur fournisseur =new Fournisseur();
            fournisseur.setId(rs.getInt("ID_CATEGORIE"));
            fournisseur.setTel(rs.getString("TEL_FOURNISSEUR"));
            fournisseur.setGmail(rs.getString("GMAIL_FOURNISSEUR"));
            fournisseur.setAdresse(rs.getString("ADRESSE_FOURNISSEUR"));
            fournisseur.setNom(rs.getString("NOM_FOURNISSEUR"));

            produit.setFournisseur(fournisseur);
        }
        return produit;
    }

    @Override
    public Produit save(Produit o) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("insert into produits(NOM_PRPDUIT,DESCRIPTION_PRODUIT,PRIX_PRODUIT,QUANTITE_STOCK,ID_CATEGORIE,ID_FOURNISSEUR) values (?,?,?,?,?,?)");
        stm.setString(1,o.getNom());
        stm.setString(2,o.getDescription());
        stm.setFloat(3,o.getPrix());
        stm.setInt(4,o.getQuantite());
        stm.setInt(5,o.getCategorie().getId());
        stm.setInt(6,o.getFournisseur().getId());
        stm.executeUpdate();
        return o;
    }

    @Override
    public boolean delete(Produit o) {

        try {
            Connection connection=SingletonConnexionBD.getConnection();
            PreparedStatement pstm  = connection.prepareStatement("delete from produits where ID_PRODUIT=?");
            pstm.setInt(1,o.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            return false;
        }

        return true;
    }
    @Override
    public void deleteAllProduits() throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement pstm  = connection.prepareStatement("delete from produits");
        pstm.executeUpdate();
    }

    @Override
    public void DiminuerQuantite(int id, int quantite) throws SQLException {
        Produit p =findOne(id);
        int quantite0 = p.getQuantite();
        p.setQuantite(quantite0-quantite);
        update(p);
    }

    @Override
    public Produit update(Produit o) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement pstm = connection.prepareStatement("update  produits set NOM_PRPDUIT=?,DESCRIPTION_PRODUIT=?,PRIX_PRODUIT=?,QUANTITE_STOCK=? where ID_PRODUIT =?");
        pstm.setString(1,o.getNom());
        pstm.setString(2,o.getDescription());
        pstm.setFloat(3,o.getPrix());
        pstm.setInt(4,o.getQuantite());
        pstm.setInt(5,o.getId());
        pstm.executeUpdate();
        return o;
    }

    @Override
    public List<Produit> findProduitByMc(String Mc) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement pstm = connection.prepareStatement("select F.NOM_FOURNISSEUR,F.TEL_FOURNISSEUR,F.GMAIL_FOURNISSEUR,F.ADRESSE_FOURNISSEUR,P.ID_FOURNISSEUR,P.ID_PRODUIT,P.NOM_PRPDUIT,P.DESCRIPTION_PRODUIT,P.PRIX_PRODUIT,P.QUANTITE_STOCK,P.ID_CATEGORIE,C.NOM_CATEGORIE from produits P,categories C,fournisseurs F where P.ID_CATEGORIE=C.ID_CATEGORIE and P.ID_FOURNISSEUR=F.ID_FOURNISSEUR and P.NOM_PRPDUIT like ?");
        pstm.setString(1,"%"+Mc+"%");
        ResultSet rs = pstm.executeQuery();
        List<Produit> produits = new ArrayList<>();
        while(rs.next())
        {
            Produit p =new Produit();
            p.setId(rs.getInt("ID_PRODUIT"));
            p.setNom(rs.getString("NOM_PRPDUIT"));
            p.setDescription(rs.getString("DESCRIPTION_PRODUIT"));
            p.setPrix(rs.getFloat("PRIX_PRODUIT"));
            p.setQuantite(rs.getInt("QUANTITE_STOCK"));

            Categorie categorie =new Categorie();
            categorie.setId(rs.getInt("ID_CATEGORIE"));
            categorie.setNom(rs.getString("NOM_CATEGORIE"));
            p.setCategorie(categorie);

            Fournisseur fournisseur =new Fournisseur();
            fournisseur.setId(rs.getInt("ID_CATEGORIE"));
            fournisseur.setTel(rs.getString("TEL_FOURNISSEUR"));
            fournisseur.setGmail(rs.getString("GMAIL_FOURNISSEUR"));
            fournisseur.setAdresse(rs.getString("ADRESSE_FOURNISSEUR"));
            fournisseur.setNom(rs.getString("NOM_FOURNISSEUR"));
            p.setFournisseur(fournisseur);

            produits.add(p);
        }
        return produits;

    }

    @Override
    public List<Produit> findAllFournisseur(int id) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT F.NOM_FOURNISSEUR,F.TEL_FOURNISSEUR,F.GMAIL_FOURNISSEUR,F.ADRESSE_FOURNISSEUR,F.ID_FOURNISSEUR,P.ID_PRODUIT,P.NOM_PRPDUIT,P.DESCRIPTION_PRODUIT,P.PRIX_PRODUIT,P.QUANTITE_STOCK,P.ID_CATEGORIE,C.NOM_CATEGORIE from produits P,categories C,fournisseurs F where P.ID_CATEGORIE=C.ID_CATEGORIE and P.ID_FOURNISSEUR=F.ID_FOURNISSEUR and P.ID_FOURNISSEUR=?;");
        stm.setInt(1,id);
        ResultSet rs = stm.executeQuery();
        List<Produit> produits = new ArrayList<>();
        while(rs.next())
        {
            Produit p =new Produit();
            p.setId(rs.getInt("ID_PRODUIT"));
            p.setNom(rs.getString("NOM_PRPDUIT"));
            p.setDescription(rs.getString("DESCRIPTION_PRODUIT"));
            p.setPrix(rs.getFloat("PRIX_PRODUIT"));
            p.setQuantite(rs.getInt("QUANTITE_STOCK"));

            Categorie categorie =new Categorie();
            categorie.setId(rs.getInt("ID_CATEGORIE"));
            categorie.setNom(rs.getString("NOM_CATEGORIE"));
            p.setCategorie(categorie);

            Fournisseur fournisseur =new Fournisseur();
            fournisseur.setId(rs.getInt("ID_CATEGORIE"));
            fournisseur.setTel(rs.getString("TEL_FOURNISSEUR"));
            fournisseur.setGmail(rs.getString("GMAIL_FOURNISSEUR"));
            fournisseur.setAdresse(rs.getString("ADRESSE_FOURNISSEUR"));
            fournisseur.setNom(rs.getString("NOM_FOURNISSEUR"));
            p.setFournisseur(fournisseur);

            produits.add(p);
        }
        return produits;
    }

    @Override
    public List<Produit> findProduitFournisseurByMc(int id, String Mc) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement pstm = connection.prepareStatement("select F.NOM_FOURNISSEUR,F.TEL_FOURNISSEUR,F.GMAIL_FOURNISSEUR,F.ADRESSE_FOURNISSEUR,P.ID_FOURNISSEUR,P.ID_PRODUIT,P.NOM_PRPDUIT,P.DESCRIPTION_PRODUIT,P.PRIX_PRODUIT,P.QUANTITE_STOCK,P.ID_CATEGORIE,C.NOM_CATEGORIE from produits P,categories C,fournisseurs F where P.ID_CATEGORIE=C.ID_CATEGORIE and P.ID_FOURNISSEUR=F.ID_FOURNISSEUR and P.NOM_PRPDUIT like ? and P.ID_FOURNISSEUR=?");
        pstm.setString(1,"%"+Mc+"%");
        pstm.setString(2, String.valueOf(id));
        ResultSet rs = pstm.executeQuery();
        List<Produit> produits = new ArrayList<>();
        while(rs.next())
        {
            Produit p =new Produit();
            p.setId(rs.getInt("ID_PRODUIT"));
            p.setNom(rs.getString("NOM_PRPDUIT"));
            p.setDescription(rs.getString("DESCRIPTION_PRODUIT"));
            p.setPrix(rs.getFloat("PRIX_PRODUIT"));
            p.setQuantite(rs.getInt("QUANTITE_STOCK"));

            Categorie categorie =new Categorie();
            categorie.setId(rs.getInt("ID_CATEGORIE"));
            categorie.setNom(rs.getString("NOM_CATEGORIE"));
            p.setCategorie(categorie);

            Fournisseur fournisseur =new Fournisseur();
            fournisseur.setId(rs.getInt("ID_CATEGORIE"));
            fournisseur.setTel(rs.getString("TEL_FOURNISSEUR"));
            fournisseur.setGmail(rs.getString("GMAIL_FOURNISSEUR"));
            fournisseur.setAdresse(rs.getString("ADRESSE_FOURNISSEUR"));
            fournisseur.setNom(rs.getString("NOM_FOURNISSEUR"));
            p.setFournisseur(fournisseur);

            produits.add(p);
        }
        return produits;
    }

}
