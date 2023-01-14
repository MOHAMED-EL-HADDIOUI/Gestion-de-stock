package ma.enset.Gestion_stock.dao;

import ma.enset.Gestion_stock.dao.entites.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDoaImpl implements ClientDoa {
    @Override
    public List<Client> findAll() throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("select * from clients");
        ResultSet rs = stm.executeQuery();
        List<Client> clients = new ArrayList<>();
        while(rs.next())
        {
            Client p =new Client();
            p.setId(rs.getInt("ID_CLIENT"));
            p.setNom(rs.getString("NOM_CLIENT"));
            p.setTel(rs.getString("TEL_CLIENT"));
            p.setAdresse(rs.getString("ADRESSE_CLIENT"));
            p.setGmail(rs.getString("GMAIL_CLIENT"));
            clients.add(p);
        }
        return clients;
    }

    @Override
    public Client findOne(int id) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("select * from clients where ID_CLIENT=?");
        stm.setInt(1,id);
        Client p=null;
        ResultSet rs = stm.executeQuery();
        if (rs.next())
        {
            p=new Client();
            p.setId(rs.getInt("ID_CLIENT"));
            p.setNom(rs.getString("NOM_CLIENT"));
            p.setTel(rs.getString("TEL_CLIENT"));
            p.setGmail(rs.getString("GMAIL_CLIENT"));
            p.setAdresse(rs.getString("ADRESSE_CLIENT"));
            p.setPassword(rs.getString("PASSWORD_CLIENT"));
        }
        return p;
    }

    @Override
    public Client save(Client o) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("insert into clients(NOM_CLIENT,TEL_CLIENT,ADRESSE_CLIENT,GMAIL_CLIENT,PASSWORD_CLIENT) values (?,?,?,?,?)");
        stm.setString(1,o.getNom());
        stm.setString(2,o.getTel());
        stm.setString(3,o.getAdresse());
        stm.setString(4,o.getGmail());
        stm.setString(5,o.getPassword());
        stm.executeUpdate();
        return o;
    }

    @Override
    public boolean delete(Client o) throws SQLException {
        try {
            Connection connection=SingletonConnexionBD.getConnection();
            PreparedStatement pstm = pstm = connection.prepareStatement("delete from clients where ID_CLIENT=?");
            pstm.setInt(1,o.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public Client update(Client o) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement pstm = connection.prepareStatement("update  clients set NOM_CLIENT=?,TEL_CLIENT=?,ADRESSE_CLIENT=?,PASSWORD_CLIENT=?,GMAIL_CLIENT=? where ID_CLIENT=?");
        pstm.setString(1,o.getNom());
        pstm.setString(2,o.getTel());
        pstm.setString(3,o.getAdresse());
        pstm.setString(4,o.getPassword());
        pstm.setString(5,o.getGmail());
        pstm.setInt(6,o.getId());
        pstm.executeUpdate();
        return o;
    }
    @Override
    public void deleteAllClients() throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement pstm  = connection.prepareStatement("delete from clients");
        pstm.executeUpdate();
    }

    @Override
    public Client findUsernamePasswordClient(String username, String password) throws SQLException {
        Connection connection=SingletonConnexionBD.getConnection();
        PreparedStatement stm = connection.prepareStatement("select * from clients where GMAIL_CLIENT=? and PASSWORD_CLIENT=? ");
        stm.setString(1,username);
        stm.setString(2,password);
        Client p=null;
        ResultSet rs = stm.executeQuery();
        if (rs.next())
        {
            p=new Client();
            p.setId(rs.getInt("ID_CLIENT"));
            p.setNom(rs.getString("NOM_CLIENT"));
            p.setTel(rs.getString("TEL_CLIENT"));
            p.setAdresse(rs.getString("GMAIL_CLIENT"));
            p.setGmail(rs.getString("ADRESSE_CLIENT"));
            p.setPassword(rs.getString("PASSWORD_CLIENT"));
        }
        return p;
    }
}
