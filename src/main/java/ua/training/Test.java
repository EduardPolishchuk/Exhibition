package ua.training;

import ua.training.model.entity.Exhibition;
import ua.training.model.service.ExhibitionService;

import java.sql.*;
import java.util.*;

public class Test {
    private static final String LOGIN_REG = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{0,19}$";
    private static final String EMAIL_REG = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{0,19}$";
    private static final String FIRST_NAME_REG = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{0,19}$";
    private static final String LAST_NAME_REG = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{0,19}$";
    private static final String PASSWORD_REG = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{0,19}$";


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        ExhibitionService exhibitionService = new ExhibitionService();
        List<Exhibition> list = exhibitionService.getFrom(1, 0, 50);
        list.sort(Comparator.comparing(Exhibition::getPrice).reversed());
        for (Exhibition e : list) {
            System.out.println(e);
        }
    }
}
