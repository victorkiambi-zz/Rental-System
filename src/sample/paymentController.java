package sample;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.omg.CORBA.INTERNAL;


import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;


public class paymentController implements Initializable {

    Connection conn;

    public Stage stage = new Stage();

    @FXML

    public TableView<Apartment> tableView;

    @FXML
    private TableColumn<Apartment, Integer> housecol;
    @FXML
    private TableColumn<Apartment, String> namecol;
    @FXML
    private TableColumn<Apartment, Integer> idcol;
    @FXML
    private TableColumn<Apartment, Integer> rentcol;
    @FXML
    private TableColumn<Apartment, Integer> depocol;
    @FXML
    private TableColumn<Apartment, Integer> watercol;
    @FXML
    private TableColumn<Apartment, Integer> electriccol;
    @FXML
    private TableColumn<Apartment, Integer> garbagecol;
    @FXML

    private TableColumn<Apartment, Integer> feecol;
    @FXML

    private TableColumn<Apartment, Integer> balancecol;


    public VBox vBox3;

    @FXML
    public RadioButton checkdepo, checkdepo1;

    @FXML
    public Label labelid, labelhouse, labelmonth, labelinvoice;

    @FXML
    public TextField txtsearch, txtid, txtrent, txtdepo, txtgarbage, txtwater, txtelectric, txtbalance, txtrentpay, txtidstuff, idget;

    @FXML
    public TextField txtactual, txtpaid, txtnewbal, txtpaidby, txttotal, txtcomm;

    @FXML
    public Label label, labelid1, labelname, labelbuild, labelhouse2, labelrent, labeldepo, labelwater, labelelectric, labelgarbage, labelfees, labelmonth1, labelbalance;

    @FXML
    RadioButton radiopay, radioupdate;

    @FXML
    public TextField txtactual1, txtpaid1, txtnewbal1, txtbalance1, txtactualrent, txtbf, txtpaidrent, txtnewbalrent;

    @FXML
    public TextField txtactual2, txtpaid2, txtnewbal2, txtbalance2;

    @FXML
    public TextField txtactual3, txtpaid3, txtnewbal3, txtbalance3;

    @FXML
    public TextField txtactual4, txtpaid4, txtnewbal4, txtbalance4;
    @FXML
    public TextField txtactual5, txtpaid5, txtnewbal5, txtbalance5;
    @FXML
    public DatePicker datePicker, datePicker1, datePicker2, datePicker3, datePicker4, datePicker5;

    @FXML
    public Button balancebtn, savebtn1, savestuff, printbtn, btnsearch1, printbtn1, totalbtn, commbtn, savebtn, update, upbtn, ansearch;

    @FXML
    public TextField totalrent, deposit, watertxt, electricity, garbagetxt, feestxt, balance, comm;


    @FXML
    public TableView<Apartment> tableView1;

    @FXML
    private TableColumn<Apartment, Integer> houseno;
    @FXML
    private TableColumn<Apartment, String> tenantname;
    @FXML
    private TableColumn<Apartment, Integer> idno;
    @FXML
    private TableColumn<Apartment, Integer> rent;
    @FXML
    private TableColumn<Apartment, Integer> water;
    @FXML
    private TableColumn<Apartment, Integer> electric;
    @FXML
    private TableColumn<Apartment, Integer> garbage;
    @FXML
    private TableColumn<Apartment, Integer> fees;
    @FXML
    private TableColumn<Apartment, String> month;

    @FXML
    public TableView<Apartment> tableView2;

    @FXML
    private TableColumn<Apartment, Integer> hcol;
    @FXML
    private TableColumn<Apartment, String> ncol;
    @FXML
    private TableColumn<Apartment, Integer> pcol;

    @FXML
    private TableColumn<Apartment, String> mcol;

    @FXML

    public ObservableSet<Apartment> observableSet = FXCollections.observableSet();
    @FXML

    public ObservableSet<Apartment> observableSet1 = FXCollections.observableSet();


    private ObservableList<Apartment> data;
    public ObservableList<Apartment> data2;

    public SelectionModel<Tab> selectionModel;

    ObservableList<String> apart = FXCollections.observableArrayList();
    ObservableList<Integer> data3 = FXCollections.observableArrayList();


    ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");


    @FXML
    public ComboBox<String> comboapart, combomonths, comboMonth, combomonths1, comboapart1, compay, comMonth;
    ObservableList<String> pay = FXCollections.observableArrayList("Rent", "Water", "Garbage", "2 Bedroom", "3 Bedroom");

    ObservableList<Apartment> paydata = FXCollections.observableArrayList();

    @FXML
    public TabPane tabPane;
    @FXML
    public Tab tabrent, tabdepo, tabwater, tabelectric, tabgarbage, tabfees, tabview, tabrentupdate;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        comboapart.setItems(apart);
        combomonths.setItems(months);
        comboMonth.setItems(months);
        comboapart1.setItems(apart);
        combomonths1.setItems(months);
        compay.setItems(pay);

        comMonth.setItems(months);


