package ma.enset.Gestion_stock.services;

import ma.enset.Gestion_stock.dao.*;
import ma.enset.Gestion_stock.dao.entites.*;
import sun.misc.Cleaner;

import java.sql.SQLException;
import java.util.List;


public class CatalogueServiceImpl implements CatalogueService {
    ProduitDoa produitDoa;
    CategorieDoa categorieDoa;
    ClientDoa clientDoa;
    CommandeDoa commandeDoa;
    FournisseurDoa fournisseurDoa;
    ChatDoa chatDoa;

    public CatalogueServiceImpl(ProduitDoa produitDoa, CategorieDoa categorieDoa, ClientDoa clientDoa, CommandeDoa commandeDoa, FournisseurDoa fournisseurDoa, ChatDoa chatDoa) {
        this.produitDoa = produitDoa;
        this.categorieDoa = categorieDoa;
        this.clientDoa = clientDoa;
        this.commandeDoa = commandeDoa;
        this.fournisseurDoa = fournisseurDoa;
        this.chatDoa = chatDoa;
    }

    @Override
    public void DiminuerQuantite(int id, int quantite) throws SQLException {
        this.produitDoa.DiminuerQuantite(id,quantite);
    }

    @Override
    public List<Produit> getAllProduits() throws SQLException {
        return produitDoa.findAll();
    }

    @Override
    public List<Produit> getAllProduitsFournisseur(int id) throws SQLException {
        return produitDoa.findAllFournisseur(id);
    }

    @Override
    public List<Produit> getProduitsFournisseurParMc(int id, String mc) throws SQLException {
        return produitDoa.findProduitFournisseurByMc(id,mc);
    }

    @Override
    public void addProduit(Produit p) throws SQLException {
        produitDoa.save(p);
    }

    @Override
    public void deleteProduit(Produit p) throws SQLException {
        produitDoa.delete(p);
    }

    @Override
    public void updateProduit(Produit p) throws SQLException {
        produitDoa.update(p);
    }

    @Override
    public List<Produit> getProduitsParMc(String mc) throws SQLException {
        return produitDoa.findProduitByMc(mc);
    }

    @Override
    public List<Categorie> getAllCategories() throws SQLException {
        return categorieDoa.findAll();
    }

    @Override
    public Categorie getCategorieById(int id) throws SQLException {
        return categorieDoa.findOne(id);
    }

    @Override
    public void addCategorie(Categorie c) throws SQLException {
        categorieDoa.save(c);
    }
    @Override
    public void deleteCategorie(Categorie c) throws SQLException {
        categorieDoa.delete(c);
    }
    @Override
    public void deleteAllProduits() throws SQLException {
        produitDoa.deleteAllProduits();
    }

    @Override
    public void deleteAllCategoris() throws SQLException {
        categorieDoa.deleteAllCategories();
    }

    @Override
    public Client findUsernamePasswordClient(String username, String password) throws SQLException {
        return clientDoa.findUsernamePasswordClient(username,password);
    }

    @Override
    public Client findOneClient(int id) throws SQLException {
        return clientDoa.findOne(id);
    }

    @Override
    public List<Client> getAllClients() throws SQLException {
        return clientDoa.findAll();
    }

    @Override
    public void addClient(Client p) throws SQLException {
        clientDoa.save(p);
    }

    @Override
    public void deleteClient(Client p) throws SQLException {
        clientDoa.delete(p);
    }

    @Override
    public void updateClient(Client p) throws SQLException {
        clientDoa.update(p);
    }

    @Override
    public void deleteAllClients() throws SQLException {
        clientDoa.deleteAllClients();
    }

    @Override
    public List<Commande> getAllCommandes() throws SQLException {
        return commandeDoa.findAll();
    }

    @Override
    public List<Commande> getCommandesClient(int id) throws SQLException {
        return commandeDoa.findCommandeClient(id);
    }

    @Override
    public List<Commande> getCommandesFournisseur(int id) throws SQLException {
        return commandeDoa.findCommandeFournisseur(id);
    }

    @Override
    public void addCommande(Commande p) throws SQLException {
        commandeDoa.save(p);
    }

    @Override
    public void deleteCommande(Commande p) throws SQLException {
        commandeDoa.delete(p);
    }

    @Override
    public void updateCommande(Commande p) throws SQLException {
        commandeDoa.update(p);
    }

    @Override
    public List<Chat> getChartsClient(int id) throws SQLException {
        return chatDoa.findChartsClient(id);
    }

    @Override
    public List<Chat> getChartsFournisseur(int id) throws SQLException {
        return chatDoa.findChartsFournisseur(id);
    }

    @Override
    public List<Chat> findChatsClientByMc(int id, String Mc) throws SQLException {
        return chatDoa.findChatsClientByMc(id,Mc);
    }
    @Override
    public List<Chat> findChatsFournisseurByMc(int id, String Mc) throws SQLException {
        return chatDoa.findChatsFournisseurByMc(id,Mc);
    }

    @Override
    public Fournisseur findUsernamePassword(String username, String password) throws SQLException {
        return fournisseurDoa.findUsernamePassword(username,password);
    }

    @Override
    public void addChat(Chat p) throws SQLException {
        chatDoa.save(p);
    }

    @Override
    public List<Fournisseur> getAllFournisseurs() throws SQLException {
        return fournisseurDoa.findAll();
    }

    @Override
    public Fournisseur findOneFournisseur(int id) throws SQLException {
        return  fournisseurDoa.findOne(id);
    }

    @Override
    public void updateFournisseur(Fournisseur p) throws SQLException {
        fournisseurDoa.update(p);
    }


}
