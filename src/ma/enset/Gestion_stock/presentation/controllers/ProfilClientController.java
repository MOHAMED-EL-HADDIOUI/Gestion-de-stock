package ma.enset.Gestion_stock.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ma.enset.Gestion_stock.dao.*;
import ma.enset.Gestion_stock.dao.entites.Client;
import ma.enset.Gestion_stock.services.CatalogueService;
import ma.enset.Gestion_stock.services.CatalogueServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfilClientController implements Initializable {
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
        if(id!=0) {
            Client client = null;
            try {
                client = catalogueService.findOneClient(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            username.setText(client.getNom());
            email.setText(client.getGmail());
            password.setText(client.getPassword());
            tel.setText(String.valueOf(client.getTel()));
            adresse.setText(client.getAdresse());
            username.setDisable(true);
            email.setDisable(true);
            password.setDisable(true);
            tel.setDisable(true);
            adresse.setDisable(true);
        }
    }
    public void cancelButtonAction(ActionEvent event)
    {
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }
    public void updateClient() throws SQLException {
        if(id!=0) {
            Client client = catalogueService.findOneClient(id);
            client.setNom(username.getText());
            client.setGmail(email.getText());
            client.setPassword(password.getText());
            client.setTel(tel.getText());
            client.setAdresse(adresse.getText());
            catalogueService.updateClient(client);
            username.setDisable(true);
            email.setDisable(true);
            password.setDisable(true);
            tel.setDisable(true);
            adresse.setDisable(true);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Votre profil a été modifié avec succès");
            alert.show();
        }
    }
    public void update() {
        username.setDisable(false);
        email.setDisable(false);
        password.setDisable(false);
        tel.setDisable(false);
        adresse.setDisable(false);
    }

    public static void setId(int id) {
        ProfilClientController.id = id;
    }
}
