package ma.enset.Gestion_stock.services;

import ma.enset.Gestion_stock.dao.ClientDoaImpl;
import ma.enset.Gestion_stock.dao.entites.*;

import java.sql.SQLException;
import java.util.List;

public interface CatalogueService {
    //                 produit
    void DiminuerQuantite(int id ,int quantite) throws SQLException;
    List<Produit> getAllProduits() throws SQLException;
    List<Produit> getAllProduitsFournisseur(int id) throws SQLException;

    List<Produit> getProduitsFournisseurParMc(int id,String mc) throws SQLException;

    void addProduit(Produit p) throws SQLException;

    void deleteProduit(Produit p) throws SQLException;

    void updateProduit(Produit p) throws SQLException;

    void deleteAllProduits() throws SQLException;

    List<Produit> getProduitsParMc(String mc) throws SQLException;

    //                 categorie
    List<Categorie> getAllCategories() throws SQLException;

    Categorie getCategorieById(int id) throws SQLException;

    void addCategorie(Categorie c) throws SQLException;

    void deleteCategorie(Categorie c) throws SQLException;

    void deleteAllCategoris() throws SQLException;

    //                 client
     Client findUsernamePasswordClient(String username, String password) throws SQLException;
    Client findOneClient(int id) throws SQLException;

    List<Client> getAllClients() throws SQLException;

    void addClient(Client p) throws SQLException;

    void deleteClient(Client p) throws SQLException;

    void updateClient(Client p) throws SQLException;

    void deleteAllClients() throws SQLException;

    //                 commande
    List<Commande> getAllCommandes() throws SQLException;

    List<Commande> getCommandesClient(int id) throws SQLException;

    List<Commande> getCommandesFournisseur(int id) throws SQLException;

    void addCommande(Commande p) throws SQLException;

    void deleteCommande(Commande p) throws SQLException;

    void updateCommande(Commande p) throws SQLException;

    //                 chat
    List<Chat> getChartsClient(int id) throws SQLException;
    List<Chat> getChartsFournisseur(int id) throws SQLException;

    public List<Chat> findChatsClientByMc(int id, String Mc) throws SQLException;
    public List<Chat> findChatsFournisseurByMc(int id, String Mc) throws SQLException;
    public Fournisseur findUsernamePassword(String username,String password) throws SQLException;

    //
    void addChat(Chat p) throws SQLException;

    List<Fournisseur> getAllFournisseurs() throws SQLException;
    Fournisseur findOneFournisseur(int id) throws SQLException;
    void updateFournisseur(Fournisseur p) throws SQLException;
}
