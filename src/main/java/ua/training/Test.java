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
    private static final String LOGIN_REG = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{0,19}$";
    private static final String EMAIL_REG = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{0,19}$";
    private static final String FIRST_NAME_REG = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{0,19}$";
    private static final String LAST_NAME_REG = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{0,19}$";
    private static final String PASSWORD_REG = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{0,19}$";



    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Map<String, String> map = new HashMap<>();
        String result;
        String login = "login";
        String email = "email";
        String firstName = "firstName";
        String lastName = "lastName";
        String password = "password";
        map.put(login, LOGIN_REG);
        map.put(email, EMAIL_REG);
        map.put(firstName, FIRST_NAME_REG);
        map.put(lastName, LAST_NAME_REG);
        map.put(password, PASSWORD_REG);
        for (String str : map.keySet()) {
            System.out.println(str.matches(map.get(str)));
            System.out.println(map.get(str));
            System.out.println(str);
        }
    }
}
