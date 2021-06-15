package ua.training;

import ua.training.model.entity.User;
import ua.training.model.service.ExhibitionService;
import ua.training.model.service.UserService;

import java.sql.*;

public class Test {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        ExhibitionService exhibitionService = new ExhibitionService();
        UserService userService = new UserService();
        User user = userService.findById(9).get();
        System.out.println(userService.buyTicket(user,3,3));
    }
}
