package ua.training;

import ua.training.model.entity.Exhibition;
import ua.training.model.entity.User;
import ua.training.model.entity.enums.Hall;
import ua.training.model.service.ExhibitionService;
import ua.training.model.service.UserService;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ExhibitionService exhibitionService = new ExhibitionService();
        UserService userService = new UserService();
//        Optional<User> user = userService.findById(1);
//        System.out.println(user.get());
//        System.out.println(userService.isValid("wer","1"));
        List<Exhibition> list = exhibitionService.getFrom(2,3);
        for (Exhibition e:list             ) {
            System.out.println(e);
        }
//
//        if (userService.isValid("wer", "1").isPresent()) {
//            System.out.println(userService.isValid("wer", "1").get());
//        }
//        LocalDate localDate = LocalDate.of(2021, 4, 30);
//        Set<Hall> halls = new HashSet<>();
//        halls.add(Hall.BLUE);
//        halls.add(Hall.RED);
//        halls.add(Hall.GREEN);
//        Exhibition exhibition = Exhibition.builder()
//                .imageUrl("https://artelagunaprize.com/wp-content/uploads/2021/03/a4.jpg")
//                .description("desrc").price(120).date(localDate)
//                .theme("Leo").build();
//        exhibition.setHalls(halls);
//        exhibitionService.create(exhibition);
//        Optional<Map<Exhibition, Integer>> osp = exhibitionService.getUserExhibitions(userService.isValid("wer", "1").get());
//        Map<Exhibition, Integer> map = osp.get();
//        for (Exhibition e : map.keySet()) {
//            System.out.println(e + " Ticket_count:" + map.get(e));
//
//        }


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
