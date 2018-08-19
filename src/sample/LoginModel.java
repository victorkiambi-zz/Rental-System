package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {


    Connection conection;


    public LoginModel () {
        conection = MysqlConnector.connect();
        if (conection == null) {

            System.out.println("connection not successful");
            System.exit(1);}
    }

    public boolean isDbConnected() {
        try {
            return !conection.isClosed();
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public boolean isLogin (String user, String pass) throws SQLException {

        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;
        String query = "select * from login where username =? and password =?";

        try {
            preparedStatement = conection.prepareStatement(query);
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,pass);


            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
            else{
                return  false;
            }


        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        finally {
            preparedStatement.close();
            resultSet.close();
        }


    }


    public static void createTable(String apartment){

        Connection connection = null;



    }
}
