package ma.enset.Gestion_stock.dao;

import ma.enset.Gestion_stock.dao.entites.Categorie;
import ma.enset.Gestion_stock.dao.entites.Client;
import ma.enset.Gestion_stock.dao.entites.Commande;
import ma.enset.Gestion_stock.dao.entites.Produit;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommandeDoaImpl implements CommandeDoa {
    @Override
    public List<Commande> findAll() throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("select D.DATE_COMMANDE,D.ID_COMMANDE,P.NOM_PRODUIT,P.DESCRIPTION_PRODUIT,P.PRIX_PRODUIT,P.QUANTITE_STOCK,D.ID_CLIENT,D.ID_PRODUIT,D.QUANTITE ,C.NOM_CLIENT,C.GMAIL_CLIENT,C.TEL_CLIENT,C.ADRESSE_CLIENT from produits P,clients C,commandes D where D.ID_CLIENT=C.ID_CLIENT and D.ID_PRODUIT=P.ID_PRODUIT;");
        ResultSet rs = stm.executeQuery();
        List<Commande> commandes = new ArrayList<>();
        while(rs.next())
        {
            Produit p =new Produit();
            p.setId(rs.getInt("ID_PRODUIT"));
            p.setNom(rs.getString("NOM_PRODUIT"));
            p.setDescription(rs.getString("DESCRIPTION_PRODUIT"));
            p.setPrix(rs.getFloat("PRIX_PRODUIT"));
            p.setQuantite(rs.getInt("QUANTITE_STOCK"));

            Client client =new Client();
            client.setId(rs.getInt("ID_CLIENT"));
            client.setNom(rs.getString("NOM_CLIENT"));
            client.setTel(rs.getString("TEL_CLIENT"));
            client.setTel(rs.getString("ADRESSE_CLIENT"));
            client.setGmail(rs.getString("GMAIL_CLIENT"));

            Commande commande = new Commande();
            commande.setId(rs.getInt("ID_COMMANDE"));
            commande.setQuantite(rs.getInt("QUANTITE"));
            commande.setDate(rs.getDate("DATE_COMMANDE"));

            commande.setClient(client);

            commande.setProduit(p);

            commandes.add(commande);
        }
        return commandes;
    }

    @Override
    public Commande findOne(int id) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("select D.DATE_COMMANDE,D.ID_COMMANDE,P.NOM_PRODUIT,P.DESCRIPTION_PRODUIT,P.PRIX_PRODUIT,P.QUANTITE_STOCK,D.ID_CLIENT,D.ID_PRODUIT,D.QUANTITE ,C.NOM_CLIENT,C.GMAIL_CLIENT,C.TEL_CLIENT,C.ADRESSE_CLIENT from produits P,clients C,commandes D where D.ID_CLIENT=C.ID_CLIENT and D.ID_PRODUIT=P.ID_PRODUIT and and D.ID_COMMANDE=?;");
        stm.setInt(1,id);
        Commande commande=null;
        ResultSet rs = stm.executeQuery();
        if (rs.next())
        {
            Produit p = new Produit();
            p.setId(rs.getInt("ID_PRODUIT"));
            p.setNom(rs.getString("NOM_PRODUIT"));
            p.setDescription(rs.getString("DESCRIPTION_PRODUIT"));
            p.setPrix(rs.getFloat("PRIX_PRODUIT"));
            p.setQuantite(rs.getInt("QUANTITE_STOCK"));

            Client client =new Client();
            client.setId(rs.getInt("ID_CLIENT"));
            client.setNom(rs.getString("NOM_CLIENT"));
            client.setTel(rs.getString("TEL_CLIENT"));
            client.setTel(rs.getString("ADRESSE_CLIENT"));
            client.setGmail(rs.getString("GMAIL_CLIENT"));

            commande = new Commande();
            commande.setId(rs.getInt("ID_COMMANDE"));
            commande.setQuantite(rs.getInt("QUANTITE"));
            commande.setDate(rs.getDate("DATE_COMMANDE"));

            commande.setClient(client);

            commande.setProduit(p);


        }
        return commande;
    }

    @Override
    public Commande save(Commande o) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("insert into commandes(ID_CLIENT,ID_PRODUIT,QUANTITE,DATE_COMMANDE) values (?,?,?,?)");
        stm.setInt(1,o.getClient().getId());
        stm.setInt(2,o.getProduit().getId());
        stm.setInt(3,o.getQuantite());
        stm.setDate(4, Date.valueOf(LocalDate.now()));
        stm.executeUpdate();
        return o;
    }

    @Override
    public boolean delete(Commande o) throws SQLException {
        try {
            Connection connection=SingletonConnexionBD.getConnection();
            PreparedStatement pstm = pstm = connection.prepareStatement("delete from commandes where ID_COMMANDE=?");
            pstm.setInt(1,o.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    @Override
    public Commande update(Commande o) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("update  commandes set ID_CLIENT=?,ID_PRODUIT=?,QUANTITE=? where ID_COMMANDE =?");
        stm.setInt(1,o.getClient().getId());
        stm.setInt(2,o.getProduit().getId());
        stm.setInt(3,o.getQuantite());
        stm.setInt(4,o.getId());
        stm.executeUpdate();
        return o;
    }

    @Override
    public List<Commande> findCommandeClient(int id) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("select D.DATE_COMMANDE,D.ID_COMMANDE,P.NOM_PRPDUIT,P.PRIX_PRODUIT,P.DESCRIPTION_PRODUIT,P.PRIX_PRODUIT,P.QUANTITE_STOCK,D.ID_CLIENT,D.ID_PRODUIT,D.QUANTITE ,C.NOM_CLIENT,C.GMAIL_CLIENT,C.TEL_CLIENT,C.ADRESSE_CLIENT from produits P,clients C,commandes D where D.ID_CLIENT=C.ID_CLIENT and D.ID_PRODUIT=P.ID_PRODUIT and D.ID_CLIENT=?;");
        stm.setInt(1,id);
        ResultSet rs = stm.executeQuery();
        List<Commande> commandes = new ArrayList<>();
        while(rs.next())
        {
            Produit p =new Produit();
            p.setId(rs.getInt("ID_PRODUIT"));
            p.setNom(rs.getString("NOM_PRPDUIT"));
            p.setDescription(rs.getString("DESCRIPTION_PRODUIT"));
            p.setPrix(rs.getFloat("PRIX_PRODUIT"));
            p.setQuantite(rs.getInt("QUANTITE_STOCK"));

            Client client =new Client();
            client.setId(rs.getInt("ID_CLIENT"));
            client.setNom(rs.getString("NOM_CLIENT"));
            client.setTel(rs.getString("TEL_CLIENT"));
            client.setTel(rs.getString("ADRESSE_CLIENT"));
            client.setGmail(rs.getString("GMAIL_CLIENT"));

            Commande commande = new Commande();
            commande.setId(rs.getInt("ID_COMMANDE"));
            commande.setQuantite(rs.getInt("QUANTITE"));
            commande.setDate(rs.getDate("DATE_COMMANDE"));

            commande.setClient(client);

            commande.setProduit(p);

            commandes.add(commande);
        }
        return commandes;
    }

    @Override
    public List<Commande> findCommandeFournisseur(int id) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("select D.DATE_COMMANDE,D.ID_COMMANDE,P.NOM_PRPDUIT,P.DESCRIPTION_PRODUIT,P.PRIX_PRODUIT,P.QUANTITE_STOCK,D.ID_CLIENT,D.ID_PRODUIT,D.QUANTITE ,C.NOM_CLIENT,C.GMAIL_CLIENT,C.TEL_CLIENT,C.ADRESSE_CLIENT from produits P,clients C,commandes D where D.ID_CLIENT=C.ID_CLIENT and D.ID_PRODUIT=P.ID_PRODUIT and P.ID_FOURNISSEUR=?;");
        stm.setInt(1,id);
        ResultSet rs = stm.executeQuery();
        List<Commande> commandes = new ArrayList<>();
        while(rs.next())
        {
            Produit p =new Produit();
            p.setId(rs.getInt("ID_PRODUIT"));
            p.setNom(rs.getString("NOM_PRPDUIT"));
            p.setDescription(rs.getString("DESCRIPTION_PRODUIT"));
            p.setPrix(rs.getFloat("PRIX_PRODUIT"));
            p.setQuantite(rs.getInt("QUANTITE_STOCK"));

            Client client =new Client();
            client.setId(rs.getInt("ID_CLIENT"));
            client.setNom(rs.getString("NOM_CLIENT"));
            client.setTel(rs.getString("TEL_CLIENT"));
            client.setTel(rs.getString("ADRESSE_CLIENT"));
            client.setGmail(rs.getString("GMAIL_CLIENT"));

            Commande commande = new Commande();
            commande.setId(rs.getInt("ID_COMMANDE"));
            commande.setQuantite(rs.getInt("QUANTITE"));
            commande.setDate(rs.getDate("DATE_COMMANDE"));

            commande.setClient(client);

            commande.setProduit(p);

            commandes.add(commande);
        }
        return commandes;
    }
}
