package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DbConnect implements Initializable {

    @FXML
    Button nextbtn;

    public static ComboBox<String > combomonths;

    ObservableList<String> months = FXCollections.observableArrayList("January","February","March");


    public  Connection connect()

    {


        try {

            String db = combomonths.getValue();
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/","root","");



            return conn;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }


    }

    public void handleNext(){
        connect();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        combomonths.setItems(months);
    }

}
