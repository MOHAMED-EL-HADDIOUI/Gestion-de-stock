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

public class CategorieControllers implements Initializable {
    @FXML
    private TextField nom;


    private CatalogueService catalogueService = new CatalogueServiceImpl(new ProduitDaoImpl(), new CategorieDoaImpl(),new ClientDoaImpl(),new CommandeDoaImpl(),new FournisseurDoaImpl(), new ChatDoaImlp());

    ObservableList<Categorie> categories = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void addCategorie() throws SQLException {
        String Nom = nom.getText();
        Categorie c =new Categorie();
        c.setNom(Nom);
        catalogueService.addCategorie(c);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("La catégorie a été créée avec succès");
        alert.show();
    }


}