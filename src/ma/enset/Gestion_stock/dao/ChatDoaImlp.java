package ma.enset.Gestion_stock.dao;

import ma.enset.Gestion_stock.dao.entites.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChatDoaImlp implements ChatDoa{

    @Override
    public List<Chat> findAll() throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("select D.DATE_COMMANDE,D.ID_COMMANDE,P.NOM_PRODUIT,P.DESCRIPTION_PRODUIT,P.PRIX_PRODUIT,P.QUANTITE_STOCK,D.ID_CLIENT,D.ID_PRODUIT,D.QUANTITE ,C.NOM_CLIENT,C.GMAIL_CLIENT,C.TEL_CLIENT,C.ADRESSE_CLIENT from fournisseurs P,clients C,commandes D where D.ID_CLIENT=C.ID_CLIENT and D.ID_PRODUIT=P.ID_PRODUIT;");
        ResultSet rs = stm.executeQuery();
        List<Chat> chats = new ArrayList<>();
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

            Chat chat = new Chat();
            chat.setId(rs.getInt("ID_COMMANDE"));
            chat.setDate(rs.getDate("DATE_COMMANDE"));

            chat.setClient(client);


            chats.add(chat);
        }
        return chats;
    }

    @Override
    public Chat findOne(int id) throws SQLException {
        return null;
    }

    @Override
    public Chat save(Chat o) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("insert into chats(ID_CLIENT,ID_FOURNISSEUR,USER,MESSAGE,DATE_CHAT) values (?,?,?,?,?)");
        stm.setInt(1,o.getClient().getId());
        stm.setInt(2,o.getFournisseur().getId());
        stm.setString(3,o.getUser());
        stm.setString(4,o.getMessage());
        stm.setDate(5, Date.valueOf(LocalDate.now()));
        stm.executeUpdate();
        return o;
    }

    @Override
    public boolean delete(Chat o) throws SQLException {
        return false;
    }

    @Override
    public Chat update(Chat o) throws SQLException {
        return null;
    }

    @Override
    public List<Chat> findChartsClient(int id) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * from fournisseurs F, (select Cl.ID_CLIENT,Cl.NOM_CLIENT,Cl.TEL_CLIENT,Cl.ADRESSE_CLIENT,Cl.GMAIL_CLIENT,Cl.PASSWORD_CLIENT,C.MESSAGE,C.ID_FOURNISSEUR,C.DATE_CHAT,C.ID_CHAT,C.USER from chats C,clients Cl where Cl.ID_CLIENT=C.ID_CLIENT and Cl.ID_CLIENT=?) as tab WHERE tab.ID_FOURNISSEUR=F.ID_FOURNISSEUR ");
        stm.setInt(1,id);
        ResultSet rs = stm.executeQuery();
        List<Chat> chats = new ArrayList<>();
        while(rs.next())
        {
            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setId(rs.getInt("ID_FOURNISSEUR"));
            fournisseur.setNom(rs.getString("NOM_FOURNISSEUR"));
            fournisseur.setGmail(rs.getString("GMAIL_FOURNISSEUR"));
            fournisseur.setAdresse(rs.getString("ADRESSE_FOURNISSEUR"));
            fournisseur.setTel(rs.getString("TEL_FOURNISSEUR"));
            fournisseur.setPassword(rs.getString("PASSWORD_FOURNISSEUR"));

            Chat chat = new Chat();
            chat.setId(rs.getInt("ID_CHAT"));
            chat.setDate(rs.getDate("DATE_CHAT"));
            chat.setMessage(rs.getString("MESSAGE"));
            chat.setUser(rs.getString("USER"));

            Client client = new Client();
            client.setId(id);
            client.setNom(rs.getString("NOM_CLIENT"));
            client.setAdresse(rs.getString("ADRESSE_CLIENT"));
            client.setTel(rs.getString("TEL_CLIENT"));
            client.setGmail(rs.getString("GMAIL_CLIENT"));
            client.setPassword(rs.getString("PASSWORD_CLIENT"));

            chat.setClient(client);
            chat.setFournisseur(fournisseur);


            chats.add(chat);
        }
        return chats;
    }

    @Override
    public List<Chat> findChartsFournisseur(int id) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * from clients Cl, (select F.ID_FOURNISSEUR,F.NOM_FOURNISSEUR,F.TEL_FOURNISSEUR,F.ADRESSE_FOURNISSEUR,F.GMAIL_FOURNISSEUR,F.PASSWORD_FOURNISSEUR,C.MESSAGE,C.ID_CLIENT,C.DATE_CHAT,C.ID_CHAT,C.USER from chats C,fournisseurs F where F.ID_FOURNISSEUR=C.ID_FOURNISSEUR and F.ID_FOURNISSEUR=?) as tab WHERE tab.ID_CLIENT=Cl.ID_CLIENT ");
        stm.setInt(1,id);
        ResultSet rs = stm.executeQuery();
        List<Chat> chats = new ArrayList<>();
        while(rs.next())
        {
            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setId(rs.getInt("ID_FOURNISSEUR"));
            fournisseur.setNom(rs.getString("NOM_FOURNISSEUR"));
            fournisseur.setGmail(rs.getString("GMAIL_FOURNISSEUR"));
            fournisseur.setAdresse(rs.getString("ADRESSE_FOURNISSEUR"));
            fournisseur.setTel(rs.getString("TEL_FOURNISSEUR"));
            fournisseur.setPassword(rs.getString("PASSWORD_FOURNISSEUR"));

            Chat chat = new Chat();
            chat.setId(rs.getInt("ID_CHAT"));
            chat.setDate(rs.getDate("DATE_CHAT"));
            chat.setMessage(rs.getString("MESSAGE"));
            chat.setUser(rs.getString("USER"));

            Client client = new Client();
            client.setId(id);
            client.setNom(rs.getString("NOM_CLIENT"));
            client.setAdresse(rs.getString("ADRESSE_CLIENT"));
            client.setTel(rs.getString("TEL_CLIENT"));
            client.setGmail(rs.getString("GMAIL_CLIENT"));
            client.setPassword(rs.getString("PASSWORD_CLIENT"));

            chat.setClient(client);
            chat.setFournisseur(fournisseur);


            chats.add(chat);
        }
        return chats;
    }
    @Override
    public List<Chat> findChatsClientByMc(int id,String Mc) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * from fournisseurs F, (select Cl.ID_CLIENT,Cl.NOM_CLIENT,Cl.TEL_CLIENT,Cl.ADRESSE_CLIENT,Cl.GMAIL_CLIENT,Cl.PASSWORD_CLIENT,C.MESSAGE,C.ID_FOURNISSEUR,C.DATE_CHAT,C.ID_CHAT,C.USER from chats C,clients Cl where Cl.ID_CLIENT=C.ID_CLIENT and Cl.ID_CLIENT=?) as tab WHERE tab.ID_FOURNISSEUR=F.ID_FOURNISSEUR and F.NOM_FOURNISSEUR like ? ");
        pstm.setInt(1,id);
        pstm.setString(2,"%"+Mc+"%");
        ResultSet rs = pstm.executeQuery();
        List<Chat> chats = new ArrayList<>();
        while(rs.next())
        {
            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setId(rs.getInt("ID_FOURNISSEUR"));
            fournisseur.setNom(rs.getString("NOM_FOURNISSEUR"));
            fournisseur.setGmail(rs.getString("GMAIL_FOURNISSEUR"));
            fournisseur.setAdresse(rs.getString("ADRESSE_FOURNISSEUR"));
            fournisseur.setTel(rs.getString("TEL_FOURNISSEUR"));
            fournisseur.setPassword(rs.getString("PASSWORD_FOURNISSEUR"));

            Chat chat = new Chat();
            chat.setId(rs.getInt("ID_CHAT"));
            chat.setDate(rs.getDate("DATE_CHAT"));
            chat.setMessage(rs.getString("MESSAGE"));
            chat.setUser(rs.getString("USER"));

            Client client = new Client();
            client.setId(id);
            client.setNom(rs.getString("NOM_CLIENT"));
            client.setAdresse(rs.getString("ADRESSE_CLIENT"));
            client.setTel(rs.getString("TEL_CLIENT"));
            client.setGmail(rs.getString("GMAIL_CLIENT"));
            client.setPassword(rs.getString("PASSWORD_CLIENT"));

            chat.setClient(client);
            chat.setFournisseur(fournisseur);


            chats.add(chat);
        }
        return chats;

    }
    public List<Chat> findChatsFournisseurByMc(int id,String Mc) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * from clients Cl, (select F.ID_FOURNISSEUR,F.NOM_FOURNISSEUR,F.TEL_FOURNISSEUR,F.ADRESSE_FOURNISSEUR,F.GMAIL_FOURNISSEUR,F.PASSWORD_FOURNISSEUR,C.MESSAGE,C.ID_CLIENT,C.DATE_CHAT,C.ID_CHAT,C.USER from chats C,fournisseurs F where F.ID_FOURNISSEUR=C.ID_FOURNISSEUR and F.ID_FOURNISSEUR=?) as tab WHERE tab.ID_CLIENT=Cl.ID_CLIENT and Cl.NOM_CLIENT like ? ");
        pstm.setInt(1,id);
        pstm.setString(2,"%"+Mc+"%");
        ResultSet rs = pstm.executeQuery();
        List<Chat> chats = new ArrayList<>();
        while(rs.next())
        {
            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setId(rs.getInt("ID_FOURNISSEUR"));
            fournisseur.setNom(rs.getString("NOM_FOURNISSEUR"));
            fournisseur.setGmail(rs.getString("GMAIL_FOURNISSEUR"));
            fournisseur.setAdresse(rs.getString("ADRESSE_FOURNISSEUR"));
            fournisseur.setTel(rs.getString("TEL_FOURNISSEUR"));
            fournisseur.setPassword(rs.getString("PASSWORD_FOURNISSEUR"));

            Chat chat = new Chat();
            chat.setId(rs.getInt("ID_CHAT"));
            chat.setDate(rs.getDate("DATE_CHAT"));
            chat.setMessage(rs.getString("MESSAGE"));
            chat.setUser(rs.getString("USER"));
            Client client = new Client();
            client.setId(id);
            client.setNom(rs.getString("NOM_CLIENT"));
            client.setAdresse(rs.getString("ADRESSE_CLIENT"));
            client.setTel(rs.getString("TEL_CLIENT"));
            client.setGmail(rs.getString("GMAIL_CLIENT"));
            client.setPassword(rs.getString("PASSWORD_CLIENT"));

            chat.setClient(client);
            chat.setFournisseur(fournisseur);


            chats.add(chat);
        }
        return chats;

    }

}
