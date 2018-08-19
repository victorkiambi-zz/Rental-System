package sample;

import com.sun.org.apache.regexp.internal.RE;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.util.ResourceBundle;

public class tenantController implements Initializable {

    public Button addBtn;

    @FXML
    public TextField txtid, txtapartment;

    @FXML
    public Label txtnames, txtstatus, txtchildren,labeltype;

    Connection conn;


    @FXML
    public Button savebtn;

    @FXML
    public TabPane tabPane;

    @FXML
    public Tab tab1,tab2;

    public SelectionModel<Tab> selectionModel;


    @FXML
    public TextField txtname, txtapart,txthouse,txtvehicle,txtcontact, txtchild, txtempname, txtempcontact, txtempbox, txtemergname, txtemergid, txtemergcontact, txtemergbox;

    @FXML
    public TextField txtname1,txthouse1,txtvehicle1,txtcontact1, txtstatus1, txtempname1, txtempcontact1, txtempbox1, txtemergname1, txtemergid1, txtemergecontact1, txtemergbox1;


    @FXML
    public ComboBox<String> comboBox;

    @FXML
    public ComboBox<String> combomonth;

    ObservableList<Tenant> data;

    ObservableList<String> stat = FXCollections.observableArrayList("Single", "Married");

    ObservableList<String> work = FXCollections.observableArrayList("Yes", "No");

    ObservableList<String> apart = FXCollections.observableArrayList();

    ObservableList<String> houses = FXCollections.observableArrayList();

    ObservableList<String> monthstuff = FXCollections.observableArrayList("January","February","March","April","May","June","July","August","September","October","November","December");


    @FXML
    TableView tableView;

    @FXML
    private TableColumn<Tenant, Integer> houseno;
    @FXML
    private TableColumn<Tenant, String> names;
    @FXML
    private TableColumn<Tenant, Integer> idno;
    @FXML
    private TableColumn<Tenant, String> housetype;

    @FXML
    private TableColumn<Tenant, Integer> contact;
    @FXML
    private TableColumn<Tenant, Integer> month;

    @FXML
    ComboBox<String> combowork,comboapart,combohouse,combomonth2,comboapart2;



    @Override

