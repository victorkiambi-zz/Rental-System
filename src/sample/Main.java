package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.*;

import static java.util.jar.Pack200.Packer.PASS;

public class Main extends Application {


    Connection connection;

    LoginModel loginModel = new LoginModel();

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        primaryStage.setScene(new Scene(root, 750, 450));


        primaryStage.show();

       /* GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(grid, 300, 275);

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);
        TextField username = new TextField();
        grid.add(username, 1, 1);
        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);
        PasswordField userpass = new PasswordField();
        grid.add(userpass, 1, 2);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);


        btn.setOnAction(event -> {
            connection = MysqlConnector.connect();



            try {

                if (loginModel.isLogin(username.getText(), userpass.getText())) {


                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("primary.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();

                    Stage stage = new Stage();

                    stage.initStyle(StageStyle.DECORATED);
                    stage.setTitle("Login");
                    stage.setScene(new Scene(root1));
                    stage.show();
                    primaryStage.close();


                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setHeaderText("Information Alert");
                    String s = "USERNAME OR PASSWORD IS INCORRECT  ";
                    alert.setContentText(s);
                    alert.show();


                }


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



        });

        primaryStage.setScene(scene);
        primaryStage.show();

*/
    }

    public static void main(String[] args)  {

        LoginModel loginModel = new LoginModel();

        if (loginModel.isDbConnected()) {
            System.out.println("db connected");
        }else {
            System.out.println("not connected");
        }
        launch(args);

    }

}
