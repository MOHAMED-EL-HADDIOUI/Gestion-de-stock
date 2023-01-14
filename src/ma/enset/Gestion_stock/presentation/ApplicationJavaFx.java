package ma.enset.Gestion_stock.presentation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ApplicationJavaFx extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/login/Login.fxml"));
        primaryStage.setTitle("Gestion de stock");
        primaryStage.setScene(new Scene(root,700,600));
        primaryStage.show();
    }
}
