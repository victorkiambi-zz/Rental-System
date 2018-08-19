package sample;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class updatesController implements Initializable {


    @FXML
    ComboBox<String> compay, comMonth;

    @FXML
    TextField tid, tbal, tpaid, tnewbal, tpaidby, tbal1, tpaid1, tnewbal1, tpaidby1, tbal2, tpaid2, tnewbal2, tpaidby2, tbal3, tpaid3, tnewbal3, tpaidby3, tbal4, tpaid4, tnewbal4, tpaidby4, tbal5, tpaid5, tnewbal5, tpaidby5;

    @FXML
    TextField brent,bdepo,bwater,bgar,bfee,belec;

    @FXML
    Button savebtn, savebtn1, savebtn2, savebtn3, savebtn4, savebtn5;

    @FXML
    TabPane tabPane;

    @FXML
    Tab  tabrent, tabwater, tabdepo, tabelec, tabgar, tabfee;
    @FXML
    DatePicker datePicker;

    ObservableList data;

    ObservableList data2;

    ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

    ObservableList<String> pay = FXCollections.observableArrayList("Rent", "Water", "Garbage", "2 Bedroom", "3 Bedroom");

    Connection conn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comMonth.setItems(months);


        datePicker.setValue(LocalDate.now());


        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, old, newv) -> {



            if (comMonth.getValue() == null) {
                Alert();
                return;

            } else {

                if (newv == tabrent) {

                    conn = MysqlConnector.connect();


                    data2 = FXCollections.observableArrayList();

                    String month = comMonth.getValue();
                    String id = tid.getText();

                    Statement stmt;
                    ResultSet rs;
                    try {
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery("SELECT rent_balance  FROM balance   WHERE tenant_id =" + id + " AND month ='"+month+"'");

                        while (rs.next()) {
                            tbal.setText(String.valueOf(rs.getInt(1)));


                        }
                    } catch (SQLException e) {

                        e.printStackTrace();

                    }

                }

                if (newv == tabdepo) {

                    conn = MysqlConnector.connect();


                    data2 = FXCollections.observableArrayList();
                    String month = comMonth.getValue();
                    String id = tid.getText();

                    Statement stmt;
                    ResultSet rs;
                    try {
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery("SELECT deposit_balance  FROM balance   WHERE tenant_id =" + id + " AND month ='"+month+"'");

                        while (rs.next()) {
                            tbal1.setText(String.valueOf(rs.getInt(1)));

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                if (newv == tabwater) {
                    conn = MysqlConnector.connect();


                    data2 = FXCollections.observableArrayList();
                    String month = comMonth.getValue();
                    String id = tid.getText();

                    Statement stmt;
                    ResultSet rs;
                    try {
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery("SELECT water_balance  FROM balance   WHERE tenant_id =" + id + " AND month ='"+month+"'");

                        while (rs.next()) {
                            tbal2.setText(String.valueOf(rs.getInt(1)));

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                }

                if (newv == tabelec) {

                    conn = MysqlConnector.connect();


                    data2 = FXCollections.observableArrayList();
                    String month = comMonth.getValue();
                    String id = tid.getText();

                    Statement stmt;
                    ResultSet rs;
                    try {
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery("SELECT electricity_balance  FROM balance   WHERE tenant_id =" + id + " AND month ='"+month+"'");

                        while (rs.next()) {
                            tbal3.setText(String.valueOf(rs.getInt(1)));

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }

                if (newv == tabgar) {

                    conn = MysqlConnector.connect();


                    data2 = FXCollections.observableArrayList();
                    String month = comMonth.getValue();
                    String id = tid.getText();

                    Statement stmt;
                    ResultSet rs;
                    try {
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery("SELECT garbage_balance  FROM balance   WHERE tenant_id =" + id + " AND month ='"+month+"'");

                        while (rs.next()) {
                            tbal4.setText(String.valueOf(rs.getInt(1)));


                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if (newv == tabfee) {

                    conn = MysqlConnector.connect();


                    data2 = FXCollections.observableArrayList();
                    String month = comMonth.getValue();
                    String id = tid.getText();

                    Statement stmt;
                    ResultSet rs;
                    try {
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery("SELECT fees_balance  FROM balance   WHERE tenant_id =" + id + " AND month ='"+month+"'");

                        while (rs.next()) {
                            tbal5.setText(String.valueOf(rs.getInt(1)));


                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
    }

    public void Alert(){

        Alert alert = new Alert(Alert.AlertType.ERROR);


        String s = "Please Enter Details ";
        alert.setContentText(s);
        alert.show();
    }



    public void handleSearch(ActionEvent event){


        if (tid.getText().trim().isEmpty()){
            Alert();
            return;
        }if (comMonth.getValue()== null){
            Alert();
            return;
        } else {

            conn = MysqlConnector.connect();



            String month = comMonth.getValue();
            String id = tid.getText();

            brent.clear();
            bdepo.clear();
            bwater.clear();
            bgar.clear();
            belec.clear();
            bfee.clear();
            Statement stmt;
            ResultSet rs;
            try {
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT rent_balance,deposit_balance,water_balance,electricity_balance,garbage_balance,fees_balance  FROM balance   WHERE tenant_id =" + id + " AND month ='" + month + "'");

                while (rs.next()) {
                    brent.setText(String.valueOf(rs.getInt(1)));
                    bdepo.setText(String.valueOf(rs.getInt(2)));
                    bwater.setText(String.valueOf(rs.getInt(3)));
                    belec.setText(String.valueOf(rs.getInt(4)));
                    bgar.setText(String.valueOf(rs.getInt(5)));
                    bfee.setText(String.valueOf(rs.getInt(6)));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            conn = MysqlConnector.connect();


            data2 = FXCollections.observableArrayList();

            String monthi = comMonth.getValue();
            String idi = tid.getText();

            Statement stmt1;
            ResultSet rs1;
            try {
                stmt1 = conn.createStatement();
                rs1 = stmt1.executeQuery("SELECT rent_balance  FROM balance   WHERE tenant_id =" + idi + " AND month ='"+monthi+"'");

                while (rs1.next()) {
                    tbal.setText(String.valueOf(rs1.getInt(1)));


                }
            } catch (SQLException e) {

                e.printStackTrace();

            }


        }

    }


    public void handleBalance(ActionEvent event) {


        String bof = tbal.getText();
        int bf = Integer.parseInt(bof);



        if (tpaid.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);


            String s = "Please Enter Payment ";
            alert.setContentText(s);
            alert.show();
        } else {
            String paidstuff = tpaid.getText();

            int paid = Integer.parseInt(paidstuff);
            int newbal = paid - bf;

            tnewbal.setText(String.valueOf(newbal));

        }
    }


    public void handlePay(ActionEvent event) {

        try {
            PreparedStatement preparedStatement, preparedStatement1, preparedStatement2;
            ResultSet resultSet;

            conn = MysqlConnector.connect();


            String id = tid.getText();
            int idno = Integer.parseInt(id);

            String paid = tpaid.getText();
            int paiddepo = Integer.parseInt(paid);

            String balance = tnewbal.getText();
            int bal = Integer.parseInt(balance);



            preparedStatement1 = conn.prepareStatement("UPDATE balance SET rent_balance =? WHERE tenant_id =" + idno + " ");

            preparedStatement1.setInt(1, bal);

            preparedStatement2 = conn.prepareStatement("INSERT INTO rent (tenant_id,paid,month,paid_by,date)" + " VALUES (?,?,?,?,?)");

            String month = comMonth.getValue();
            String paidby = tpaid.getText();

            java.sql.Date getDate = java.sql.Date.valueOf(datePicker.getValue());


            preparedStatement2.setInt(1, idno);
            preparedStatement2.setInt(2, paiddepo);
            preparedStatement2.setString(3, month);
            preparedStatement2.setString(4, paidby);
            preparedStatement2.setDate(5, getDate);


            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();


        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);


        String s = "SAVED ";
        alert.setContentText(s);
        alert.show();
        tbal.clear();
        tpaid.clear();
        tnewbal.clear();





    }

    public void handleBalance1(ActionEvent event) {


        String bof = tbal1.getText();
        int bf = Integer.parseInt(bof);



        if (tpaid1.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);


            String s = "Please Enter Payment ";
            alert.setContentText(s);
            alert.show();
        } else {
            String paidstuff = tpaid1.getText();

            int paid = Integer.parseInt(paidstuff);
            int newbal = paid - bf;

            tnewbal1.setText(String.valueOf(newbal));

        }
    }


    public void handlePay1(ActionEvent event) {

        try {
            PreparedStatement preparedStatement, preparedStatement1, preparedStatement2;
            ResultSet resultSet;

            conn = MysqlConnector.connect();


            String id = tid.getText();
            int idno = Integer.parseInt(id);

            String paid = tpaid1.getText();
            int paiddepo = Integer.parseInt(paid);

            String balance = tnewbal1.getText();
            int bal = Integer.parseInt(balance);



            preparedStatement1 = conn.prepareStatement("UPDATE balance SET deposit_balance =? WHERE tenant_id =" + idno + " ");

            preparedStatement1.setInt(1, bal);

            preparedStatement2 = conn.prepareStatement("INSERT INTO balance (tenant_id,paid,month,paid_by,date)" + " VALUES (?,?,?,?,?)");

            String month = comMonth.getValue();
            String paidby = tpaid1.getText();

            java.sql.Date getDate = java.sql.Date.valueOf(datePicker.getValue());


            preparedStatement2.setInt(1, idno);
            preparedStatement2.setInt(2, paiddepo);
            preparedStatement2.setString(3, month);
            preparedStatement2.setString(4, paidby);
            preparedStatement2.setDate(5, getDate);


            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();


        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);


        String s = "SAVED ";
        alert.setContentText(s);
        alert.show();
        tbal1.clear();
        tpaid1.clear();
        tnewbal1.clear();


    }

    public void handleBalance2(ActionEvent event) {


        String bof = tbal2.getText();
        int bf = Integer.parseInt(bof);



        if (tpaid2.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);


            String s = "Please Enter Payment ";
            alert.setContentText(s);
            alert.show();
        } else {
            String paidstuff = tpaid2.getText();

            int paid = Integer.parseInt(paidstuff);
            int newbal = paid - bf;

            tnewbal2.setText(String.valueOf(newbal));

        }
    }


    public void handlePay2(ActionEvent event) {

        try {
            PreparedStatement preparedStatement, preparedStatement1, preparedStatement2;
            ResultSet resultSet;

            conn = MysqlConnector.connect();


            String id = tid.getText();
            int idno = Integer.parseInt(id);

            String paid = tpaid2.getText();
            int paiddepo = Integer.parseInt(paid);

            String balance = tnewbal2.getText();
            int bal = Integer.parseInt(balance);



            preparedStatement1 = conn.prepareStatement("UPDATE balance SET water_balance =? WHERE tenant_id =" + idno + " ");

            preparedStatement1.setInt(1, bal);

            preparedStatement2 = conn.prepareStatement("INSERT INTO water (tenant_id,paid,month,paid_by,date)" + " VALUES (?,?,?,?,?)");

            String month = comMonth.getValue();
            String paidby = tpaid.getText();

            java.sql.Date getDate = java.sql.Date.valueOf(datePicker.getValue());


            preparedStatement2.setInt(1, idno);
            preparedStatement2.setInt(2, paiddepo);
            preparedStatement2.setString(3, month);
            preparedStatement2.setString(4, paidby);
            preparedStatement2.setDate(5, getDate);


            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();


        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);


        String s = "SAVED ";
        alert.setContentText(s);
        alert.show();
        tbal.clear();
        tpaid.clear();
        tnewbal.clear();





    }
    public void handleBalance3(ActionEvent event) {


        String bof = tbal3.getText();
        int bf = Integer.parseInt(bof);



        if (tpaid3.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);


            String s = "Please Enter Payment ";
            alert.setContentText(s);
            alert.show();
        } else {
            String paidstuff = tpaid3.getText();

            int paid = Integer.parseInt(paidstuff);
            int newbal = paid - bf;

            tnewbal3.setText(String.valueOf(newbal));

        }
    }


    public void handlePay3(ActionEvent event) {

        try {
            PreparedStatement preparedStatement, preparedStatement1, preparedStatement2;
            ResultSet resultSet;

            conn = MysqlConnector.connect();


            String id = tid.getText();
            int idno = Integer.parseInt(id);

            String paid = tpaid3.getText();
            int paiddepo = Integer.parseInt(paid);

            String balance = tnewbal3.getText();
            int bal = Integer.parseInt(balance);



            preparedStatement1 = conn.prepareStatement("UPDATE balance SET electricity_balance =? WHERE tenant_id =" + idno + " ");

            preparedStatement1.setInt(1, bal);

            preparedStatement2 = conn.prepareStatement("INSERT INTO electricity (tenant_id,paid,month,paid_by,date)" + " VALUES (?,?,?,?,?)");

            String month = comMonth.getValue();
            String paidby = tpaid3.getText();

            java.sql.Date getDate = java.sql.Date.valueOf(datePicker.getValue());


            preparedStatement2.setInt(1, idno);
            preparedStatement2.setInt(2, paiddepo);
            preparedStatement2.setString(3, month);
            preparedStatement2.setString(4, paidby);
            preparedStatement2.setDate(5, getDate);


            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();


        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);


        String s = "SAVED ";
        alert.setContentText(s);
        alert.show();
        tbal.clear();
        tpaid.clear();
        tnewbal.clear();





    }
    public void handleBalance4(ActionEvent event) {


        String bof = tbal4.getText();
        int bf = Integer.parseInt(bof);



        if (tpaid4.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);


            String s = "Please Enter Payment ";
            alert.setContentText(s);
            alert.show();
        } else {
            String paidstuff = tpaid4.getText();

            int paid = Integer.parseInt(paidstuff);
            int newbal = paid - bf;

            tnewbal4.setText(String.valueOf(newbal));

        }
    }


    public void handlePay4(ActionEvent event) {

        try {
            PreparedStatement preparedStatement, preparedStatement1, preparedStatement2;
            ResultSet resultSet;

            conn = MysqlConnector.connect();


            String id = tid.getText();
            int idno = Integer.parseInt(id);

            String paid = tpaid4.getText();
            int paiddepo = Integer.parseInt(paid);

            String balance = tnewbal4.getText();
            int bal = Integer.parseInt(balance);




            preparedStatement1 = conn.prepareStatement("UPDATE balance SET garbage_balance =? WHERE tenant_id =" + idno + " ");

            preparedStatement1.setInt(1, bal);

            preparedStatement2 = conn.prepareStatement("INSERT INTO garbage (tenant_id,paid,month,paid_by,date)" + " VALUES (?,?,?,?,?)");

            String month = comMonth.getValue();
            String paidby = tpaid4.getText();

            java.sql.Date getDate = java.sql.Date.valueOf(datePicker.getValue());


            preparedStatement2.setInt(1, idno);
            preparedStatement2.setInt(2, paiddepo);
            preparedStatement2.setString(3, month);
            preparedStatement2.setString(4, paidby);
            preparedStatement2.setDate(5, getDate);


            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();


        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);


        String s = "SAVED ";
        alert.setContentText(s);
        alert.show();
        tbal.clear();
        tpaid.clear();
        tnewbal.clear();





    }

    public void handleBalance5(ActionEvent event) {


        String bof = tbal5.getText();
        int bf = Integer.parseInt(bof);



        if (tpaid5.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);


            String s = "Please Enter Payment ";
            alert.setContentText(s);
            alert.show();
        } else {
            String paidstuff = tpaid5.getText();

            int paid = Integer.parseInt(paidstuff);
            int newbal = paid - bf;

            tnewbal5.setText(String.valueOf(newbal));

        }
    }


    public void handlePay5(ActionEvent event) {

        try {
            PreparedStatement preparedStatement, preparedStatement1, preparedStatement2;
            ResultSet resultSet;

            conn = MysqlConnector.connect();


            String id = tid.getText();
            int idno = Integer.parseInt(id);

            String paid = tpaid5.getText();
            int paiddepo = Integer.parseInt(paid);

            String balance = tnewbal5.getText();
            int bal = Integer.parseInt(balance);




            preparedStatement1 = conn.prepareStatement("UPDATE balance SET fees_balance =? WHERE tenant_id =" + idno + " ");

            preparedStatement1.setInt(1, bal);

            preparedStatement2 = conn.prepareStatement("INSERT INTO fee (tenant_id,paid,month,paid_by,date)" + " VALUES (?,?,?,?,?)");

            String month = comMonth.getValue();
            String paidby = tpaid5.getText();

            java.sql.Date getDate = java.sql.Date.valueOf(datePicker.getValue());


            preparedStatement2.setInt(1, idno);
            preparedStatement2.setInt(2, paiddepo);
            preparedStatement2.setString(3, month);
            preparedStatement2.setString(4, paidby);
            preparedStatement2.setDate(5, getDate);


            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();


        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);


        String s = "SAVED ";
        alert.setContentText(s);
        alert.show();
        tbal.clear();
        tpaid.clear();
        tnewbal.clear();





    }
}
