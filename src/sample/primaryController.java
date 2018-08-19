package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class primaryController implements Initializable {


    @FXML
    public Button button1, button2, button3, button4,dashbtn,apartbtn,tenbtn,paybtn;

    public Separator separator1;


    @FXML
    private Stage stage;

    public Parent root;

    @FXML
    public BorderPane borderPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleScene1(ActionEvent event) {
        if (event.getSource() == button1) {
            stage = (Stage) button1.getScene().getWindow();
            try {
                root = FXMLLoader.load(getClass().getResource("apartment.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root);
            borderPane.setCenter(root);

        }



    }


    public void handleScene2(ActionEvent event) {
        if (event.getSource() == button2) {
            stage = (Stage) button2.getScene().getWindow();
            try {
                root = FXMLLoader.load(getClass().getResource("tenant.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Scene scene = new Scene(root);
        borderPane.setCenter(root);

    }

    public void handleScene3(ActionEvent event) {
        if (event.getSource() == button3) {
            stage = (Stage) button3.getScene().getWindow();
            try {
                root = FXMLLoader.load(getClass().getResource("payment.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Scene scene = new Scene(root);
        borderPane.setCenter(root);
    }

    public void handleScene4(ActionEvent event) {
        if (event.getSource() == dashbtn) {
            stage = (Stage) dashbtn.getScene().getWindow();
            try {
                root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Scene scene = new Scene(root);
        borderPane.setCenter(root);

    }

    public void handleApart(ActionEvent event) {
        if (event.getSource() == apartbtn) {
            stage = (Stage) apartbtn.getScene().getWindow();
            try {
                root = FXMLLoader.load(getClass().getResource("apartment.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root);
            borderPane.setCenter(root);

        }



    }


    public void handleTen(ActionEvent event) {
        if (event.getSource() == tenbtn) {
            stage = (Stage) tenbtn.getScene().getWindow();
            try {
                root = FXMLLoader.load(getClass().getResource("tenant.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Scene scene = new Scene(root);
        borderPane.setCenter(root);

    }

    public void handlePay(ActionEvent event) {
        if (event.getSource() == paybtn) {
            stage = (Stage) paybtn.getScene().getWindow();
            try {
                root = FXMLLoader.load(getClass().getResource("payment.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Scene scene = new Scene(root);
        borderPane.setCenter(root);
    }



    }