        tabPane.getSelectionModel().select(0);
        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, old, newv) -> {


            if (comboapart.getValue() == null) {
                Alert();
                return;
            }
            if (combomonths.getValue() == null) {
                Alert();
                return;

            } else {

                if (newv == tabrent) {

                    conn = MysqlConnector.connect();


                    data2 = FXCollections.observableArrayList();
                    String apaart = comboapart.getValue();
                    String month = combomonths.getValue();
                    String tid = labelid.getText();

                    Statement stmt;
                    ResultSet rs;
                    try {
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery("SELECT rent,rent_balance  FROM " + apaart + " INNER JOIN balance ON " + apaart + ".house_no = balance.housenumber WHERE " + apaart + ".tenant_id =" + tid + "");

                        while (rs.next()) {
                            txtactual.setText(String.valueOf(rs.getInt(1)));
                            txtbalance.setText(String.valueOf(rs.getInt(2)));

                        }
                    } catch (SQLException e) {

                        e.printStackTrace();

                    }

                }

                if (newv == tabdepo) {

                    conn = MysqlConnector.connect();


                    data2 = FXCollections.observableArrayList();
                    String apaart = comboapart.getValue();
                    String month = combomonths.getValue();
                    String tid = labelid.getText();

                    Statement stmt;
                    ResultSet rs;
                    try {
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery("SELECT deposit,deposit_balance  FROM " + apaart + " INNER JOIN balance ON " + apaart + ".house_no = balance.housenumber WHERE " + apaart + ".tenant_id ='" + tid + "'");

                        while (rs.next()) {
                            txtactual1.setText(String.valueOf(rs.getInt(1)));
                            txtbalance1.setText(String.valueOf(rs.getInt(2)));

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                if (newv == tabwater) {
                    conn = MysqlConnector.connect();


                    data2 = FXCollections.observableArrayList();
                    String apaart = comboapart.getValue();
                    String month = combomonths.getValue();
                    String tid = labelid.getText();

                    Statement stmt;
                    ResultSet rs;
                    try {
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery("SELECT water,water_balance  FROM fees INNER JOIN balance ON fees.houseno = balance.housenumber WHERE balance.tenant_id ='" + tid + "'");
                        while (rs.next()) {
                            txtactual2.setText(String.valueOf(rs.getInt(1)));
                            txtbalance2.setText(String.valueOf(rs.getInt(2)));

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                }

                if (newv == tabelectric) {

                    conn = MysqlConnector.connect();


                    data2 = FXCollections.observableArrayList();
                    String apaart = comboapart.getValue();
                    String month = combomonths.getValue();
                    String tid = labelid.getText();

                    Statement stmt;
                    ResultSet rs;
                    try {
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery("SELECT electricity,electricity_balance  FROM fees INNER JOIN balance ON fees.houseno = balance.housenumber WHERE balance.tenant_id ='" + tid + "'");
                        while (rs.next()) {
                            txtactual3.setText(String.valueOf(rs.getInt(1)));
                            txtbalance3.setText(String.valueOf(rs.getInt(2)));

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }

                if (newv == tabgarbage) {

                    conn = MysqlConnector.connect();


                    data2 = FXCollections.observableArrayList();
                    String apaart = comboapart.getValue();
                    String month = combomonths.getValue();
                    String tid = labelid.getText();

                    Statement stmt;
                    ResultSet rs;
                    try {
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery("SELECT garbage,garbage_balance  FROM fees INNER JOIN balance ON fees.houseno = balance.housenumber WHERE balance.tenant_id ='" + tid + "'");

                        while (rs.next()) {
                            txtactual4.setText(String.valueOf(rs.getInt(1)));
                            txtbalance4.setText(String.valueOf(rs.getInt(2)));

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if (newv == tabfees) {

                    conn = MysqlConnector.connect();


                    data2 = FXCollections.observableArrayList();
                    String apaart = comboapart.getValue();
                    String month = combomonths.getValue();
                    String tid = labelid.getText();

                    Statement stmt;
                    ResultSet rs;
                    try {
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery("SELECT fee,fees_balance  FROM fees INNER JOIN balance ON fees.houseno = balance.housenumber WHERE balance.tenant_id ='" + tid + "'");

                        while (rs.next()) {
                            txtactual5.setText(String.valueOf(rs.getInt(1)));
                            txtbalance5.setText(String.valueOf(rs.getInt(2)));

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (newv == tabview) {

                    String id_no = labelid.getText();
                    int idnumber = Integer.parseInt(id_no);

                    Statement stmt;
                    ResultSet rs;
                    try {
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery("SELECT payment_id, tenant_name,apartment,houseno, rent, deposit, water, garbage, electricity, fee, balance, month, date FROM payment INNER JOIN tenant ON payment.id_number= tenant.id_no WHERE id_number =" + idnumber + "");

                        while (rs.next()) {

                            labelinvoice.setText(String.valueOf(rs.getInt(1)));
                            labelname.setText(rs.getString(2));
                            labelbuild.setText(rs.getString(3));
                            labelhouse2.setText(String.valueOf((rs.getInt(4))));
                            labelrent.setText(String.valueOf(rs.getInt(5)));
                            labeldepo.setText(String.valueOf(rs.getInt(6)));
                            labelwater.setText(String.valueOf(rs.getInt(7)));
                            labelgarbage.setText(String.valueOf(rs.getInt(8)));
                            labelelectric.setText(String.valueOf(rs.getInt(9)));
                            labelfees.setText(String.valueOf(rs.getInt(10)));
                            labelbalance.setText(String.valueOf(rs.getInt(11)));

                            labelmonth1.setText(rs.getString(12));


                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                if (newv == tabrentupdate)

                    conn = MysqlConnector.connect();


                data2 = FXCollections.observableArrayList();
                String apaart = comboapart.getValue();
                String month = combomonths.getValue();
                String tid = labelid.getText();

                Statement stmt;
                ResultSet rs;
                try {
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery("SELECT rent,rent_balance  FROM " + apaart + " INNER JOIN balance ON " + apaart + ".house_no = balance.housenumber WHERE " + apaart + ".tenant_id =" + tid + "");

                    while (rs.next()) {
                        txtactualrent.setText(String.valueOf(rs.getInt(1)));
                        txtbf.setText(String.valueOf(rs.getInt(2)));

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


        conn = MysqlConnector.connect();

        Statement stmt;
        ResultSet rs;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT apartment_name FROM apartment");

            while (rs.next()) {
                apart.add(rs.getString(1));

            }
        } catch (SQLException e) {

            e.printStackTrace();
            e.printStackTrace();

        }

       /* comboapart.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue) -> {

            try {
                data = FXCollections.observableArrayList();
                PreparedStatement preparedStatement;
                ResultSet resultSet;

                //TODO SELECT FROM USER INPUT
                resultSet = conn.createStatement().executeQuery("SELECT house_no,tenant_name,id_no, rent, water,electricity,garbage,fee,months FROM ((tenant INNER JOIN " + newvalue + " ON tenant.id_no =" + newvalue + ".tenant_id) INNER JOIN fees ON " + newvalue + ".house_no = fees.houseno )");

                while (resultSet.next()) {
                    data.add(new Apartment(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5),
                            resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getString(9)));
                }

                houseno.setCellValueFactory(new PropertyValueFactory<>("house_no"));
                tenantname.setCellValueFactory(new PropertyValueFactory<>("tenant_name"));
                idno.setCellValueFactory(new PropertyValueFactory<>("id_no"));
                rent.setCellValueFactory(new PropertyValueFactory<>("rent"));
                water.setCellValueFactory(new PropertyValueFactory<>("water"));
                electric.setCellValueFactory(new PropertyValueFactory<>("electricity"));
                garbage.setCellValueFactory(new PropertyValueFactory<>("garbage"));
                fees.setCellValueFactory(new PropertyValueFactory<>("fees"));
                month.setCellValueFactory(new PropertyValueFactory<>("months"));


                tableView1.setItems(data);



            } catch (SQLException e) {
                e.printStackTrace();
            }
        });*/


        datePicker.setValue(LocalDate.now());

        ToggleGroup group = new ToggleGroup();


        checkdepo.setToggleGroup(group);
        checkdepo1.setToggleGroup(group);
        group.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            if (group.getSelectedToggle() == checkdepo) {

                tabPane.getTabs();


                selectionModel = tabPane.getSelectionModel();
                selectionModel.select(tabdepo);
                tabdepo.setDisable(false);
            }
            if (group.getSelectedToggle() == checkdepo1) {

                try {


                    int depo = 0;

                    PreparedStatement preparedStatement, preparedStatement1;

                    preparedStatement = conn.prepareStatement("UPDATE payment SET deposit =? WHERE id_number =" + idno + " ");


                    preparedStatement.setInt(1, depo);

                    preparedStatement1 = conn.prepareStatement("UPDATE balance SET deposit_balance =? WHERE tenant_id =" + idno + " ");

                    preparedStatement1.setInt(1, depo);


                    preparedStatement.executeUpdate();

                    preparedStatement1.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                tabPane.getTabs();


                selectionModel = tabPane.getSelectionModel();
                selectionModel.select(tabwater);
            }
        });


    }

    public void handleSearch1(ActionEvent event) {

        if (comboapart.getValue() == null) {
            Alert();
            return;
        }
        if (combomonths.getValue() == null) {
            Alert();
            return;

        } else {


            try {
                observableSet.clear();
                String apartment = comboapart.getValue();
                String months = combomonths.getValue();

                PreparedStatement preparedStatement;
                ResultSet resultSet;


                resultSet = conn.createStatement().executeQuery("SELECT houseno,tenant_name,id_no,months FROM tenant WHERE months = '" + months + "'");

                while (resultSet.next()) {
                    observableSet.add(new Apartment(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4)));
                }

                houseno.setCellValueFactory(new PropertyValueFactory<>("house_no"));
                tenantname.setCellValueFactory(new PropertyValueFactory<>("tenant_name"));
                idno.setCellValueFactory(new PropertyValueFactory<>("id_no"));

                month.setCellValueFactory(new PropertyValueFactory<>("months"));


                tableView1.setItems(FXCollections.observableArrayList(observableSet));

                tableView1.setOnMouseClicked((MouseEvent e) -> {
                    if (e.getClickCount() > 1) {

                        Apartment name = tableView1.getSelectionModel().getSelectedItem();

                        String id = Integer.toString(name.getId_no());


                        String tname = name.getTenant_name();

                        labelid.setText(id);


                        String houseno = Integer.toString(name.getHouse_no());
                        labelhouse.setText(houseno);

                        String monthS = name.getMonths();
                        labelmonth.setText(monthS);

                        AtomicInteger atomicInteger = new AtomicInteger(1000);

                        int paymentid = atomicInteger.getAndIncrement();
                        System.out.println(paymentid);


                        conn = MysqlConnector.connect();


                        data2 = FXCollections.observableArrayList();
                        String apaart = comboapart.getValue();
                        String month = combomonths.getValue();
                        String tid = labelid.getText();

                        Statement stmt;
                        ResultSet rs;
                        try {
                            stmt = conn.createStatement();
                            rs = stmt.executeQuery("SELECT rent,rent_balance  FROM " + apaart + " INNER JOIN balance ON " + apaart + ".house_no = balance.housenumber WHERE " + apaart + ".tenant_id =" + tid + "");

                            while (rs.next()) {
                                txtactual.setText(String.valueOf(rs.getInt(1)));
                                txtbalance.setText(String.valueOf(rs.getInt(2)));

                            }
                        } catch (SQLException eV) {

                            eV.printStackTrace();

                        }


                    }


                });


            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }


    public static boolean isNumber(String in) {

        try {
            Integer.parseInt(in);
            return true;
        } catch (Exception E) {
            return false;
        }
    }

    public void handleSearch(ActionEvent event) {
        conn = MysqlConnector.connect();


        if (txtidstuff.getText().trim().isEmpty()) {
            Alert();
            return;
        }
        if (comboapart.getValue() == null) {
            Alert();
            return;
        }
        if (combomonths.getValue() == null) {
            Alert();
            return;

        } else {


            try {

                String idstuff = txtidstuff.getText();


                String apartment = comboapart.getValue();

                String month1 = combomonths.getValue();

                data = FXCollections.observableArrayList();
                PreparedStatement preparedStatement;
                ResultSet resultSet;

                //TODO SELECT FROM USER INPUT
                resultSet = conn.createStatement().executeQuery("SELECT houseno,tenant_name,id_no,months FROM tenant WHERE months = '" + month1 + "' AND id_no = '" + idstuff + "'");

                while (resultSet.next()) {
                    data.add(new Apartment(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4)));
                }

                houseno.setCellValueFactory(new PropertyValueFactory<>("house_no"));
                tenantname.setCellValueFactory(new PropertyValueFactory<>("tenant_name"));
                idno.setCellValueFactory(new PropertyValueFactory<>("id_no"));

                month.setCellValueFactory(new PropertyValueFactory<>("months"));


                tableView1.setItems(data);
                tableView1.setOnMouseClicked((MouseEvent e) -> {
                    if (e.getClickCount() > 1) {

                   /* AtomicInteger payid = new AtomicInteger(1000);

                    int paymentid = payid.getAndIncrement();*/


                        Apartment name = tableView1.getSelectionModel().getSelectedItem();


                    }
                });


            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

    }

    public void handleBalance(ActionEvent event) {


        if (txtpaid.getText().trim().isEmpty()) {
            Alert();
            return;

        } else {

            String actual = txtactual.getText();
            int rent = Integer.parseInt(actual);
            String balance = txtbalance.getText();
            int bal = Integer.parseInt(balance);

            String paidrent = txtpaid.getText();

            int paid = Integer.parseInt(paidrent);

            int temp = paid - rent;

            int newbal = temp + bal;

            txtnewbal.setText(String.valueOf(newbal));

        }
    }


    public void handlePay(ActionEvent event) {

        try {
            PreparedStatement preparedStatement, preparedStatement1;
            ResultSet resultSet;

            conn = MysqlConnector.connect();

            preparedStatement = conn.prepareStatement("INSERT INTO payment (id_number,rent,month,date,paid_by)" + "values (?,?,?,?,?)");


            String id = labelid.getText();
            int idno = Integer.parseInt(id);

            String paid = txtpaid.getText();
            int paidrent = Integer.parseInt(paid);


            String houseno = labelhouse.getText();
            int house = Integer.parseInt(houseno);

            String month = comboMonth.getValue();
            String paidby = txtpaidby.getText();

            java.sql.Date getDate = java.sql.Date.valueOf(datePicker.getValue());


            preparedStatement.setInt(1, idno);
            preparedStatement.setInt(2, paidrent);
            preparedStatement.setString(3, month);
            preparedStatement.setDate(4, getDate);
            preparedStatement.setString(5, paidby);

            preparedStatement1 = conn.prepareStatement("INSERT INTO rent (tenant_id,paid,month,paid_by,date)" + " VALUES (?,?,?,?,?)");


            preparedStatement1.setInt(1, idno);
            preparedStatement1.setInt(2, paidrent);
            preparedStatement1.setString(3, month);
            preparedStatement1.setString(4, paidby);
            preparedStatement1.setDate(5, getDate);

            preparedStatement.executeUpdate();
            preparedStatement1.executeUpdate();

            insertBalance();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);


        String s = "SAVED ";
        alert.setContentText(s);
        alert.show();
    }


    public void insertBalance() {


        try {
            PreparedStatement preparedStatement, preparedStatement1;
            ResultSet resultSet;

            conn = MysqlConnector.connect();


            String id = labelid.getText();
            int idno = Integer.parseInt(id);

            String paid = txtpaid.getText();
            int paidrent = Integer.parseInt(paid);


            String houseno = labelhouse.getText();
            int house = Integer.parseInt(houseno);

            String month = comboMonth.getValue();
            String paidby = txtpaidby.getText();

            java.sql.Date getDate = java.sql.Date.valueOf(datePicker.getValue());


            preparedStatement1 = conn.prepareStatement("UPDATE  balance SET tenant_id =?,housenumber =?,month =?,rent_balance=? WHERE tenant_id = " + idno + "");

            String bal = txtnewbal.getText();
            int balance = Integer.parseInt(bal);


            preparedStatement1.setInt(1, idno);
            preparedStatement1.setInt(2, house);
            preparedStatement1.setString(3, month);
            preparedStatement1.setInt(4, balance);


            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        txtactual.clear();
        txtbalance.clear();
        txtnewbal.clear();
        txtpaidby.clear();
        txtpaid.clear();


    }

    public void handleBalance1(ActionEvent event) {


        String actual = txtactual1.getText();
        int rent = Integer.parseInt(actual);
        String balance = txtbalance1.getText();
        int bal = Integer.parseInt(balance);


        if (txtpaid1.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);


            String s = "Please Enter Payment ";
            alert.setContentText(s);
            alert.show();
        } else {
            String paidrent = txtpaid1.getText();

            int paid = Integer.parseInt(paidrent);
            int temp = paid - rent;

            int newbal = temp + bal;

            txtnewbal1.setText(String.valueOf(newbal));

        }
    }


    public void handlePay1(ActionEvent event) {

        try {
            PreparedStatement preparedStatement, preparedStatement1, preparedStatement2;
            ResultSet resultSet;

            conn = MysqlConnector.connect();


            String id = labelid.getText();
            int idno = Integer.parseInt(id);

            String paid = txtpaid1.getText();
            int paiddepo = Integer.parseInt(paid);

            String balance = txtnewbal1.getText();
            int bal = Integer.parseInt(balance);


            preparedStatement = conn.prepareStatement("UPDATE payment SET deposit =? WHERE id_number =" + idno + " ");


            preparedStatement.setInt(1, paiddepo);

            preparedStatement1 = conn.prepareStatement("UPDATE balance SET deposit_balance =? WHERE tenant_id =" + idno + " ");

            preparedStatement1.setInt(1, bal);

            preparedStatement2 = conn.prepareStatement("INSERT INTO deposit (tenant_id,paid,month,paid_by,date)" + " VALUES (?,?,?,?,?)");

            String month = comboMonth.getValue();
            String paidby = txtpaid.getText();

            java.sql.Date getDate = java.sql.Date.valueOf(datePicker.getValue());


            preparedStatement2.setInt(1, idno);
            preparedStatement2.setInt(2, paiddepo);
            preparedStatement2.setString(3, month);
            preparedStatement2.setString(4, paidby);
            preparedStatement2.setDate(5, getDate);

            preparedStatement.executeUpdate();
            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();


        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);


        String s = "SAVED ";
        alert.setContentText(s);
        alert.show();
        txtactual1.clear();
        txtbalance1.clear();
        txtnewbal1.clear();

        txtpaid1.clear();


        tabPane.getTabs();


        SelectionModel<Tab> selectionModel;

        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tabwater);


    }

    public void handleBalance2(ActionEvent event) {


        String actual = txtactual2.getText();
        int rent = Integer.parseInt(actual);
        String balance = txtbalance2.getText();
        int bal = Integer.parseInt(balance);


        if (txtpaid2.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);


            String s = "Please Enter Payment ";
            alert.setContentText(s);
            alert.show();
        } else {
            String paidrent = txtpaid2.getText();

            int paid = Integer.parseInt(paidrent);

            int temp = paid - rent;

            int newbal = temp + bal;

            txtnewbal2.setText(String.valueOf(newbal));

        }
    }


    public void handlePay2(ActionEvent event) {

        try {
            PreparedStatement preparedStatement, preparedStatement1, preparedStatement2;
            ResultSet resultSet;

            conn = MysqlConnector.connect();


            String id = labelid.getText();
            int idno = Integer.parseInt(id);

            String paid = txtpaid2.getText();
            int paiddepo = Integer.parseInt(paid);

            String balance = txtnewbal2.getText();
            int bal = Integer.parseInt(balance);


            preparedStatement = conn.prepareStatement("UPDATE payment SET water =? WHERE id_number =" + idno + " ");


            preparedStatement.setInt(1, paiddepo);

            preparedStatement1 = conn.prepareStatement("UPDATE balance SET water_balance =? WHERE tenant_id =" + idno + " ");

            preparedStatement1.setInt(1, bal);
            preparedStatement2 = conn.prepareStatement("INSERT INTO water (tenant_id,paid,month,paid_by,date)" + " VALUES (?,?,?,?,?)");

            String month = comboMonth.getValue();
            String paidby = txtpaid.getText();

            java.sql.Date getDate = java.sql.Date.valueOf(datePicker.getValue());


            preparedStatement2.setInt(1, idno);
            preparedStatement2.setInt(2, paiddepo);
            preparedStatement2.setString(3, month);
            preparedStatement2.setString(4, paidby);
            preparedStatement2.setDate(5, getDate);

            preparedStatement.executeUpdate();

            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();


        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);


        String s = "SAVED ";
        alert.setContentText(s);
        alert.show();
        txtactual2.clear();
        txtbalance2.clear();
        txtnewbal2.clear();

        txtpaid2.clear();


        tabPane.getTabs();


        SelectionModel<Tab> selectionModel;

        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tabelectric);


    }

    public void handleBalance3(ActionEvent event) {


        String actual = txtactual3.getText();
        int rent = Integer.parseInt(actual);
        String balance = txtbalance3.getText();
        int bal = Integer.parseInt(balance);


        if (txtpaid3.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);


            String s = "Please Enter Payment ";
            alert.setContentText(s);
            alert.show();
        } else {
            String paidrent = txtpaid3.getText();

            int paid = Integer.parseInt(paidrent);

            int temp = paid - rent;

            int newbal = temp + bal;

            txtnewbal3.setText(String.valueOf(newbal));

        }
    }


    public void handlePay3(ActionEvent event) {

        try {
            PreparedStatement preparedStatement, preparedStatement1, preparedStatement2;
            ResultSet resultSet;

            conn = MysqlConnector.connect();


            String id = labelid.getText();
            int idno = Integer.parseInt(id);

            String paid = txtpaid3.getText();
            int paiddepo = Integer.parseInt(paid);

            String balance = txtnewbal3.getText();
            int bal = Integer.parseInt(balance);


            preparedStatement = conn.prepareStatement("UPDATE payment SET electricity =? WHERE id_number =" + idno + " ");


            preparedStatement.setInt(1, paiddepo);

            preparedStatement1 = conn.prepareStatement("UPDATE balance SET electricity_balance =? WHERE tenant_id =" + idno + " ");

            preparedStatement1.setInt(1, bal);

            preparedStatement2 = conn.prepareStatement("INSERT INTO electricity (tenant_id,paid,month,paid_by,date)" + " VALUES (?,?,?,?,?)");

            String month = comboMonth.getValue();
            String paidby = txtpaid.getText();

            java.sql.Date getDate = java.sql.Date.valueOf(datePicker.getValue());


            preparedStatement2.setInt(1, idno);
            preparedStatement2.setInt(2, paiddepo);
            preparedStatement2.setString(3, month);
            preparedStatement2.setString(4, paidby);
            preparedStatement2.setDate(5, getDate);


            preparedStatement.executeUpdate();

            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();


        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);


        String s = "SAVED ";
        alert.setContentText(s);
        alert.show();
        txtactual3.clear();
        txtbalance3.clear();
        txtnewbal3.clear();

        txtpaid3.clear();


        tabPane.getTabs();


        SelectionModel<Tab> selectionModel;

        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tabgarbage);


    }


    public void handleBalance4(ActionEvent event) {


        String actual = txtactual4.getText();
        int rent = Integer.parseInt(actual);
        String balance = txtbalance4.getText();
        int bal = Integer.parseInt(balance);


        if (txtpaid4.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);


            String s = "Please Enter Payment ";
            alert.setContentText(s);
            alert.show();
        } else {
            String paidrent = txtpaid4.getText();

            int paid = Integer.parseInt(paidrent);

            int total = rent + bal;
            int newbal = paid - total;

            txtnewbal4.setText(String.valueOf(newbal));

        }
    }


    public void handlePay4(ActionEvent event) {

        try {
            PreparedStatement preparedStatement, preparedStatement1, preparedStatement2;
            ResultSet resultSet;

            conn = MysqlConnector.connect();


            String id = labelid.getText();
            int idno = Integer.parseInt(id);

            String paid = txtpaid4.getText();
            int paiddepo = Integer.parseInt(paid);

            String balance = txtnewbal4.getText();
            int bal = Integer.parseInt(balance);


            preparedStatement = conn.prepareStatement("UPDATE payment SET garbage =? WHERE id_number =" + idno + " ");


            preparedStatement.setInt(1, paiddepo);

            preparedStatement1 = conn.prepareStatement("UPDATE balance SET garbage_balance =? WHERE tenant_id =" + idno + " ");

            preparedStatement1.setInt(1, bal);

            preparedStatement2 = conn.prepareStatement("INSERT INTO garbage (tenant_id,paid,month,paid_by,date)" + " VALUES (?,?,?,?,?)");

            String month = comboMonth.getValue();
            String paidby = txtpaid.getText();

            java.sql.Date getDate = java.sql.Date.valueOf(datePicker.getValue());


            preparedStatement2.setInt(1, idno);
            preparedStatement2.setInt(2, paiddepo);
            preparedStatement2.setString(3, month);
            preparedStatement2.setString(4, paidby);
            preparedStatement2.setDate(5, getDate);


            preparedStatement.executeUpdate();

            preparedStatement1.executeUpdate();

            preparedStatement2.executeUpdate();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);


        String s = "SAVED ";
        alert.setContentText(s);
        alert.show();
        txtactual4.clear();
        txtbalance4.clear();
        txtnewbal4.clear();

        txtpaid4.clear();


        tabPane.getTabs();


        SelectionModel<Tab> selectionModel;

        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tabfees);


    }

    public void handleBalance5(ActionEvent event) {


        String actual = txtactual5.getText();
        int rent = Integer.parseInt(actual);
        String balance = txtbalance5.getText();
        int bal = Integer.parseInt(balance);


        if (txtpaid5.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);


            String s = "Please Enter Payment ";
            alert.setContentText(s);
            alert.show();
        } else {
            String paidrent = txtpaid5.getText();

            int paid = Integer.parseInt(paidrent);
            int temp = paid - rent;

            int newbal = temp + bal;
            txtnewbal5.setText(String.valueOf(newbal));

        }
    }


    public void handlePay5(ActionEvent event) {

        try {
            PreparedStatement preparedStatement, preparedStatement1, preparedStatement2;
            ResultSet resultSet;

            conn = MysqlConnector.connect();


            String id = labelid.getText();
            int idno = Integer.parseInt(id);

            String paid = txtpaid5.getText();
            int paiddepo = Integer.parseInt(paid);

            String balance = txtnewbal5.getText();
            int bal = Integer.parseInt(balance);


            preparedStatement = conn.prepareStatement("UPDATE payment SET fee =? WHERE id_number =" + idno + " ");


            preparedStatement.setInt(1, paiddepo);

            preparedStatement1 = conn.prepareStatement("UPDATE balance SET fees_balance =? WHERE tenant_id =" + idno + " ");

            preparedStatement1.setInt(1, bal);

            preparedStatement2 = conn.prepareStatement("INSERT INTO fee (tenant_id,paid,month,paid_by,date)" + " VALUES (?,?,?,?,?)");

            String month = comboMonth.getValue();
            String paidby = txtpaid.getText();

            java.sql.Date getDate = java.sql.Date.valueOf(datePicker.getValue());


            preparedStatement2.setInt(1, idno);
            preparedStatement2.setInt(2, paiddepo);
            preparedStatement2.setString(3, month);
            preparedStatement2.setString(4, paidby);
            preparedStatement2.setDate(5, getDate);

            preparedStatement.executeUpdate();


            preparedStatement.executeUpdate();

            preparedStatement1.executeUpdate();

            preparedStatement2.executeUpdate();

            insertPaybalance();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);


        String s = "SAVED ";
        alert.setContentText(s);
        alert.show();
        txtactual5.clear();
        txtbalance5.clear();
        txtnewbal5.clear();

        txtpaid5.clear();


        tabPane.getTabs();


        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tabview);
    }

    public void insertPaybalance() {

        conn = MysqlConnector.connect();

        Statement stmt;
        ResultSet rs;

        String id = labelid.getText();
        int idno = Integer.parseInt(id);

        try {

            PreparedStatement preparedStatement;
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT SUM(rent_balance + deposit_balance + water_balance + electricity_balance+garbage_balance+fees_balance)  FROM balance WHERE tenant_id =" + idno + "");


            while (rs.next()) {
                int total;
                total = rs.getInt(1);


                preparedStatement = conn.prepareStatement("UPDATE payment SET balance =? WHERE id_number =" + idno + "");

                preparedStatement.setInt(1, total);
                preparedStatement.executeUpdate();

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void handleBalance6(ActionEvent event) {

        String actual = txtactualrent.getText();
        int rent = Integer.parseInt(actual);
        String balance = txtbf.getText();
        int bal = Integer.parseInt(balance);


        if (txtpaid1.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);


            String s = "Please Enter Payment ";
            alert.setContentText(s);
            alert.show();
        } else {
            String paidrent = txtpaid1.getText();

            int paid = Integer.parseInt(paidrent);
            int temp = paid - rent;

            int newbal = temp + bal;

            txtnewbalrent.setText(String.valueOf(newbal));

        }

    }


    public void handleDeposit(ActionEvent event) {


    }

    public void handleSearchPay(ActionEvent event) {

        conn = MysqlConnector.connect();
        String apart1 = comboapart1.getValue();

        String monthpay = combomonths1.getValue();


        if (comboapart1.getSelectionModel().isEmpty() && combomonths1.getSelectionModel().isEmpty()) {


            Alert alert = new Alert(Alert.AlertType.ERROR);


            String s = "Please Select Building and Month ";
            alert.setContentText(s);
            alert.show();

        } else {
            try {

                observableSet1.clear();

                PreparedStatement preparedStatement;
                ResultSet resultSet;

                //TODO SELECT FROM USER INPUT
                resultSet = conn.createStatement().executeQuery("SELECT houseno,tenant_name,id_no, rent,deposit, water,electricity,garbage,fee,balance FROM tenant INNER JOIN payment ON tenant.id_no =payment.id_number WHERE tenant.apartment = '" + apart1 + "' AND payment.month = '" + monthpay + "'");

                while (resultSet.next()) {
                    observableSet1.add(new Apartment(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5),
                            resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10)));
                }

                housecol.setCellValueFactory(new PropertyValueFactory<>("house_no"));
                namecol.setCellValueFactory(new PropertyValueFactory<>("tenant_name"));
                namecol.setCellValueFactory(new PropertyValueFactory<>("tenant_name"));
                idcol.setCellValueFactory(new PropertyValueFactory<>("id_no"));
                rentcol.setCellValueFactory(new PropertyValueFactory<>("rent"));
                depocol.setCellValueFactory(new PropertyValueFactory<>("deposit"));
                watercol.setCellValueFactory(new PropertyValueFactory<>("water"));
                electriccol.setCellValueFactory(new PropertyValueFactory<>("electricity"));
                garbagecol.setCellValueFactory(new PropertyValueFactory<>("garbage"));
                feecol.setCellValueFactory(new PropertyValueFactory<>("fees"));
                balancecol.setCellValueFactory(new PropertyValueFactory<>("balance"));


                observableSet1.addAll(observableSet1);

                tableView.setItems(FXCollections.observableArrayList(observableSet1));


            } catch (SQLException e) {
                e.printStackTrace();
            }

            totalbtn.setVisible(true);

            savestuff.setVisible(true);


        }


    }

    public void handleSavetotal(ActionEvent event) {
        try {


            conn = MysqlConnector.connect();
            PreparedStatement preparedStatement, preparedStatement1, preparedStatement2;
            ResultSet resultSet;

            conn = MysqlConnector.connect();


            String renti = totalrent.getText();
            int rent = Integer.parseInt(renti);

            String deposits = deposit.getText();
            int depo = Integer.parseInt(deposits);

            String waterpay = watertxt.getText();
            int water = Integer.parseInt(waterpay);

            String elecpay = electricity.getText();
            int elec = Integer.parseInt(elecpay);

            String garb = garbagetxt.getText();
            int gar = Integer.parseInt(garb);
            String feesstuff = feestxt.getText();
            int fee = Integer.parseInt(feesstuff);

            String balancestuff = balance.getText();
            int bala = Integer.parseInt(balancestuff);

            String commo = txtcomm.getText();
            int com = Integer.parseInt(commo);
            String apart = comboapart1.getValue();

            preparedStatement = conn.prepareStatement("INSERT INTO total (apartment, totalpaid,deposit_paid,water,electricity,garbage,fee,balance,commission)" + " VALUES (?,?,?,?,?,?,?,?,?)");


            preparedStatement.setString(1, apart);
            preparedStatement.setInt(2, rent);
            preparedStatement.setInt(3, depo);
            preparedStatement.setInt(4, water);
            preparedStatement.setInt(5, elec);
            preparedStatement.setInt(6, gar);
            preparedStatement.setInt(7, fee);
            preparedStatement.setInt(8, bala);
            preparedStatement.setInt(9, com);


            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);

        alert1.setHeaderText("Saved");
        alert1.show();

        totalrent.clear();
        deposit.clear();
        watertxt.clear();
        electricity.clear();
        garbagetxt.clear();
        feestxt.clear();
        balance.clear();
        txtcomm.clear();
    }


    public void handleTotal(ActionEvent event) {

        if (comboapart1.getValue() == null) {
            Alert();
            return;
        }
        if (comboapart1.getValue() == null) {
            Alert();
            return;
        } else {


            try {
                Statement stmt;
                ResultSet resultSet, resultSet1, resultSet2, resultSet3, resultSet4, resultSet5, resultSet6;

                conn = MysqlConnector.connect();

                String monthi = combomonths.getValue();
                String apart = comboapart1.getValue();
                stmt = conn.createStatement();
                resultSet = stmt.executeQuery("SELECT apartment,SUM(rent),SUM(deposit),SUM(water),SUM(electricity),SUM(garbage),SUM(fee),SUM(balance)FROM payment INNER JOIN tenant on payment.month = tenant.months WHERE month =" + monthi + ")");


                while (resultSet.next()) {

                    totalrent.setText(resultSet.getString(1));
                    deposit.setText(resultSet.getString(2));
                    watertxt.setText(resultSet.getString(3));
                    electricity.setText(resultSet.getString(4));
                    garbagetxt.setText(resultSet.getString(5));
                    feestxt.setText(resultSet.getString(6));
                    balance.setText(resultSet.getString(7));


                }


                String totaling = totalrent.getText();
                int total = Integer.parseInt(totaling);


                int comm = total * 10;

                int com = comm / 100;

                txtcomm.setText(String.valueOf(com));


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void handleComm(ActionEvent event) {


    }

    public void handlePrint(ActionEvent event) {


        try {

            Document document = new Document();

            document.setMargins(20, 20, 20, 20);

            int i = 0;
            String filename = Integer.toString(i);
            File f = new File(filename);
            while (f.exists()) {
                i++;
                filename = Integer.toString(i);
                f = new File(filename);
            }
            f.createNewFile();

            String tname = labelname.getText();

            String name = tname + f;
            String id = labelid.getText();
            String monthstuff = combomonths.getValue();

            PdfWriter.getInstance(document, new FileOutputStream("C:/Users/hp/Documents/" + name + ".pdf"));
            document.open();

            Paragraph title = new Paragraph("LAWSAVIC INVESTMENTS LIMITED", new Font(Font.FontFamily.HELVETICA, 12));
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(30f);
            document.add(title);

            Font font = FontFactory.getFont(FontFactory.HELVETICA, 6);

            String paymentid, date, names, houseno, apartment, renti, depositi, wateri, eleci, garbagei, feei, monthi, balancei, paidbyi, datei;

            String sql = " SELECT payment_id,apartment,houseno,tenant_name,rent,deposit, water,electricity,garbage,fee,balance,month,paid_by,date FROM payment INNER JOIN tenant ON payment.id_number =tenant.id_no WHERE payment.id_number = '" + id + "' AND payment.month = '" + monthstuff + "'";

            PreparedStatement preparedStatement;
            ResultSet resultSet;

            preparedStatement = conn.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                paymentid = resultSet.getString(1);
                apartment = resultSet.getString(2);
                houseno = resultSet.getString(3);

                names = resultSet.getString(4);
                renti = resultSet.getString(5);
                depositi = resultSet.getString(6);
                wateri = resultSet.getString(7);
                eleci = resultSet.getString(8);
                garbagei = resultSet.getString(9);
                feei = resultSet.getString(10);
                balancei = resultSet.getString(11);
                monthi = resultSet.getString(12);
                paidbyi = resultSet.getString(13);
                datei = resultSet.getString(14);


                Paragraph dateto = new Paragraph(datei, FontFactory.getFont(FontFactory.HELVETICA, 6));
                dateto.setAlignment(Element.ALIGN_RIGHT);


                PdfPTable table = new PdfPTable(2);
                PdfPCell cell1 = new PdfPCell();
                cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell1.addElement(new Phrase("Invoice No: : ", FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cell1.addElement(new Phrase(paymentid, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cell1.addElement(new Phrase("Names:  ", FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cell1.addElement(new Phrase(names, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                table.addCell(cell1);

                PdfPCell cell2 = new PdfPCell();
                cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell2.addElement(new Phrase("Apartment : ", FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cell2.addElement(new Phrase(apartment, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cell2.addElement(new Phrase("House No : ", FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cell2.addElement(new Phrase(houseno, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                table.addCell(cell2);


                table.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(new Phrase());
                table.addCell(new Phrase());


                document.add(dateto);
                document.add(table);

                table.setSpacingAfter(20f);

                PdfPTable table1 = new PdfPTable(2);

                PdfPCell cell3 = new PdfPCell();
                cell3.addElement(new Phrase(" Rent ", FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cell3.addElement(new Phrase(" Deposit ", FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cell3.addElement(new Phrase(" Water ", FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cell3.addElement(new Phrase(" Electricity ", FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cell3.addElement(new Phrase(" Garbage ", FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cell3.addElement(new Phrase(" Fees ", FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cell3.addElement(new Phrase(" Balance ", FontFactory.getFont(FontFactory.HELVETICA, 6)));
                table1.addCell(cell3);

                PdfPCell cell4 = new PdfPCell();
                cell4.addElement(new Phrase(renti, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cell4.addElement(new Phrase(depositi, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cell4.addElement(new Phrase(wateri, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cell4.addElement(new Phrase(eleci, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cell4.addElement(new Phrase(garbagei, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cell4.addElement(new Phrase(feei, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cell4.addElement(new Phrase(balancei, FontFactory.getFont(FontFactory.HELVETICA, 6)));

                table1.addCell(cell4);

                table1.addCell(new Phrase());
                table1.addCell(new Phrase());

                document.add(table1);

                document.close();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }


    public void handlePrinting(ActionEvent event) {


        conn = MysqlConnector.connect();


        try {

            String apart1 = comboapart1.getValue();

            String monthpay = combomonths1.getValue();

            Document report = new Document();
            report.setMargins(20, 20, 20, 20);

            int i = 0;
            String filename = Integer.toString(i);
            File f = new File(filename);
            while (f.exists()) {
                i++;
                filename = Integer.toString(i);
                f = new File(filename);
            }
            f.createNewFile();

            String name = apart1 + f;

            PdfWriter.getInstance(report, new FileOutputStream("C:/Users/hp/Documents/" + name + ".pdf"));
            report.open();


            //we have four columns in our table


            String houseno, names, id, rent, deposit, water, electricity, garbage, fees, balance;

            String apart2 = comboapart1.getValue();

            String sql = " SELECT houseno,tenant_name,rent, water,electricity,garbage,fee,balance FROM payment INNER JOIN tenant ON payment.id_number =tenant.id_no WHERE tenant.apartment = '" + apart1 + "' AND payment.month = '" + monthpay + "'";


            Font font = FontFactory.getFont(FontFactory.HELVETICA, 6);


            PdfPTable table = new PdfPTable(10);


            PdfPTable nameTable = new PdfPTable(2);
            PdfPCell cell = new PdfPCell(new Paragraph(apart2));

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            nameTable.addCell(cell);


            report.add(nameTable);

            PdfPTable vendTable = new PdfPTable(8);
            PdfPCell cell1 = new PdfPCell(new Paragraph("House No.", font));
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            vendTable.addCell(cell1);
            PdfPCell cell2 = new PdfPCell(new Paragraph(" Names", font));
            cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            vendTable.addCell(cell2);
            PdfPCell cell3 = new PdfPCell(new Paragraph("Rent ", font));
            cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            vendTable.addCell(cell3);
            PdfPCell cell4 = new PdfPCell(new Paragraph("Water", font));
            cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            vendTable.addCell(cell4);
            PdfPCell cell5 = new PdfPCell(new Paragraph("Electricity", font));
            cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
            vendTable.addCell(cell5);
            PdfPCell cell6 = new PdfPCell(new Paragraph("Garbage", font));
            cell6.setBackgroundColor(BaseColor.LIGHT_GRAY);
            vendTable.addCell(cell6);
            PdfPCell cell7 = new PdfPCell(new Paragraph("Fees", font));
            cell7.setBackgroundColor(BaseColor.LIGHT_GRAY);
            vendTable.addCell(cell7);
            PdfPCell cell8 = new PdfPCell(new Paragraph("Balance", font));
            cell8.setBackgroundColor(BaseColor.LIGHT_GRAY);
            vendTable.addCell(cell8);


            PreparedStatement preparedStatement;
            ResultSet resultSet;

            preparedStatement = conn.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                houseno = resultSet.getString(1);
                names = resultSet.getString(2);
                rent = resultSet.getString(3);

                water = resultSet.getString(4);
                electricity = resultSet.getString(5);
                garbage = resultSet.getString(6);
                fees = resultSet.getString(7);
                balance = resultSet.getString(8);

                vendTable.addCell(new Phrase(houseno, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                vendTable.addCell(new Phrase(names, FontFactory.getFont(FontFactory.HELVETICA, 6)));

                vendTable.addCell(new Phrase(rent, FontFactory.getFont(FontFactory.HELVETICA, 6)));

                vendTable.addCell(new Phrase(water, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                vendTable.addCell(new Phrase(electricity, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                vendTable.addCell(new Phrase(garbage, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                vendTable.addCell(new Phrase(fees, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                vendTable.addCell(new Phrase(balance, FontFactory.getFont(FontFactory.HELVETICA, 6)));


                report.add(vendTable);


            }

            PdfPTable totalstuff = new PdfPTable(2);

            PdfPCell cella = new PdfPCell(new Paragraph());
            cella.addElement(new Phrase("Total Rent", FontFactory.getFont(FontFactory.HELVETICA, 6)));
            cella.addElement(new Phrase("Total Deposit", FontFactory.getFont(FontFactory.HELVETICA, 6)));
            cella.addElement(new Phrase("Total Water", FontFactory.getFont(FontFactory.HELVETICA, 6)));
            cella.addElement(new Phrase("Total Electricity", FontFactory.getFont(FontFactory.HELVETICA, 6)));
            cella.addElement(new Phrase("Total Garbage", FontFactory.getFont(FontFactory.HELVETICA, 6)));
            cella.addElement(new Phrase("Total Fees", FontFactory.getFont(FontFactory.HELVETICA, 6)));
            cella.addElement(new Phrase("Total Balance", FontFactory.getFont(FontFactory.HELVETICA, 6)));
            cella.addElement(new Phrase("Total Commission", FontFactory.getFont(FontFactory.HELVETICA, 6)));

            totalstuff.addCell(cella);

            String trent, tdepo, twater, telec, tgar, tfee, tbal, comm;


            PreparedStatement preparedStatement1;
            ResultSet resultSet1;

            String sql1 = " SELECT totalpaid,deposit_paid,water,electricity,garbage,fee,balance,commission FROM total";


            preparedStatement1 = conn.prepareStatement(sql1);

            resultSet1 = preparedStatement1.executeQuery();


            while (resultSet1.next()) {
                trent = resultSet1.getString(1);
                tdepo = resultSet1.getString(2);
                twater = resultSet1.getString(3);

                telec = resultSet1.getString(4);
                tgar = resultSet1.getString(5);
                tfee = resultSet1.getString(6);
                tbal = resultSet1.getString(7);
                comm = resultSet1.getString(8);

                PdfPCell cellb = new PdfPCell(new Paragraph());

                cellb.addElement(new Phrase(trent, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cellb.addElement(new Phrase(tdepo, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cellb.addElement(new Phrase(twater, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cellb.addElement(new Phrase(telec, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cellb.addElement(new Phrase(tgar, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cellb.addElement(new Phrase(tfee, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cellb.addElement(new Phrase(tbal, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                cellb.addElement(new Phrase(comm, FontFactory.getFont(FontFactory.HELVETICA, 6)));
                totalstuff.addCell(cellb);

                totalstuff.addCell(new Phrase());

                report.add(totalstuff);


                report.close();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);

        alert1.setHeaderText("Printing Complete");


        alert1.show();

    }

    public void Alert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText("Error");
        String s = "Please enter Details ";
        alert.setContentText(s);
        alert.show();

    }

    public void handlePayUpdate(ActionEvent event) {


        try {
            conn = MysqlConnector.connect();
            PreparedStatement preparedStatement, preparedStatement1, preparedStatement2;
            ResultSet resultSet;


            String id = labelid.getText();
            int idno = Integer.parseInt(id);

            String paid = txtpaid1.getText();
            int paiddepo = Integer.parseInt(paid);

            String balance = txtnewbal1.getText();
            int bal = Integer.parseInt(balance);

            String month = combomonths.getValue();


            preparedStatement = conn.prepareStatement("UPDATE payment SET rent =? WHERE id_number ='" + idno + "' AND month ='" + month + "' ");


            preparedStatement.setInt(1, bal);

            preparedStatement1 = conn.prepareStatement("INSERT INTO rent (tenant_id,rent_paid,month,paid_by,date)" + " VALUES (?,?,?,?,?)");


            String paidby = txtpaid.getText();

            java.sql.Date getDate = java.sql.Date.valueOf(datePicker.getValue());

            preparedStatement1.setInt(1, idno);
            preparedStatement1.setInt(2, paiddepo);
            preparedStatement1.setString(3, month);
            preparedStatement1.setString(4, paidby);
            preparedStatement1.setDate(5, getDate);


            preparedStatement.executeUpdate();
            preparedStatement1.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void handleNewupdates(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updates.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();


            stage.setTitle("Building Fees");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void handleSearchpayment(ActionEvent event) {

        if (compay.getValue()== null){
            Alert();
            return;
        }if (comMonth.getValue() ==null){
            Alert();
            return;
        }if (idget.getText().trim().isEmpty()){
            Alert();
            return;
        }
        else {

        conn = MysqlConnector.connect();


        String paysth = compay.getValue();

        String monthpay = comMonth.getValue();

        String idi =idget.getText();



            try {

                paydata.clear();

                PreparedStatement preparedStatement;
                ResultSet resultSet;

                //TODO SELECT FROM USER INPUT
                resultSet = conn.createStatement().executeQuery("SELECT houseno,tenant_name,paid,month FROM tenant INNER JOIN "+paysth+" ON tenant.id_no ="+paysth+".tenant_id WHERE tenant.months = '" + monthpay + "' AND tenant.id_no ='"+idi+"'");

                while (resultSet.next()) {
                    paydata.add(new Apartment(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4)));
                }

                hcol.setCellValueFactory(new PropertyValueFactory<>("house_no"));
                ncol.setCellValueFactory(new PropertyValueFactory<>("tenant_name"));
                pcol.setCellValueFactory(new PropertyValueFactory<>("id_no"));

                tableView2.setItems(paydata);


            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }
}