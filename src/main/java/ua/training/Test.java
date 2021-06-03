package ua.training;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ExpositionDao;
import ua.training.model.entity.Exposition;
import ua.training.model.entity.User;
import ua.training.model.entity.enums.Hall;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        DaoFactory daoFactory = DaoFactory.getInstance();
//        UserDao userDao = daoFactory.createUserDao();
//        System.out.println(userDao.findById(1));
        ExpositionDao expositionDao = daoFactory.createExpositionDao();
//        System.out.println(expositionDao.delete(22));

        for (Exposition e : expositionDao.findAll()) {
            System.out.println(e);
        }
//        LocalDate calendar = LocalDate.of(2020, 2, 26);
//        Set<Hall> halls = new HashSet<>();
//        halls.add(Hall.BLUE);
//        halls.add(Hall.GREEN);
//        Exposition ex = Exposition.builder()
//                .id(1)
//                .theme("Grass")
//                .date(calendar)
//                .description("graaaaaaas")
//                .halls(halls)
//                .imageUrl("/url/url")
//                .price(200)
//                .max(200)
//                .build();
//        System.out.println(expositionDao.update(ex));

//        for (Exposition ex : expositionDao.findAll()) {
//            System.out.println(ex);
//        }
//        System.out.println(expositionDao.findAll());
//        Set<Hall> halls = new HashSet<>();
//        halls.add(Hall.BLUE);
//        halls.add(Hall.RED);
//        LocalDate calendar = LocalDate.of(2020, 2, 26);
////        Exposition exposition = new Exposition("Rocks", 120, halls, calendar);
////        System.out.println(expositionDao.create(exposition));
//
//        Exposition exp = Exposition.builder()
//                .id(1).current(2).date(calendar).build();
//        System.out.println(exp.getMax());
//        exp.setHalls(halls);
//        System.out.println(exp.getMax());
//        exp.setHalls(halls);
//        System.out.println(exp.getMax());
//
//        User user = User.builder()
//                .id(2)
//                .build();


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
