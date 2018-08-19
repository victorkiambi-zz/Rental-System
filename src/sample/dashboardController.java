package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class dashboardController implements Initializable {

    public ImageView imageView1, imageView2, imageView3;
    
    public Button btn1,btn2,btn3;
    
     @FXML
    private Stage stage;

    public Parent root;

    @FXML
    BorderPane borderPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }

    public void handleApart(ActionEvent event) {
        if (event.getSource() == btn1) {
            stage = (Stage) btn1.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Apartment.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();

                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }



        }



    }


    public void handleTen(ActionEvent event) {
        if (event.getSource() == btn2) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tenant.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();

                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        borderPane.setCenter(root);

    }

    public void handlePay(ActionEvent event) {
        if (event.getSource() == btn3) {
            stage = (Stage) btn2.getScene().getWindow();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("payment.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();

                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }




}
