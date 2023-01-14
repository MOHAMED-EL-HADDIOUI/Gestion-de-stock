package ma.enset.Gestion_stock.dao;

import ma.enset.Gestion_stock.dao.entites.Categorie;
import ma.enset.Gestion_stock.dao.entites.Chat;

import java.sql.SQLException;
import java.util.List;

public interface ChatDoa extends Dao<Chat>{
    public List<Chat> findChartsClient(int id) throws SQLException;
    public List<Chat> findChartsFournisseur(int id) throws SQLException;
    public List<Chat> findChatsClientByMc(int id,String Mc) throws SQLException;
    public List<Chat> findChatsFournisseurByMc(int id,String Mc) throws SQLException;

}
