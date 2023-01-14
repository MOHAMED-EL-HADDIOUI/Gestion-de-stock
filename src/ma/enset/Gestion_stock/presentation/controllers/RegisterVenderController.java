package ma.enset.Gestion_stock.presentation.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import java.sql.Connection;
import javafx.scene.control.TextField;
import ma.enset.Gestion_stock.dao.SingletonConnexionBD;


import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import  java.sql.Ref;
import java.sql.Statement;
public class RegisterVenderController implements Initializable {
    @FXML
    private ImageView shildImageView;
    @FXML
    private Button closeButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private TextField setPasswordField;
    @FXML
    private TextField confirmPasswordField;

    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField telTextField;
    @FXML
    private TextField adressnameTextField;
    @FXML
    private TextField gmailTextField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File ShieldFile = new File("src/ma/enset/Gestion_stock/presentation/view/Images/icon-register.png");
        Image ShieldImage = new Image(ShieldFile.toURI().toString());
        shildImageView.setImage(ShieldImage);
    }


    public void closeButtonOnAction(ActionEvent event)
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void registerButtononAction(ActionEvent event)
    {
        if (setPasswordField.getText().equals(confirmPasswordField.getText())){
            registerUser();
            confirmPasswordLabel.setText("");

        }else {
            confirmPasswordLabel.setText("Password does not match");
            registrationMessageLabel.setText("");
        }


    }

    public void registerUser()
    {
        SingletonConnexionBD conx= new SingletonConnexionBD();
        Connection connection = conx.getConnection();

        String firstname =firstnameTextField.getText();
        String tel =telTextField.getText();
        String adrress =adressnameTextField.getText();
        String gmail =gmailTextField.getText();
        String password =setPasswordField.getText();

        String insertFields="INSERT INTO fournisseurs(NOM_FOURNISSEUR,TEL_FOURNISSEUR ,ADRESSE_FOURNISSEUR,GMAIL_FOURNISSEUR , \tPASSWORD_FOURNISSEUR ) VALUES('";
        String insertValues=firstname+"','"+tel+"','"+adrress+"','"+gmail+"','"+password+"')";
        String insertRegister=insertFields+insertValues;

        try{
            Statement statement=connection.createStatement();
            statement.executeUpdate(insertRegister);
            registrationMessageLabel.setText("User has been registred successfully");
        }catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
}
