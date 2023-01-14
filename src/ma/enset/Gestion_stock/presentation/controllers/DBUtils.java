package ma.enset.Gestion_stock.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ma.enset.Gestion_stock.dao.*;
import ma.enset.Gestion_stock.dao.entites.Client;
import ma.enset.Gestion_stock.dao.entites.Fournisseur;
import ma.enset.Gestion_stock.services.CatalogueService;
import ma.enset.Gestion_stock.services.CatalogueServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

public class DBUtils {
    public static void  reparationFournisseur(ActionEvent actionEvent,String fxmlfile,String username,String password,String typeUser)
    {
        Parent root = null;
        if(username!=null && password!=null && typeUser!=null)
        {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlfile));
                root = loader.load();
                ProfilFournisseurController profilFournisseurController = loader.getController();
                CatalogueService catalogueService = new CatalogueServiceImpl(new ProduitDaoImpl(), new CategorieDoaImpl(),new ClientDoaImpl(),new CommandeDoaImpl(),new FournisseurDoaImpl(),new ChatDoaImlp());
                Fournisseur fournisseur = catalogueService.findUsernamePassword(username,password);
                ProfilFournisseurController.setId(fournisseur.getId());
                Produits_Fournisseurs_Controller.setId(fournisseur.getId());
                MessageFournisseur.setId(fournisseur.getId());
                Commandes_Fournisseur_Controller.setId(fournisseur.getId());

            }catch (IOException e)
            {
                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public static void  reparationClient(ActionEvent actionEvent,String fxmlfile,String username,String password,String typeUser)
    {
        Parent root = null;
        if(username!=null && password!=null && typeUser!=null)
        {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlfile));
                root = loader.load();
                ProfilClientController profilClientController = loader.getController();
                CatalogueService catalogueService = new CatalogueServiceImpl(new ProduitDaoImpl(), new CategorieDoaImpl(),new ClientDoaImpl(),new CommandeDoaImpl(),new FournisseurDoaImpl(),new ChatDoaImlp());
                Client client = catalogueService.findUsernamePasswordClient(username,password);
                ProfilClientController.setId(client.getId());
                Produits_Client_Controller.setId(client.getId());
                MessageClient.setId(client.getId());
                Commandes_Client_Controller.setId(client.getId());

            }catch (IOException e)
            {
                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
