package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.xml.soap.Text;
import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MysqlConnector implements  Initializable{

public static ComboBox<String > combomonths;


    @FXML
    Button button;

    ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March", "April");



    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }
    public static Connection connect() {



        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/rental","root","");

            return conn;

        } catch (Exception e) {
           System.out.println(e);
           return null;
        }


    }

    private static String getCombo() {
       String db = combomonths.getValue();
        return db;
    }





    public void handleNext(){
        connect();
    }


}
