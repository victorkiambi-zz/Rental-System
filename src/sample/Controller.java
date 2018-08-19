package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller  implements Initializable {


    public LoginModel loginmodel = new LoginModel();

    public Button loginBtn;
    public TextField username;
    public PasswordField userpass;
    public ImageView imageView1;

    Connection connection;

   public Stage stage = new Stage();


    public void handleLogin(ActionEvent event) throws SQLException {



        if (loginmodel.isLogin(username.getText(), userpass.getText())) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("primary.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();

                stage.initStyle(StageStyle.DECORATED);
                stage.setTitle("Login");
                stage.setScene(new Scene(root1));
                stage.show();

            } catch (Exception e) {
                System.out.print(e);

            }
            finally {
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }




        }    else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setHeaderText("Information Alert");
                    String s = "USERNAME OR PASSWORD IS INCORRECT  ";
                    alert.setContentText(s);
                    alert.show();



        }




            }




    @Override
    public void initialize(URL location, ResourceBundle resources) {


        if (loginmodel.isDbConnected()) {
            
            try{
            connection = MysqlConnector.connect();

                Statement preparedStatement;

                preparedStatement= connection.createStatement();
            String [] queries = {
                    "CREATE TABLE IF NOT EXISTS login  (username char(20) UNIQUE,password char(20) )",
            " INSERT IGNORE INTO login (username ,password)" + "values ('admin','login')",
           "CREATE TABLE IF NOT EXISTS payment  (payment_id int (10) PRIMARY KEY AUTO_INCREMENT, id_number int(15),rent int(6),deposit int (6),water int(6),electricity int(6),garbage int(6),fee int (6),balance int (10),month char (20), date date,paid_by char (20) )",
                    " INSERT IGNORE INTO payment (payment_id)" + "values ('10000')",
                    "CREATE TABLE IF NOT EXISTS apartment (apartment_name char (10),location char(20), owner char (20),contact int(10),caretaker char (20))",

          "CREATE TABLE IF NOT EXISTS tenant (houseno int (10),apartment char (20), tenant_name char (20), id_no int (10), status char (10),people int (10),vehicle char (10),contact int (10), work char (10),employer_name char (10),employer_contact varchar (10),employer_address char (20),emergency_name char (20),emergency_id int (10), emergency_address char(10),emergency_contact int (10), months char (15))",
                    "CREATE TABLE IF NOT EXISTS rent  (payment_id int(10) PRIMARY KEY AUTO_INCREMENT NOT NULL,tenant_id char(10),paid int (10),month char(20),paid_by char (20),date date )",
                    " INSERT IGNORE INTO rent (payment_id)" + "values ('10000')",
                    "CREATE TABLE IF NOT EXISTS deposit  (payment_id int(10) PRIMARY KEY AUTO_INCREMENT,tenant_id char(10),paid int (10),month char(20),paid_by char (20),date date )",
                    " INSERT IGNORE INTO deposit (payment_id)" + "values ('10000')",
                    "CREATE TABLE IF NOT EXISTS water  (payment_id int(10) PRIMARY KEY AUTO_INCREMENT,tenant_id char(10),paid int (10),month char(20),paid_by char (20),date date )",
                    " INSERT IGNORE INTO water (payment_id)" + "values ('10000')",
                    "CREATE TABLE IF NOT EXISTS electricity  (payment_id int(10) PRIMARY KEY AUTO_INCREMENT,tenant_id char(10),paid int (10),month char(20),paid_by char (20),date date )",
                    " INSERT IGNORE INTO electricity (payment_id)" + "values ('10000')",
                    "CREATE TABLE IF NOT EXISTS garbage  (payment_id int(10) PRIMARY KEY AUTO_INCREMENT,tenant_id char(10),paid int (10),month char(20),paid_by char (20),date date )",
                    " INSERT IGNORE INTO garbage (payment_id)" + "values ('10000')",
                    "CREATE TABLE IF NOT EXISTS fee  (payment_id int(10) PRIMARY KEY AUTO_INCREMENT,tenant_id char(10),paid int (10),month char(20),paid_by char (20),date date )",
                    " INSERT IGNORE INTO fee (payment_id)" + "values ('10000')",
           "CREATE TABLE IF NOT EXISTS balance (tenant_id int (10),housenumber int (10),month char (20),rent_balance int (10) NOT NULL,deposit_balance int (10) NOT NULL, water_balance int (10) NOT NULL,electricity_balance int (10),garbage_balance int (10) NOT NULL,fees_balance int (10) NOT NULL)",
                    "CREATE TABLE IF NOT EXISTS tempbalance (tenant_id int (10),housenumber int (10),month char (20),rent_balance int (10) NOT NULL,deposit_balance int (10) NOT NULL, water_balance int (10) NOT NULL,electricity_balance int (10),garbage_balance int (10) NOT NULL,fees_balance int (10) NOT NULL)",
                    "CREATE TABLE IF NOT EXISTS tempapart ( house_no varchar(5),house_type char(10),rent int (10),deposit int(10),tenant_id int (10),month char (12))",

                    "CREATE TABLE IF NOT EXISTS total (apartment char (10), totalpaid int (10), deposit_paid int (10), water int (10), electricity int (10),garbage int (10),fee int (10), balance int (10),commission int (10))",

           "CREATE TABLE IF NOT EXISTS temptenant (houseno int (10),apartment char (20), tenant_name char (20), id_no int (10), status char (10),people int (10),vehicle char (10),contact int (10), work char (10),employer_name char (10),employer_contact varchar (10),employer_address char (20),emergency_name char (20),emergency_id int (10), emergency_address char(10),emergency_contact int (10), months char (15))",

          "CREATE TABLE IF NOT EXISTS tempfees  ( houseno varchar(5),apartment char (10),water int (10), electricity int (10),garbage int (10),fee int (10), monthstuff char (20))"};




              

            for (String query : queries) {

                preparedStatement.addBatch(query);
            }
            preparedStatement.executeBatch();
            preparedStatement.close();
            connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }



            System.out.println("connection established");



        } else {


            System.out.println("is not connected");
        }
    }
}



