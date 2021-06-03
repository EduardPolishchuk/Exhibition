package ua.training;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ExpositionDao;
import ua.training.model.entity.Exhibition;
import ua.training.model.service.UserService;

import java.sql.*;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserService userService = new UserService();
        System.out.println(userService.findById(1));


    }

    public static synchronized boolean dbCheck(String login, String pass) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con =
                DriverManager.
                        getConnection("jdbc:" +
                                        "mysql:" +
                                        "//localhost:3306/" +
                                        "mydb",
                                "root",
                                "root");

        Statement query = con.createStatement();
        ResultSet rs = query.executeQuery("SELECT * FROM user where login='" + login + "'");
        if (rs.next() && rs.getString("password").equals(pass)) {
            return true;
        }


        return false;
    }

    public static synchronized String getRole(String login) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con =
                DriverManager.
                        getConnection("jdbc:" +
                                        "mysql:" +
                                        "//localhost:3306/" +
                                        "mydb",
                                "root",
                                "root");

        Statement query = con.createStatement();
        ResultSet rs = query.executeQuery("select role.name FROM role inner join user on user.role_id = role.id where user.login ='" + login + "'");
        if (rs.next()) {
            return rs.getString("name").toLowerCase();
        }


        return null;
    }


}
