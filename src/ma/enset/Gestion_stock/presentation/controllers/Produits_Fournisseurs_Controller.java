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

public class Produits_Fournisseurs_Controller implements Initializable {
    private static int id;
    @FXML
    private TextField nom;
    @FXML
    private TextArea description;
    @FXML
    private TextField prix;
    @FXML
    private TextField quantiteStock;
    @FXML
    private ComboBox<Categorie> categorie;
    @FXML
    private TableView<Produit> tableView;
    @FXML
    private TableColumn<Produit,String> colProduit;
    @FXML
    private TableColumn<Produit,String> colDescription;
    @FXML
    private TableColumn<Produit,Float> colPrix;
    @FXML
    private TableColumn<Produit,Integer> colQuantite;
    @FXML
    private TextField rechercher;
    @FXML
    private TableColumn<Produit,Categorie> colCategorie;

    ObservableList<Produit> produits= FXCollections.observableArrayList();

    private CatalogueService catalogueService = new CatalogueServiceImpl(new ProduitDaoImpl(), new CategorieDoaImpl(),new ClientDoaImpl(),new CommandeDoaImpl(),new FournisseurDoaImpl(),new ChatDoaImlp());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(id!=0) {
            try {
                colProduit.setCellValueFactory(new PropertyValueFactory<>("nom"));
                colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
                colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
                colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                colCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
                categorie.getItems().addAll(catalogueService.getAllCategories());
                tableView.setItems(produits);
                Fournisseur fournisseur = catalogueService.findOneFournisseur(id);
                loadProduits(fournisseur.getId());
                rechercher.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        try {
                            produits.clear();
                            produits.addAll(catalogueService.getProduitsFournisseurParMc(fournisseur.getId(), newValue));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private  void loadProduits(int id) throws SQLException {
        produits.clear();
        produits.addAll(catalogueService.getAllProduitsFournisseur(id));
    }
    @FXML
    private void addProduit() throws SQLException {
        if(id!=0) {
            String Nom = nom.getText();
            String Description = description.getText();
            float Prix = Float.parseFloat(prix.getText());
            int Quantite = Integer.parseInt(quantiteStock.getText());
            Categorie categorie1 = categorie.getSelectionModel().getSelectedItem();
            Fournisseur fournisseur = catalogueService.findOneFournisseur(id);
            if (Nom.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Veuillez chosisir categorie !!!");
                alert.show();
            } else {
                Produit p = new Produit();
                p.setNom(Nom);
                p.setPrix(Prix);
                p.setQuantite(Quantite);
                p.setDescription(Description);
                p.setCategorie(categorie1);
                p.setFournisseur(fournisseur);
                catalogueService.addProduit(p);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Le produit a été créée avec succès");
                alert.show();
                loadProduits(fournisseur.getId());
            }
        }
    }
    public void deleteProduit() throws SQLException {
        if(id!=0) {
            Produit c = tableView.getSelectionModel().getSelectedItem();
            Fournisseur fournisseur = catalogueService.findOneFournisseur(id);
            catalogueService.deleteProduit(c);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Le produit a été supprimé avec succès");
            alert.show();
            loadProduits(fournisseur.getId());
        }
    }
    public void deleteAllProduits() throws SQLException {
        if(id!=0) {
            catalogueService.deleteAllProduits();
            Fournisseur fournisseur = catalogueService.findOneFournisseur(id);
            loadProduits(fournisseur.getId());
        }
    }
    public void selectionProduit() throws SQLException {
        nom.clear();
        prix.clear();
        description.clear();
        quantiteStock.clear();
        Produit c =tableView.getSelectionModel().getSelectedItem();
        nom.setText(c.getNom());
        prix.setText(String.valueOf(c.getPrix()));
        description.setText(c.getDescription());
        quantiteStock.setText(String.valueOf(c.getQuantite()));
    }
    public void updateProduit() throws SQLException {
        if(id!=0) {
            Produit p = tableView.getSelectionModel().getSelectedItem();
            String Nom = nom.getText();
            String Description = description.getText();
            float Prix = Float.parseFloat(prix.getText());
            int Quantite = Integer.parseInt(quantiteStock.getText());
            Categorie categorie1 = categorie.getSelectionModel().getSelectedItem();
            Fournisseur fournisseur = catalogueService.findOneFournisseur(id);
            p.setNom(Nom);
            p.setPrix(Prix);
            p.setQuantite(Quantite);
            p.setDescription(Description);
            p.setCategorie(categorie1);
            catalogueService.updateProduit(p);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Le produit a été modifié avec succès");
            alert.show();
            loadProduits(fournisseur.getId());
            nom.clear();
            prix.clear();
            description.clear();
            quantiteStock.clear();
        }
    }

    public static void setId(int id) {
        Produits_Fournisseurs_Controller.id = id;
    }
}
