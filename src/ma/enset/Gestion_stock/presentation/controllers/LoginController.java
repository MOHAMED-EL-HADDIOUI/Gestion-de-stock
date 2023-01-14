package ma.enset.Gestion_stock.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class LoginController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private Button RegistringButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandineImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField enterPasswordField;
    @FXML
    private Button creteAccountButtonVender;
    @FXML
    private ComboBox<String> comboBox;

    public void loginButtonAction(ActionEvent event)
    {

        if (usernameTextField.getText().isEmpty()==false && enterPasswordField.getText().isEmpty()==false){
            validateLogin();
        }else
        {
            loginMessageLabel.setText("please enter user name and password");
        }

    }

    public void cancelButtonAction(ActionEvent event)
    {
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File brandingFile = new File("src/ma/enset/Gestion_stock/presentation/view/Images/blog.png");
        Image brandineImage = new Image(brandingFile.toURI().toString());
        brandineImageView.setImage(brandineImage);

        File lockFile = new File("src/ma/enset/Gestion_stock/presentation/view/Images/LOGIN.jpg");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
        comboBox.getItems().addAll("Vendor","Client");

    }

    public void validateLogin(){
        SingletonConnexionBD singletonConnexionBD = new SingletonConnexionBD();
        String Type = comboBox.getSelectionModel().getSelectedItem();
        Connection connection = singletonConnexionBD.getConnection();
        if(Type=="Client")
        {
            String verifyLogin="SELECT count(1) FROM clients WHERE GMAIL_CLIENT = '"+ usernameTextField.getText() + "'AND PASSWORD_CLIENT='" + enterPasswordField.getText() + "'";
            try {

                Statement statement = connection.createStatement();
                ResultSet queryResult = statement.executeQuery(verifyLogin);

                while (queryResult.next())
                {
                    if (queryResult.getInt(1) ==1)
                    {
                        DBUtils.reparationClient(new ActionEvent(),"../view/client/Profil.fxml",usernameTextField.getText(),enterPasswordField.getText(),"client");
                        Parent root = FXMLLoader.load(getClass().getResource("../view/client/Client.fxml"));
                        Stage fournisseurStage = new Stage();
                        fournisseurStage.setTitle("Client");
                        fournisseurStage.setScene(new Scene(root,700,600));
                        fournisseurStage.show();

                    }else
                    {
                        loginMessageLabel.setText("Invalid login try again");
                    }
                }

            }catch (Exception e)
            {
                e.printStackTrace();
                e.getCause();
            }
        }
        else {
            String verifyLogin="SELECT count(1) FROM fournisseurs WHERE GMAIL_FOURNISSEUR = '"+ usernameTextField.getText() + "'AND PASSWORD_FOURNISSEUR='" + enterPasswordField.getText() + "'";
            try {

                Statement statement = connection.createStatement();
                ResultSet queryResult = statement.executeQuery(verifyLogin);

                while (queryResult.next())
                {
                    if (queryResult.getInt(1) ==1)
                    {
                        DBUtils.reparationFournisseur(new ActionEvent(),"../view/fournisseur/Profil.fxml",usernameTextField.getText(),enterPasswordField.getText(),"fournisseur");
                        Parent root = FXMLLoader.load(getClass().getResource("../view/fournisseur/Fournisseur.fxml"));
                        Stage fournisseurStage = new Stage();
                        fournisseurStage.setTitle("Fournisseur");
                        fournisseurStage.setScene(new Scene(root,700,600));
                        fournisseurStage.show();
                    }else
                    {
                        loginMessageLabel.setText("Invalid login try again");
                    }
                }

            }catch (Exception e)
            {
                e.printStackTrace();
                e.getCause();
            }
        }

    }
    public void createAccountForm()
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/login/Register.fxml"));
            Stage registerStage = new Stage();
            registerStage.setTitle("Registring Page");
            registerStage.setScene(new Scene(root,700,600));
            registerStage.show();

        }catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void registrinButtonAction(ActionEvent event)
    {
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        createAccountForm();
    }
    public void createAccountFormVender()
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/login/RegisterVender.fxml"));
            Stage registerStage = new Stage();
            registerStage.setTitle("Vendor Registring Page ");
            registerStage.setScene(new Scene(root,700,600));
            registerStage.show();

        }catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void vendorregistrinButtonAction(ActionEvent event)
    {
        Stage stage = (Stage)creteAccountButtonVender.getScene().getWindow();
        createAccountFormVender();
    }


}
