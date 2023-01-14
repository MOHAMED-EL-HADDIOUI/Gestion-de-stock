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

public class MessageClient implements Initializable {
    private static int id;
    @FXML
    private ComboBox<Fournisseur> fournisseur;
    @FXML
    private TextArea message;
    @FXML
    private TextField rechercher;
    @FXML
    private TableView<Chat> tableView;
    @FXML
    private TableColumn<Chat, Date> colDateMessage;
    @FXML
    private TableColumn<Chat,Fournisseur> colFournisseur;
    @FXML
    private TableColumn<Chat, String> colEmetteur;
    @FXML
    private TableColumn<Chat,String> colMessage;
    @FXML
    ObservableList<Chat> chats= FXCollections.observableArrayList();
    private CatalogueService catalogueService = new CatalogueServiceImpl(new ProduitDaoImpl(), new CategorieDoaImpl(),new ClientDoaImpl(),new CommandeDoaImpl(),new FournisseurDoaImpl(),new ChatDoaImlp());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(id!=0) {
            colDateMessage.setCellValueFactory(new PropertyValueFactory<>("date"));
            colFournisseur.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));
            colMessage.setCellValueFactory(new PropertyValueFactory<>("message"));
            colEmetteur.setCellValueFactory(new PropertyValueFactory<>("user"));
            tableView.setItems(chats);
            try {
                fournisseur.getItems().addAll(catalogueService.getAllFournisseurs());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Client client = null;
            try {
                client = catalogueService.findOneClient(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                loadChats(client.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Client finalClient = client;
            rechercher.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    try {
                        chats.clear();
                        chats.addAll(catalogueService.findChatsClientByMc(finalClient.getId(), newValue));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
        }
    }
    public void loadChats(int id) throws SQLException {
        chats.clear();
        chats.addAll(catalogueService.getChartsClient(id));
    }
    public void  findmessagefournisseur()
    {

    }
    public void addChat() throws SQLException {
        if(id!=0) {
            String messge1 = message.getText();
            Fournisseur fournisseur1 = fournisseur.getSelectionModel().getSelectedItem();
            Client client1 = catalogueService.findOneClient(id);
            if (fournisseur1 == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Veuillez choisir une fournisseur !!!");
                alert.show();
            } else {
                Chat p = new Chat();
                p.setClient(client1);
                p.setUser("client");
                p.setMessage(messge1);
                p.setFournisseur(fournisseur1);
                catalogueService.addChat(p);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Le message a été envoyé avec succès");
                alert.show();
                loadChats(client1.getId());
            }
        }

    }

    public static void setId(int id) {
        MessageClient.id = id;
    }
}
