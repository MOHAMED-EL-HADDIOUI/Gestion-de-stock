package ma.enset.Gestion_stock.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.Gestion_stock.dao.*;
import ma.enset.Gestion_stock.dao.entites.Categorie;
import ma.enset.Gestion_stock.dao.entites.Fournisseur;
import ma.enset.Gestion_stock.dao.entites.Produit;
import ma.enset.Gestion_stock.services.CatalogueService;
import ma.enset.Gestion_stock.services.CatalogueServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Produits_Client_Controller implements Initializable {
    private static int id;

    @FXML
    private TableView<Produit> tableView;
    @FXML
    private TableColumn<Produit,String> colProduit;
    @FXML
    private TableColumn<Produit,Float> colPrix;
    @FXML
    private TableColumn<Produit,Categorie> colCategorie;
    @FXML
    private TableColumn<Produit,String> colDescription;
    @FXML
    private TableColumn<Produit,Integer> colQuantite;
    @FXML
    private TableColumn<Produit,Fournisseur> colFournisseur;
    @FXML
    private TextField rechercher;

    ObservableList<Produit> produits= FXCollections.observableArrayList();

    private CatalogueService catalogueService = new CatalogueServiceImpl(new ProduitDaoImpl(), new CategorieDoaImpl(),new ClientDoaImpl(),new CommandeDoaImpl(),new FournisseurDoaImpl(),new ChatDoaImlp());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(id!=0) {
            colProduit.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            colCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            colFournisseur.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));
            tableView.setItems(produits);
            try {
                loadProduits();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            rechercher.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    try {
                        produits.clear();
                        produits.addAll(catalogueService.getProduitsParMc(newValue));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
        }


    }
    private  void loadProduits() throws SQLException {
        produits.clear();
        produits.addAll(catalogueService.getAllProduits());
    }

    public static void setId(int id) {
        Produits_Client_Controller.id = id;
    }
}
