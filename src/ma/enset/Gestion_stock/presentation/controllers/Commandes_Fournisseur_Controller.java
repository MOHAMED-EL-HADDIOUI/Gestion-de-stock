package ma.enset.Gestion_stock.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ma.enset.Gestion_stock.dao.entites.Client;
import ma.enset.Gestion_stock.dao.entites.Commande;
import ma.enset.Gestion_stock.dao.entites.Produit;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.Gestion_stock.dao.*;
import ma.enset.Gestion_stock.dao.entites.*;
import ma.enset.Gestion_stock.services.CatalogueService;
import ma.enset.Gestion_stock.services.CatalogueServiceImpl;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class Commandes_Fournisseur_Controller implements Initializable {
    private static int id;
    @FXML
    private TableView<Commande> tableView;
    @FXML
    private TableColumn<Commande, Client> colClient;
    @FXML
    private TableColumn<Commande, Date> colDatecommande;
    @FXML
    private TableColumn<Commande,Produit> colProduit;
    @FXML
    private TableColumn<Commande, Integer> colQuantite;
    @FXML
    ObservableList<Commande> commandes= FXCollections.observableArrayList();
    private CatalogueService catalogueService = new CatalogueServiceImpl(new ProduitDaoImpl(), new CategorieDoaImpl(),new ClientDoaImpl(),new CommandeDoaImpl(),new FournisseurDoaImpl(),new ChatDoaImlp());

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(id!=0) {
            try {
                colProduit.setCellValueFactory(new PropertyValueFactory<>("produit"));
                colClient.setCellValueFactory(new PropertyValueFactory<>("client"));
                colDatecommande.setCellValueFactory(new PropertyValueFactory<>("date"));
                colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                tableView.setItems(commandes);
                Fournisseur fournisseur = catalogueService.findOneFournisseur(id);
                loadCommandes(fournisseur.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private  void loadCommandes(int id) throws SQLException {
        commandes.clear();
        commandes.addAll(catalogueService.getCommandesFournisseur(id));
    }

    public static void setId(int id) {
        Commandes_Fournisseur_Controller.id = id;
    }

}
