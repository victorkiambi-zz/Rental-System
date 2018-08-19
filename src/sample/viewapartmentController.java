package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class viewapartmentController implements Initializable {

    Connection conn;


    public Button updatebtn;

    public Button deletbtn;

    @FXML
    private Button loadbtn;

    public TextField textfieldapart;

    @FXML
    public ListView listView;

    @FXML
    public Label labelhouse;

    @FXML
    public TextField txtwater, txtelec, txtgar, txtfee;


    @FXML
    public TableView<Houseclass> tableView;

    @FXML
    private TableColumn<Houseclass, Integer> houseno;
    @FXML
    private TableColumn<Houseclass, Integer> water;
    @FXML
    private TableColumn<Houseclass, Integer> electricity;
    @FXML
    private TableColumn<Houseclass, Integer> garbage;
    @FXML
    private TableColumn<Houseclass, Integer> fee;

    @FXML
    private TableColumn<Houseclass, Integer> month;


    @FXML
    public ObservableList data2;
    @FXML
    public ObservableList data;

    @FXML
    public
    ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");


    @FXML
    public ComboBox<String> comboBuild, comboMonths;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        comboMonths.setItems(months);


        conn = MysqlConnector.connect();
        try {

            Statement stmt;
            ResultSet rs;

            data2 = FXCollections.observableArrayList();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT apartment_name FROM apartment");

            while (rs.next()) {
                data2.add(rs.getString(1));

            }
        } catch (SQLException e) {

        }

        comboBuild.setItems(data2);


        comboBuild.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {

            try {
                data = FXCollections.observableArrayList();
                PreparedStatement preparedStatement;
                ResultSet resultSet;


                //TODO SELECT FROM USER INPUT
                // resultSet = conn.createStatement().executeQuery(" Select id_no,tenant_name,house_no,deposit,rent,water,garbage,electricity,balance,date FROM payment INNER JOIN  " + apartment + " ON payment.id_number =" + apartment + ".id_no");

                resultSet = conn.createStatement().executeQuery("SELECT houseno, water, electricity, garbage, fee,monthstuff FROM fees WHERE `apartment` ='" + newV + "'");

                while (resultSet.next()) {
                    data.add(new Houseclass(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getString(6)));
                }

                houseno.setCellValueFactory(new PropertyValueFactory<>("houses"));
                water.setCellValueFactory(new PropertyValueFactory<>("water"));
                electricity.setCellValueFactory(new PropertyValueFactory<>("electricity"));
                garbage.setCellValueFactory(new PropertyValueFactory<>("garbage"));
                fee.setCellValueFactory(new PropertyValueFactory<>("fee"));
                month.setCellValueFactory(new PropertyValueFactory<>("month"));


                tableView.setItems(data);

                tableView.setOnMouseClicked((MouseEvent e) -> {
                    if (e.getClickCount() > 1) {

                        Houseclass houseclass = (Houseclass) tableView.getSelectionModel().getSelectedItem();


                        String houses = Integer.toString(houseclass.getHouses());
                        labelhouse.setText(houses);
                        String water = Integer.toString(houseclass.getWater());
                        txtwater.setText(water);

                        String electricity = Integer.toString(houseclass.getElectricity());
                        txtelec.setText(electricity);
                        String garbage = Integer.toString(houseclass.getGarbage());
                        txtgar.setText(garbage);
                        String fees = Integer.toString(houseclass.getFee());
                        txtfee.setText(fees);


                    }

                });


            } catch (SQLException e) {
                e.printStackTrace();
            }


        });

        comboMonths.getSelectionModel().selectedItemProperty().addListener((observable, oldv, newv) -> {

            try {


                data = FXCollections.observableArrayList();
                PreparedStatement preparedStatement;
                ResultSet resultSet;


                String apart = comboBuild.getValue();


                //TODO SELECT FROM USER INPUT
                // resultSet = conn.createStatement().executeQuery(" Select id_no,tenant_name,house_no,deposit,rent,water,garbage,electricity,balance,date FROM payment INNER JOIN  " + apartment + " ON payment.id_number =" + apartment + ".id_no");

                resultSet = conn.createStatement().executeQuery("SELECT houseno, water, electricity, garbage, fee, monthstuff FROM fees WHERE `apartment` ='" + apart + "' AND monthstuff ='" + newv + "'");

                while (resultSet.next()) {
                    data.add(new Houseclass(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getString(6)));
                }

                houseno.setCellValueFactory(new PropertyValueFactory<>("houses"));
                water.setCellValueFactory(new PropertyValueFactory<>("water"));
                electricity.setCellValueFactory(new PropertyValueFactory<>("electricity"));
                garbage.setCellValueFactory(new PropertyValueFactory<>("garbage"));
                fee.setCellValueFactory(new PropertyValueFactory<>("fee"));
                month.setCellValueFactory(new PropertyValueFactory<>("month"));


                tableView.setItems(data);

                tableView.setOnMouseClicked((MouseEvent e) -> {
                    if (e.getClickCount() > 1) {

                        Houseclass houseclass = (Houseclass) tableView.getSelectionModel().getSelectedItem();


                        String houses = Integer.toString(houseclass.getHouses());
                        labelhouse.setText(houses);
                        String water = Integer.toString(houseclass.getWater());
                        txtwater.setText(water);
                        String electricity = Integer.toString(houseclass.getElectricity());
                        txtelec.setText(electricity);
                        String garbage = Integer.toString(houseclass.getGarbage());
                        txtgar.setText(garbage);
                        String fees = Integer.toString(houseclass.getFee());
                        txtfee.setText(fees);


                    }

                });

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


    }

    public void handleUpdate(ActionEvent event) {

        conn = MysqlConnector.connect();


        String housenum = labelhouse.getText();
        String apart = comboBuild.getValue();


        try {

            String water = txtwater.getText();
            int waterpay = Integer.parseInt(water);


            String electricity = txtelec.getText();
            int elec = Integer.parseInt(electricity);

            String garbage = txtgar.getText();
            int gar = Integer.parseInt(garbage);

            String fees = txtfee.getText();
            int fee = Integer.parseInt(fees);

            PreparedStatement preparedStatement, preparedStatement1;
            ResultSet resultSet;

            preparedStatement = conn.prepareStatement(" UPDATE  fees SET water =?,electricity =?, garbage =?,fee =? WHERE houseno =" + housenum);


            preparedStatement.setInt(1, waterpay);
            preparedStatement.setInt(2, elec);
            preparedStatement.setInt(3, gar);
            preparedStatement.setInt(4, fee);


            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        txtwater.clear();
        txtelec.clear();
        txtgar.clear();
        txtfee.clear();
        labelhouse.setText("");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);


        String s = "Updated Successfully";
        alert.setContentText(s);
        alert.show();

        data.clear();
        try {

            String newV = comboBuild.getValue();
            data = FXCollections.observableArrayList();
            PreparedStatement preparedStatement;
            ResultSet resultSet;


            //TODO SELECT FROM USER INPUT
            // resultSet = conn.createStatement().executeQuery(" Select id_no,tenant_name,house_no,deposit,rent,water,garbage,electricity,balance,date FROM payment INNER JOIN  " + apartment + " ON payment.id_number =" + apartment + ".id_no");

            resultSet = conn.createStatement().executeQuery("SELECT houseno, water, electricity, garbage, fee,monthstuff FROM fees WHERE `apartment` ='" + newV + "'");

            while (resultSet.next()) {
                data.add(new Houseclass(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5),resultSet.getString(6)));
            }

            houseno.setCellValueFactory(new PropertyValueFactory<>("houses"));
            water.setCellValueFactory(new PropertyValueFactory<>("water"));
            electricity.setCellValueFactory(new PropertyValueFactory<>("electricity"));
            garbage.setCellValueFactory(new PropertyValueFactory<>("garbage"));
            fee.setCellValueFactory(new PropertyValueFactory<>("fee"));
            month.setCellValueFactory(new PropertyValueFactory<>("month"));

            tableView.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}

