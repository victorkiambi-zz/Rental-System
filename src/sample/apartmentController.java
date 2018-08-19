package sample;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Notifications;


import javax.management.Notification;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class apartmentController implements Initializable {

    public Stage stage = new Stage();

    Connection conn;

    @FXML
    public TextField txtname, txtno, txtlocate, txtowner, txtcontact, txtcaretaker,txtno1,txttype,txtrent,txtwater,txtelec,txtgar,txtfees,txtdeposit;

    @FXML
    public TextField txthouses1,txtrent1,txtdeposit1,txtwater1,txtelec1,txtgar1,txtfees1,txtnewrent,txtnewdepo,txtrentnew,txtdeponew;
    public Button savebtn,addBtn,updatebtn,nextbtn,addhouse,addhouses;

    public Label labelapart, labelname,labelhouse,labelidno;
    public TableView tableView1;


    public Button apartmentAdd;

    public Button deletbtn, importbtn, savebtn1;


    public TextField textfieldapart;

    @FXML
    public ComboBox<String> comboBox,comboBox1,comboBox2;


    @FXML
    public TabPane tabPane;

    @FXML
    public Tab tab2,tab3;

    public SelectionModel<Tab> selectionModel;

    @FXML
    private TableColumn<Houseclass, Integer> houseno;
    @FXML
    private TableColumn<Houseclass, String> type;
    @FXML
    private TableColumn<Houseclass, Integer> rent;
    @FXML
    private TableColumn<Houseclass, Integer> deposit;

    @FXML
    private TableColumn<Houseclass, String> monthcol;
    @FXML
    private TableColumn<Houseclass, Integer> electricity;
    @FXML
    private TableColumn<Houseclass, Integer> garbage;
    @FXML
    private TableColumn<Houseclass, Integer> fees;



    @FXML
    public TableView tableView;

    @FXML
    private TableColumn<Houseclass, Integer> housecol;
    @FXML
    private TableColumn<Houseclass, Integer> typecol;
    @FXML
    private TableColumn<Houseclass, Integer> rentcol;
    @FXML
    private TableColumn<Houseclass, Integer> depocol;
    @FXML
    private TableColumn<Houseclass, Integer> feescol;

    @FXML
    public VBox vBox,vBox1;
    private ObservableList<Houseclass> data;

    @FXML
    public ComboBox<String> comboBuild,comboMonth,comboMonth1,compay,comMonth,comboBuild2;

    @FXML
    ListView<String> listView;

    @FXML
    ObservableList<String> data2 = FXCollections.observableArrayList();

    @FXML
    ObservableList<String> datalist ;

    ObservableSet<String> observableSet = FXCollections.observableSet();

    ObservableList<String> months = FXCollections.observableArrayList("January","February","March","April","May","June","July","August","September","October","November","December");


    private ObservableList<String> housetype = FXCollections.observableArrayList("Single","Bedsitter","1 Bedroom","2 Bedroom","3 Bedroom");


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        comboMonth.setItems(months);




        comboBox2.setItems(housetype);
        comboBuild.setItems(data2);
        comboMonth.setItems(months);
        comboMonth1.setItems(months);
        comMonth.setItems(months);

        comboBuild2.setItems(data2);





        txtcontact.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtcontact.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        txthouses1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txthouses1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        txtwater1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtwater.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        txtelec1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtelec.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        txtgar1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtgar.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {



            if (newValue == tab2) {



                conn = MysqlConnector.connect();

                Statement stmt;
                ResultSet rs;
                try {
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery("SELECT apartment_name FROM apartment");

                    while (rs.next()) {
                        observableSet.add(rs.getString(1));
                    }

                    observableSet.addAll(observableSet);


                    listView.setItems(FXCollections.observableArrayList(observableSet));
                } catch (SQLException e) {
                    e.printStackTrace();
                }



                    listView.getSelectionModel().selectedItemProperty().addListener((obser, oldVal, newVal) -> {


                        try {


                            data = FXCollections.observableArrayList();
                            PreparedStatement preparedStatement;
                            ResultSet resultSet;


                            //TODO SELECT FROM USER INPUT
                            // resultSet = conn.createStatement().executeQuery(" Select id_no,tenant_name,house_no,deposit,rent,water,garbage,electricity,balance,date FROM payment INNER JOIN  " + apartment + " ON payment.id_number =" + apartment + ".id_no");

                            resultSet = conn.createStatement().executeQuery("Select * FROM " + newVal );

                            if (resultSet.next()) {
                                do {


                                        data.add(new Houseclass(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getString(5)));


                                    houseno.setCellValueFactory(new PropertyValueFactory<>("houses"));
                                    type.setCellValueFactory(new PropertyValueFactory<>("housetype"));
                                    rent.setCellValueFactory(new PropertyValueFactory<>("rent"));
                                    deposit.setCellValueFactory(new PropertyValueFactory<>("Deposit"));

                                    // successfully in. do the right things.

                                } while (resultSet.next());
                            } else {

                                Alert alert = new Alert(Alert.AlertType.ERROR);

                                alert.setHeaderText("Error");
                                String s = "No houses Created ";
                                alert.setContentText(s);
                                alert.show();
                            }




                            labelapart.setText(newVal);


                            tableView1.setItems(data);
                            tableView1.setOnMouseClicked((MouseEvent e) -> {
                                if (e.getClickCount() > 1) {

                                    Houseclass houseclass = (Houseclass) tableView1.getSelectionModel().getSelectedItem();


                                    String housenumber = Integer.toString(houseclass.getHouses());
                                    txthouses1.setText(housenumber);


                                    labelhouse.setText(housenumber);


                                    String rentpay = Integer.toString(houseclass.getRent());
                                    txtrent1.setText(rentpay);
                                    String depo = Integer.toString(houseclass.getDeposit());
                                    txtdeposit1.setText(depo);

                                }

                            });

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    });

                }

            if (newValue == tab3) {


            }

        });


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




        comMonth.getSelectionModel().selectedItemProperty().addListener((observable,old, news) ->{

            if (comboBuild2.getValue()==null)
            {
                Alert();
                return;
            }else {

                try {
                    String apart = comboBuild2.getValue();

                    data = FXCollections.observableArrayList();
                    PreparedStatement preparedStatement;
                    ResultSet resultSet;


                    //TODO SELECT FROM USER INPUT
                    // resultSet = conn.createStatement().executeQuery(" Select id_no,tenant_name,house_no,deposit,rent,water,garbage,electricity,balance,date FROM payment INNER JOIN  " + apartment + " ON payment.id_number =" + apartment + ".id_no");

                    resultSet = conn.createStatement().executeQuery("Select * FROM " + apart + " WHERE month ='" + news + "'");

                    while (resultSet.next()) {
                        data.add(new Houseclass(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getString(5)));
                    }

                    houseno.setCellValueFactory(new PropertyValueFactory<>("houses"));
                    type.setCellValueFactory(new PropertyValueFactory<>("housetype"));
                    rent.setCellValueFactory(new PropertyValueFactory<>("rent"));
                    deposit.setCellValueFactory(new PropertyValueFactory<>("rent"));




                    labelapart.setText(news);


                    tableView1.setItems(data);
                    tableView1.setOnMouseClicked((MouseEvent e) -> {
                        if (e.getClickCount() > 1) {

                            Houseclass houseclass = (Houseclass) tableView1.getSelectionModel().getSelectedItem();


                            String housenumber = Integer.toString(houseclass.getHouses());
                            txthouses1.setText(housenumber);


                            labelhouse.setText(housenumber);


                            String rentpay = Integer.toString(houseclass.getRent());
                            txtrent1.setText(rentpay);
                            String depo = Integer.toString(houseclass.getDeposit());
                            txtdeposit1.setText(depo);

                        }

                    });

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

    });


}





    public void handleNext(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewapartment.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();


            stage.setTitle("Building Fees");
            stage.setScene(new Scene(root1));
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public void handleUpdate(ActionEvent event) {

            if (txthouses1.getText().trim().isEmpty()) {
                Alert();
                return;
            }
            if (txtrent1.getText().trim().isEmpty()) {
                Alert();
                return;
            } if (txtdeposit1.getText().trim().isEmpty()) {
                Alert();
                return;

            }else {

                conn = MysqlConnector.connect();

                String apart = labelapart.getText();

                String housenum = labelhouse.getText();


                try {
                    String hs = txthouses1.getText();

                    String housetype = comboBox2.getValue();

                    String rentpay = txtrent1.getText();
                    int rent = Integer.parseInt(rentpay);

                    String depo = txtdeposit1.getText();
                    int deposit = Integer.parseInt(depo);

                    PreparedStatement preparedStatement, preparedStatement1;
                    ResultSet resultSet;

                    preparedStatement = conn.prepareStatement(" UPDATE  rental." + apart + " SET house_no = ?,house_type =?,rent =?, deposit =? WHERE house_no =" + housenum);


                    preparedStatement.setString(1, hs);
                    preparedStatement.setString(2, housetype);
                    preparedStatement.setInt(3, rent);
                    preparedStatement.setInt(4, deposit);

                    preparedStatement1 = conn.prepareStatement("UPDATE feeS SET houseno = " + hs + " WHERE houseno =" + housenum + " ");

                    preparedStatement.setString(1, hs);


                    preparedStatement.executeUpdate();
                    preparedStatement1.executeUpdate();


                } catch (SQLException e) {
                    e.printStackTrace();
                }

                txthouses1.clear();
                txtrent1.clear();
                txtdeposit1.clear();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);


                String s = "Updated Successfully";
                alert.setContentText(s);
                alert.show();

                data.clear();
                try {
                    data = FXCollections.observableArrayList();
                    PreparedStatement preparedStatement;
                    ResultSet resultSet;


                    //TODO SELECT FROM USER INPUT
                    // resultSet = conn.createStatement().executeQuery(" Select id_no,tenant_name,house_no,deposit,rent,water,garbage,electricity,balance,date FROM payment INNER JOIN  " + apartment + " ON payment.id_number =" + apartment + ".id_no");

                    resultSet = conn.createStatement().executeQuery("Select * FROM " + apart);

                    while (resultSet.next()) {
                        data.add(new Houseclass(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(3), resultSet.getInt(4)));
                    }

                    houseno.setCellValueFactory(new PropertyValueFactory<>("houses"));
                    type.setCellValueFactory(new PropertyValueFactory<>("housetype"));
                    rent.setCellValueFactory(new PropertyValueFactory<>("rent"));
                    deposit.setCellValueFactory(new PropertyValueFactory<>("rent"));


                    tableView1.setItems(data);


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }

    public void handleNew(ActionEvent event){


        if (txtrentnew.getText().trim().isEmpty()) {
            Alert();
            return;
        } if (txtdeponew.getText().trim().isEmpty()) {
            Alert();
            return;

        }else {

            conn = MysqlConnector.connect();

            String apart = comboMonth1.getValue();

            String housenum = labelidno.getText();


            try {
                String hs = txthouses1.getText();

                String monthnew = comboMonth1.getValue();
                String rentpay = txtrentnew.getText();
                int rent = Integer.parseInt(rentpay);

                String depo = txtdeponew.getText();
                int deposit = Integer.parseInt(depo);

                PreparedStatement preparedStatement, preparedStatement1;
                ResultSet resultSet;

                preparedStatement = conn.prepareStatement(" UPDATE  rental." + apart + " SET rent =?, deposit =? WHERE house_no ='" + housenum+"' AND month='"+monthnew+"'");



                preparedStatement.setInt(1, rent);
                preparedStatement.setInt(2, deposit);





                preparedStatement.executeUpdate();



            } catch (SQLException e) {
                e.printStackTrace();
            }

            txtdeponew.clear();
            txtrentnew.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);


            String s = "Updated Successfully";
            alert.setContentText(s);
            alert.show();

            data.clear();
            try {
                data = FXCollections.observableArrayList();
                PreparedStatement preparedStatement;
                ResultSet resultSet;


                String monthnew = comboMonth1.getValue();
                //TODO SELECT FROM USER INPUT
                // resultSet = conn.createStatement().executeQuery(" Select id_no,tenant_name,house_no,deposit,rent,water,garbage,electricity,balance,date FROM payment INNER JOIN  " + apartment + " ON payment.id_number =" + apartment + ".id_no");

                resultSet = conn.createStatement().executeQuery("Select * FROM " + apart +"WHERE month = "+monthnew+"");

                while (resultSet.next()) {
                    data.add(new Houseclass(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(3), resultSet.getInt(4)));
                }

                housecol.setCellValueFactory(new PropertyValueFactory<>("houses"));
                typecol.setCellValueFactory(new PropertyValueFactory<>("housetype"));
                rentcol.setCellValueFactory(new PropertyValueFactory<>("rent"));
                depocol.setCellValueFactory(new PropertyValueFactory<>("rent"));


                tableView.setItems(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }



        public void handleaddApartment(ActionEvent event) throws SQLException {

            if (txtname.getText().trim().isEmpty()) {
                Alert();
                return;
            }if (txtlocate.getText().trim().isEmpty()) {
                Alert();
                return;
            }
            if (txtowner.getText().trim().isEmpty()) {
                Alert();
                return;
            } if (txtcontact.getText().trim().isEmpty()) {
                Alert();
                return;
            }
            if (txtcaretaker.getText().trim().isEmpty()) {
                Alert();
                return;
            }else {

                try {

                    conn = MysqlConnector.connect();
                    String Apartmentname = txtname.getText();

                    String location = txtlocate.getText();

                    String owned = txtowner.getText();


                    String con = txtcontact.getText();
                    int contact = Integer.parseInt(con);

                    String caretaker = txtcaretaker.getText();


                    PreparedStatement preparedStatement, preparedStatement1;
                    ResultSet resultSet = null;


                    preparedStatement = conn.prepareStatement(" INSERT INTO apartment (apartment_name , location, owner,contact, caretaker)" + "values (?,?,?,?,?)");


                    preparedStatement.setString(1, Apartmentname);

                    preparedStatement.setString(2, location);
                    preparedStatement.setString(3, owned);
                    preparedStatement.setInt(4, contact);
                    preparedStatement.setString(5, caretaker);


                    preparedStatement.execute();


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }




        createTable();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setHeaderText("Information Alert");
            String s = "Building added Succesfully  ";
            alert.setContentText(s);
            alert.show();


            String Apartmentname = txtname.getText();
            labelname.setText(Apartmentname);


            txtname.clear();


            txtlocate.clear();

            txtcaretaker.clear();

            txtcontact.clear();

            txtowner.clear();





    }


    public void handleAdd(ActionEvent event){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("house.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            stage.initStyle(StageStyle.DECORATED);

            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            System.out.print(e);

        }
    }

    public void handledeleteApartment(ActionEvent event) {

        if (event.getSource() == deletbtn) {
            conn = MysqlConnector.connect();

            try {

                String delapart = textfieldapart.getText();

                PreparedStatement preparedStatement;
                ResultSet resultSet = null;

                preparedStatement = conn.prepareStatement("DROP TABLE koora");


                preparedStatement.executeUpdate();



                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setHeaderText("Information Alert");
                String s = "Apartment Deleted";
                alert.setContentText(s);
                alert.show();

            } catch (SQLException e) {
                System.out.println(e);
            } finally {

            }
        }


        textfieldapart.clear();


    }

   /* public void handleloaddata(ActionEvent event) {
        conn = MysqlConnector.connect();

        String apartment = textfieldapart.getText();

        if (apartment != null && !apartment.isEmpty()&& apartment.matches("[a-zA-Z]+") ) {

            try {
                data = FXCollections.observableArrayList();
                PreparedStatement preparedStatement;
                ResultSet resultSet;

                //TODO SELECT FROM USER INPUT
                // resultSet = conn.createStatement().executeQuery(" Select id_no,tenant_name,house_no,deposit,rent,water,garbage,electricity,balance,date FROM payment INNER JOIN  " + apartment + " ON payment.id_number =" + apartment + ".id_no");

                resultSet = conn.createStatement().executeQuery("Select * FROM "+apartment);

                while (resultSet.next()) {
                    data.add(new Houseclass(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5),
                            resultSet.getInt(6), resultSet.getInt(7),resultSet.getInt(8)));
                }

                houseno.setCellValueFactory(new PropertyValueFactory<>("houses"));
                type.setCellValueFactory(new PropertyValueFactory<>("housetype"));
                rent.setCellValueFactory(new PropertyValueFactory<>("rent"));
                deposit.setCellValueFactory(new PropertyValueFactory<>("rent"));
                water.setCellValueFactory(new PropertyValueFactory<>("water"));
                electricity.setCellValueFactory(new PropertyValueFactory<>("electricity"));
                garbage.setCellValueFactory(new PropertyValueFactory<>("garbage"));
                fees.setCellValueFactory(new PropertyValueFactory<>("fee"));

                tableView1.setItems(data);




              /*  while (resultSet.next()) {
                    data.add(new Apartment(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5),
                            resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9),resultSet.getString(10)));
                }

                houseno.setCellValueFactory(new PropertyValueFactory<>("house_no"));
                tenantname.setCellValueFactory(new PropertyValueFactory<>("tenant_name"));
                idno.setCellValueFactory(new PropertyValueFactory<>("id_no"));
                rentpaid.setCellValueFactory(new PropertyValueFactory<>("rent_paid"));
                rentpayable.setCellValueFactory(new PropertyValueFactory<>("rent_payable"));
                water.setCellValueFactory(new PropertyValueFactory<>("water"));
                garbage.setCellValueFactory(new PropertyValueFactory<>("garbage"));
                electricity.setCellValueFactory(new PropertyValueFactory<>("electricity"));
                balance.setCellValueFactory(new PropertyValueFactory<>("balance"));


                tableView1.setItems(data);*/


        /*    } catch (SQLException e) {
                System.out.println(e);
            }


        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            String s = "Enter Valid Apartment Name ";
            alert.setContentText(s);
            alert.show();

        }


    }

    /*public void handleNext(ActionEvent event) throws Exception {

        if (event.getSource() == nextbtn) {
            createTable();

            tabPane.getTabs();


            selectionModel = tabPane.getSelectionModel();
           selectionModel.select(tab3);

        }
    }

*/




    private void createTable() throws SQLException {

        String apartment = txtname.getText();

        try {

            PreparedStatement preparedStatement, preparedStatement1;
            Statement stmt;
            ResultSet resultSet = null;
            conn = MysqlConnector.connect();



          /*  DatabaseMetaData dbm = conn.getMetaData();
            ResultSet rs = dbm.getTables(null, null, apartment, null);
            if (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);


                String s = "Building Exists ";
                alert.setContentText(s);
                alert.show();

            } else {

            }*/


            String sql  = "CREATE TABLE "+apartment+" ( house_no varchar(5),house_type char(10),rent int (10),deposit int(10),tenant_id int (10),month char (12))";




            // String sql2 = "ALTER TABLE" +apartment+"  (ADD , ADD COLUMN  ADD  rent int (10),ADD  ADD  ADD ADD  ADD  ) ";
            preparedStatement = conn.prepareStatement(sql );
            preparedStatement1 = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Fees  ( houseno varchar(5),apartment char (10),water int (10), electricity int (10),garbage int (10),fee int (10), monthstuff char (20))");

            preparedStatement.executeUpdate();
            preparedStatement1.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
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
                String apartment = labelname.getText();
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

            vBox1.setVisible(true);

            tabPane.getTabs();


            selectionModel = tabPane.getSelectionModel();
            selectionModel.select(tab2);
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


                    String apartment = labelname.getText();
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

    public void handleImport(ActionEvent event) {


        if (comboBuild.getValue() == null) {
            Alert();
            return;
        }
        if (comboMonth.getValue() == null) {
            Alert();
            return;

        } else {


            conn = MysqlConnector.connect();

            String build = comboBuild.getValue();
            String month = comboMonth.getValue();
            String month1 = comboMonth1.getValue();

            try {

                ResultSet resultSet;
                Statement preparedStatement;

                preparedStatement= conn.createStatement();
                String [] queries = {
                        "INSERT INTO temptenant  SELECT * FROM tenant WHERE apartment ='" + build + "' AND months ='" + month + "' ",
                        "UPDATE temptenant SET months ='" + month1 + "'",
                        "INSERT INTO tenant  SELECT * FROM temptenant ",
                        "DROP TABLE temptenant",
                        "INSERT INTO tempbalance  SELECT * FROM balance WHERE month ='" + month + "'  ",
                        "UPDATE tempbalance SET month ='" + month1 + "'",
                        "INSERT INTO balance  SELECT * FROM tempbalance ",
                        "DROP TABLE tempbalance",

                        "INSERT INTO tempapart  SELECT * FROM "+build+" WHERE month ='" + month + "'",
                        "UPDATE tempapart SET month ='" + month1 + "'",
                        "INSERT INTO "+build+"  SELECT * FROM tempapart ",
                        "DROP TABLE tempapart"
                };

                for (String query : queries) {

                    preparedStatement.addBatch(query);
                }
                preparedStatement.executeBatch();
                preparedStatement.close();


            } catch (SQLException e) {
                e.printStackTrace();
            }


            try {

                String newmonth = comboMonth1.getValue();
                String newapart = comboBuild.getValue();

                data = FXCollections.observableArrayList();
                PreparedStatement preparedStatement;
                ResultSet resultSet;


                //TODO SELECT FROM USER INPUT
                // resultSet = conn.createStatement().executeQuery(" Select id_no,tenant_name,house_no,deposit,rent,water,garbage,electricity,balance,date FROM payment INNER JOIN  " + apartment + " ON payment.id_number =" + apartment + ".id_no");

                resultSet = conn.createStatement().executeQuery("Select house_no,house_type,rent,deposit,month FROM " +newapart +" WHERE month = '" + newmonth + "'");

                while (resultSet.next()) {
                    data.add(new Houseclass(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5)));
                }

                housecol.setCellValueFactory(new PropertyValueFactory<>("houses"));
                typecol.setCellValueFactory(new PropertyValueFactory<>("housetype"));
                rentcol.setCellValueFactory(new PropertyValueFactory<>("rent"));
                depocol.setCellValueFactory(new PropertyValueFactory<>("deposit"));


                tableView.setItems(data);
                tableView.setOnMouseClicked((MouseEvent e) -> {
                    if (e.getClickCount() > 1) {

                        Houseclass houseclass = (Houseclass) tableView.getSelectionModel().getSelectedItem();


                        String housenumber = Integer.toString(houseclass.getHouses());
                        labelidno.setText(housenumber);
                        String rentpay = Integer.toString(houseclass.getRent());
                        txtnewrent.setText(rentpay);
                        String depo = Integer.toString(houseclass.getDeposit());
                        txtnewdepo.setText(depo);

                    }

                });


                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setHeaderText("Information Alert");
                String s = "Imported successfully ";
                alert.setContentText(s);
                alert.show();

                savebtn1.setVisible(true);



            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
        public void handleNewfees(ActionEvent event){

            if (comboBuild.getValue()==null) {
                Alert();
                return;
            }
            if (comboMonth.getValue()==null) {
                Alert();
                return;
            }if (txtwater1.getText().trim().isEmpty()) {
                Alert();
                return;
            }
            if (txtelec1.getText().trim().isEmpty()) {
                Alert();
                return;
            }if (txtgar1.getText().trim().isEmpty()) {
                Alert();
                return;
            }
            if (txtfees1.getText().trim().isEmpty()) {
                Alert();
                return;
            }
            else {


                conn = MysqlConnector.connect();

                String build = comboBuild.getValue();
                String month = comboMonth.getValue();
                String month1 = comboMonth1.getValue();

                String water = txtwater1.getText();
                int waterpay = Integer.parseInt(water);

                String electric = txtelec1.getText();
                int elec = Integer.parseInt(electric);
                String garb = txtgar1.getText();
                int gar = Integer.parseInt(garb);
                String fees = txtfees1.getText();
                int fee = Integer.parseInt(fees);


                try {

                    PreparedStatement preparedStatement, preparedStatement2, preparedStatement3,preparedStatement4;
                    ResultSet resultSet;

                    preparedStatement = conn.prepareStatement("INSERT INTO tempfees  SELECT * FROM fees WHERE apartment ='" + build + "' AND monthstuff ='" + month + "' ");

                    preparedStatement2 = conn.prepareStatement("UPDATE tempfees SET water=?,electricity=?,garbage=?,fee =?, monthstuff =?");

                    preparedStatement2.setInt(1,waterpay);
                    preparedStatement2.setInt(2,elec);
                    preparedStatement2.setInt(3,gar);
                    preparedStatement2.setInt(4,fee);
                    preparedStatement2.setString(5,month1);


                    preparedStatement3 = conn.prepareStatement("INSERT INTO fees  SELECT * FROM tempfees");

                    preparedStatement4 = conn.prepareStatement("DROP TABLE tempfees");


                    preparedStatement.executeUpdate();
                    preparedStatement2.executeUpdate();
                    preparedStatement3.executeUpdate();
                    preparedStatement4.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                txtwater.clear();
                txtfees.clear();
                txtgar.clear();
                txtelec.clear();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setHeaderText("Information Alert");
                String s = "Imported successfully ";
                alert.setContentText(s);
                alert.show();

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewapartment.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();


                    stage.setTitle("Building Fees");
                    stage.setScene(new Scene(root1));
                    stage.show();


                } catch (IOException e) {
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
