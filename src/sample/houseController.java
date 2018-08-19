package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class houseController implements Initializable {

    public TextField txtno1, txttype,txtdeposit, txtrent, txtwater, txtgar, txtelec, txtfees, txtapart;
    public Button savebtn;
    public Label house;

    public Connection conn;


    public LoginModel loginModel;

    ObservableList<String> months = FXCollections.observableArrayList("January","February","March","April","May","June","July","August","September","October","November","December");


    private ObservableList<String> housetype = FXCollections.observableArrayList("Single","Bedsitter","1 Bedroom","2 Bedroom","3 Bedroom");

    @FXML
    public ComboBox<String> comboBox,comboBox1,comboBoxapart;

    @FXML
    ObservableList<String> data2 = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comboBox.setItems(housetype);
        comboBox1.setItems(months);
        comboBoxapart.setItems(data2);





        try {
            conn = MysqlConnector.connect();

            Statement stmt1 ;
            ResultSet rs1 ;


            stmt1 = conn.createStatement();
            rs1 = stmt1.executeQuery("SELECT apartment_name FROM apartment");

            while (rs1.next()) {
                data2.add(rs1.getString(1));

            }
        } catch (SQLException e) {

        }


        txtno1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtno1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        txtrent.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtrent.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        txtdeposit.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtdeposit.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        txtwater.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtwater.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        txtelec.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtelec.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        txtgar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtgar.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        txtfees.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtfees.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }

    public void handleSave(ActionEvent event) {

        if (txtno1.getText().trim().isEmpty()) {
            Alert();
            return;
        }
        if (txtrent.getText().trim().isEmpty()) {
            Alert();
            return;
        }
        if (txtdeposit.getText().trim().isEmpty()) {
            Alert();
            return;
        }
        else {

            conn = MysqlConnector.connect();

            try {
                String apartment = comboBoxapart.getValue();
                String hs = txtno1.getText();

                int houses = Integer.parseInt(hs);

                String housetype = comboBox.getValue();

                String rentpay = txtrent.getText();
                int rent = Integer.parseInt(rentpay);

                String depo = txtdeposit.getText();
                int deposit = Integer.parseInt(depo);

                String monthi = comboBox1.getValue();

                int[] mylist = new int[houses + 1];


                PreparedStatement preparedStatement, preparedStatement1;
                ResultSet resultSet = null;

                preparedStatement = conn.prepareStatement(" INSERT INTO " + apartment + "(house_no,house_type, rent, deposit, month)" + "values (?,?,?,?,?)");


                for (int i = 1; i < mylist.length; i++) {
                    mylist[i] = i + 1;

                    preparedStatement.setInt(1, i);
                    preparedStatement.setString(2, housetype);
                    preparedStatement.setInt(3, rent);
                    preparedStatement.setInt(4, deposit);
                    preparedStatement.setString(5,monthi);


                    preparedStatement.executeUpdate();


                }


                insertdata();


                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setHeaderText("Information Alert");
                String s = "Building created succesfully ";
                alert.setContentText(s);
                alert.show();


            } catch (SQLException e) {
                e.printStackTrace();
            }


            txtno1.clear();
            txtdeposit.clear();
            txtrent.clear();
            txtwater.clear();
            txtgar.clear();
            txtelec.clear();
            txtfees.clear();


        }

    }

    public void insertdata() {


        if (txtwater.getText().trim().isEmpty()) {
            Alert();
            return;
        }
        if (txtelec.getText().trim().isEmpty()) {
            Alert();
            return;
        }if (txtgar.getText().trim().isEmpty()) {
            Alert();
            return;
        }
        if (txtfees.getText().trim().isEmpty()) {
            Alert();
            return;
        } else {
            try {

                conn = MysqlConnector.connect();


                String apartment = comboBoxapart.getValue();
                String hs = txtno1.getText();

                int houses = Integer.parseInt(hs);


                String waterpay = txtwater.getText();
                int water = Integer.parseInt(waterpay);

                String electricity = txtelec.getText();
                int electric = Integer.parseInt(electricity);

                String garbage = txtgar.getText();
                int gar = Integer.parseInt(garbage);

                String fees = txtfees.getText();
                int fee = Integer.parseInt(fees);
                String month = comboBox1.getValue();


                int[] newlist = new int[houses + 1];


                PreparedStatement preparedStatement;

                preparedStatement = conn.prepareStatement("INSERT INTO fees (houseno,apartment, water,electricity,garbage,fee,monthstuff)" + "values (?,?,?,?,?,?,?)");


                for (int a = 1; a < newlist.length; a++) {
                    newlist[a] = a + 1;


                    preparedStatement.setInt(1, a);

                    preparedStatement.setString(2, apartment);
                    preparedStatement.setInt(3, water);
                    preparedStatement.setInt(4, electric);
                    preparedStatement.setInt(5, gar);
                    preparedStatement.setInt(6, fee);
                    preparedStatement.setString(7, month);


                    preparedStatement.executeUpdate();

                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
    public void Alert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText("Error");
        String s = "Please enter Details ";
        alert.setContentText(s);
        alert.show();

    }



}
