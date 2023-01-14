package ma.enset.Gestion_stock.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.enset.Gestion_stock.dao.*;
import ma.enset.Gestion_stock.dao.entites.Client;
import ma.enset.Gestion_stock.dao.entites.Fournisseur;
import ma.enset.Gestion_stock.services.CatalogueService;
import ma.enset.Gestion_stock.services.CatalogueServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfilFournisseurController implements Initializable {
    private static int id;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField tel;
    @FXML
    private TextArea adresse;

    private CatalogueService catalogueService = new CatalogueServiceImpl(new ProduitDaoImpl(), new CategorieDoaImpl(),new ClientDoaImpl(),new CommandeDoaImpl(),new FournisseurDoaImpl(),new ChatDoaImlp());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(id!=0){
            Fournisseur fournisseur = null;
            try {
                fournisseur = catalogueService.findOneFournisseur(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            MessageFournisseur.setId(fournisseur.getId());
            username.setText(fournisseur.getNom());
            email.setText(fournisseur.getGmail());
            password.setText(fournisseur.getPassword());
            tel.setText(String.valueOf(fournisseur.getTel()));
            adresse.setText(fournisseur.getAdresse());
            username.setDisable(true);
            email.setDisable(true);
            password.setDisable(true);
            tel.setDisable(true);
            adresse.setDisable(true);
        }

    }
    public void updateFournisseur() throws SQLException {
        Fournisseur fournisseur = catalogueService.findOneFournisseur(id);
        fournisseur.setNom(username.getText());
        fournisseur.setGmail(email.getText());
        fournisseur.setPassword(password.getText());
        fournisseur.setTel(tel.getText());
        fournisseur.setAdresse(adresse.getText());
        username.setDisable(true);
        email.setDisable(true);
        password.setDisable(true);
        tel.setDisable(true);
        adresse.setDisable(true);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Votre profil a été modifié avec succès");
        alert.show();
        catalogueService.updateFournisseur(fournisseur);
    }
    public void update() {
        username.setDisable(false);
        email.setDisable(false);
        password.setDisable(false);
        tel.setDisable(false);
        adresse.setDisable(false);
    }
    public void cancelButtonAction(ActionEvent event)
    {
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }
    public static void setId(int id) {
        ProfilFournisseurController.id = id;
    }
}
