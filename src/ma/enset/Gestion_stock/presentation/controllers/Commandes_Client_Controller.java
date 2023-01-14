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
import ma.enset.Gestion_stock.dao.entites.*;
import ma.enset.Gestion_stock.services.CatalogueService;
import ma.enset.Gestion_stock.services.CatalogueServiceImpl;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;


public class Commandes_Client_Controller implements Initializable {
    private static int id;
    @FXML
    private ComboBox<Produit> produit;
    @FXML
    private TextField quantite;
    @FXML
    private TableView<Commande> tableView;
    @FXML
    private TableColumn<Commande,Produit> colProduit;
    @FXML
    private TableColumn<Commande, Date> colDate;
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
                colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
                colQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                tableView.setItems(commandes);
                produit.getItems().addAll(catalogueService.getAllProduits());
                Client client1 = catalogueService.findOneClient(id);
                loadCommandes(client1.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private  void loadCommandes(int id) throws SQLException {
        commandes.clear();
        commandes.addAll(catalogueService.getCommandesClient(id));
    }
    @FXML
    private void addCommande() throws SQLException {
        if(id!=0) {
            int Quantite = Integer.parseInt(quantite.getText());
            Produit produit1 = produit.getSelectionModel().getSelectedItem();
            Client client1 = catalogueService.findOneClient(id);
            if (produit1 == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Veuillez choisir une produit !!!");
                alert.show();
            } else {
                Commande p = new Commande();
                p.setQuantite(Quantite);
                p.setProduit(produit1);
                p.setClient(client1);
                catalogueService.addCommande(p);
                catalogueService.DiminuerQuantite(produit1.getId(),Quantite);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("La commande a été créée avec succès");
                alert.show();
                loadCommandes(client1.getId());
            }
        }
    }

    public static void setId(int id) {
        Commandes_Client_Controller.id = id;
    }
}