    public void initialize(URL location, ResourceBundle resources) {


        comboBox.setItems(stat);
        combowork.setItems(work);
        comboapart.setItems(apart);
        comboapart2.setItems(apart);
        combohouse.setItems(houses);

        combomonth.setItems(monthstuff);
        combomonth2.setItems(monthstuff);




        txtid.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtid.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        txtempcontact.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtempcontact.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        txtchild.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtchild.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        txtcontact.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtcontact.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        txtemergcontact.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtemergecontact1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        txtemergid.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtemergid.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });



        conn = MysqlConnector.connect();

        Statement stmt ;
        ResultSet rs ;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT apartment_name FROM apartment");

            while (rs.next()) {
                apart.add(rs.getString(1));

            }
        } catch (SQLException e) {

        }

        comboapart.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            conn = MysqlConnector.connect();

            Statement stmt1;
            ResultSet rs1;



            try {



                stmt1 = conn.createStatement();
                rs1 = stmt1.executeQuery("SELECT house_no,house_type FROM "+newValue+" WHERE tenant_id is NULL ");

                while (rs1.next()) {
                    houses.add(rs1.getString(1));


                }
            } catch (SQLException e) {

            }



        });

        comboapart2.getSelectionModel().selectedItemProperty().addListener((observable,obs,nev )-> {
            conn = MysqlConnector.connect();

            Statement stmt1;
            ResultSet rs1;

            data = FXCollections.observableArrayList();

            try {
                stmt1 = conn.createStatement();
                rs1 = stmt1.executeQuery("SELECT houseno,id_no,tenant_name,contact,months FROM tenant INNER JOIN "+nev+" ON "+nev+".house_no = tenant.houseno");

                while (rs1.next()) {
                    data.add(new Tenant(rs1.getInt(1), rs1.getInt(2), rs1.getString(3), rs1.getInt(4),rs1.getString(5)));
                }





                houseno.setCellValueFactory(new PropertyValueFactory<>("houses"));

                names.setCellValueFactory(new PropertyValueFactory<>("names"));
                idno.setCellValueFactory(new PropertyValueFactory<>("id number"));
                housetype.setCellValueFactory(new PropertyValueFactory<>("type"));
                contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
                month.setCellValueFactory(new PropertyValueFactory<>("Months"));



                tableView.setItems(data);


            } catch (SQLException e) {

            }



        });
        }


        public void handleHouses(){







        }

    public void handleaddTenant(ActionEvent event) {
        if (event.getSource() == addBtn) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addTenant.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();

                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public void handleSearch(ActionEvent event) {

        conn = MysqlConnector.connect();

        String idno = txtid.getText();

        String apartment = txtapartment.getText();

        if (idno != null && !idno.isEmpty() && !idno.matches("^[a-zA-Z]+$")) {

            try {
                int id = Integer.parseInt(idno);

                PreparedStatement preparedStatement;
                ResultSet resultSet;

                //TODO SELECT FROM USER INPUT
                resultSet = conn.createStatement().executeQuery("SELECT * FROM " + apartment + " WHERE id_no =" + id + "");
                while (resultSet.next()) {
                    txthouse.setText(resultSet.getString(1));
                    txtnames.setText(resultSet.getString(2));
                    txtcontact.setText(resultSet.getString(7));
                    txtstatus.setText(resultSet.getString(4));
                    txtvehicle.setText(resultSet.getString(6));
                    txtchildren.setText(resultSet.getString(5));


                }

                //int house = resultSet.getInt(1);


            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }

    public void handletenant(ActionEvent event) throws ParseException {

        if (event.getSource() == savebtn) {
            conn = MysqlConnector.connect();


            try {


                String tenant_name = txtname.getText();

                String id_no = txtid.getText();
                int id = Integer.parseInt(id_no);

                String Apartmentname = comboapart.getValue();

                String houseno = combohouse.getValue();
                int house = Integer.parseInt(houseno);


                String status = comboBox.getValue();
                String child = txtchild.getText();
                int children = Integer.parseInt(child);

                String con = txtcontact.getText();
                int contact = Integer.parseInt(con);

                String vehicle = txtvehicle.getText();
                String workstuff = combowork.getValue();

                if (workstuff.equals("No")) {

                    txtempname.setVisible(false);
                    txtempcontact.setVisible(false);
                    txtempbox.setVisible(false);
                }
                String employername = txtempname.getText();
                String employeraddress = txtempbox.getText();
                String employercontact = txtempcontact.getText();

                String emergencyname = txtemergname.getText();
                String emergencyid = txtemergid.getText();
                int emergid = Integer.parseInt(emergencyid);
                String emergencyaddress = txtemergbox.getText();

                String emgcontact = txtemergcontact.getText();
                int emergcont = Integer.parseInt(emgcontact);



                String month = combomonth.getValue();


                PreparedStatement preparedStatement, preparedStatement1,preparedStatement2;
                ResultSet resultSet = null;


                preparedStatement = conn.prepareStatement(" INSERT INTO tenant (houseno ,apartment,tenant_name, id_no, status,people,vehicle,contact, work,employer_name,employer_contact,employer_address,emergency_name,emergency_id, emergency_address,emergency_contact,months)" + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");


                preparedStatement.setInt(1, house);
                preparedStatement.setString(2, Apartmentname);
                preparedStatement.setString(3, tenant_name);
                preparedStatement.setInt(4, id);
                preparedStatement.setString(5, status);
                preparedStatement.setInt(6, children);
                preparedStatement.setString(7, vehicle);
                preparedStatement.setInt(8, contact);

                preparedStatement.setString(9, workstuff);
                preparedStatement.setString(10, employername);
                preparedStatement.setString(11, employercontact);
                preparedStatement.setString(12, employeraddress);
                preparedStatement.setString(13, emergencyname);
                preparedStatement.setInt(14, emergid);
                preparedStatement.setString(15, emergencyaddress);
                preparedStatement.setInt(16,emergcont);
                preparedStatement.setString(17,month);


                String apartment = comboapart.getValue();
                preparedStatement1 = conn.prepareStatement("UPDATE "+apartment+" SET tenant_id = "+id+" WHERE house_no ="+house+" " );



                preparedStatement2 = conn.prepareStatement("INSERT INTO balance (tenant_id, housenumber,month, rent_balance, deposit_balance, water_balance, electricity_balance,garbage_balance,fees_balance)\n" +
                        "VALUES (?,?,?,?,?,?,?,?,?)" );


                int rent = 0;
                int depo = 0;
                int water = 0;
                int elec = 0;
                int gar = 0;
                int fee = 0;


                String houses = combohouse.getValue();
                int housei = Integer.parseInt(houses);

                String monthi = combomonth.getValue();


                preparedStatement2.setInt(1, id);
                preparedStatement2.setInt(2, housei);
                preparedStatement2.setString(3,monthi);
                preparedStatement2.setInt(4, rent);
                preparedStatement2.setInt(5, depo);
                preparedStatement2.setInt(6, water);
                preparedStatement2.setInt(7, elec);
                preparedStatement2.setInt(8, gar);
                preparedStatement2.setInt(9, fee);







                preparedStatement.executeUpdate();
                preparedStatement1.executeUpdate();

                preparedStatement2.executeUpdate();




            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setHeaderText("Information Alert");
                String s = "error  ";
                alert.setContentText(s);
                alert.show();
                e.printStackTrace();
            }
        } else {


        }

        tabPane.getTabs();


        selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tab2);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);


        String s = "Tenant added successfully  ";
        alert.setContentText(s);
        alert.show();


        txtname.clear();
        txtid.clear();


        txtchild.clear();

        txtemergname.clear();

        txtemergid.clear();
        txtemergbox.clear();
        txtempname.clear();
        txtempbox.clear();
        txtempcontact.clear();
        comboapart.setValue("");
        combohouse.setValue("");
        combowork.setValue("");
        comboBox.setValue("");
        txtvehicle.clear();
        txtcontact.clear();
        combomonth.setValue("");
        txtemergcontact.clear();


    }

    public void handleShow() {

        if (combowork.getValue().equals("No")) {
            txtempname.setVisible(false);
            txtempcontact.setVisible(false);
            txtempbox.setVisible(false);
        }else
            if (combowork.getValue().equals("Yes")){

                txtempname.setVisible(true);
                txtempcontact.setVisible(true);
                txtempbox.setVisible(true);
            }

    }

    public void handleUpdate(){

    }

    public void handleDelete(){

    }
}
