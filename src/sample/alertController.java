package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class alertController implements Initializable {


    @FXML
    public TextField txtsearch, txtid, txtrent, txtdepo, txtgarbage, txtwater, txtelectric, txtbalance, txtrentpay, txtapartment;

    @FXML
    public Label labeldate,labelid,labelname,labelbuild,labelhouse,labelrent,labeldepo,labelwater,labelelectric,labelgarbage,labelfees,labelmonth,labelbalance;


    @FXML
    public DatePicker datePicker;


    Connection conn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {








    }

    public void setText(String id_no){


        this.labelid.setText(id_no);

    }


   /* public void setText(String id,String depo,String rentpay,String waterpay,String electricity,String bal,String gar,String renti) {
        this.txtid.setText(id);
        this.txtrent.setText(rentpay);
        this.txtelectric.setText(electricity);
        this.txtbalance.setText(bal);
        this.txtgarbage.setText(gar);
        this.txtwater.setText(waterpay);
        this.txtdepo.setText(depo);
        this.txtrentpay.setText(renti);
    }

    public void setString(Date date){

        this.datePicker.setId(String.valueOf(date));


    }

    public void handleSave(ActionEvent event){

        conn = MysqlConnector.connect();


        try {
            String depo = txtdepo.getText();
            int deposit = Integer.parseInt(depo);


            String id_no = txtid.getText();
            int id = Integer.parseInt(id_no);

            String rentpay = txtrent.getText();
            int rent = Integer.parseInt(rentpay);


            String waterpay = txtwater.getText();
            int water = Integer.parseInt(waterpay);
            String gar = txtgarbage.getText();
            int garbage = Integer.parseInt(gar);

            String electricity = txtelectric.getText();
            int electric = Integer.parseInt(electricity);

            String bal = txtbalance.getText();
            int balance = Integer.parseInt(bal);
            System.out.println(balance);

            java.sql.Date getDate = java.sql.Date.valueOf(datePicker.getValue());






            PreparedStatement preparedStatement;
            ResultSet resultSet = null;

            preparedStatement = conn.prepareStatement(" INSERT INTO payment (id_number ,deposit, rent, water,garbage,electricity,balance,date )" + "values (?,?,?,?,?,?,?,?)");


            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, deposit);
            preparedStatement.setInt(3, rent);
            preparedStatement.setInt(4, water);
            preparedStatement.setInt(5, garbage);
            preparedStatement.setInt(6, electric);
            preparedStatement.setInt(7, balance);
            preparedStatement.setDate(8, getDate);


            preparedStatement.execute();




        } catch (SQLException e) {
            e.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        String s = "Success  ";
        alert.setContentText(s);
        alert.show();


        txtdepo.clear();

        txtbalance.clear();
        txtelectric.clear();
        txtgarbage.clear();
        txtwater.clear();
        txtrent.clear();

        txtrentpay.clear();

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }*/


        }



