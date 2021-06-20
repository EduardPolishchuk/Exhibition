package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.Exhibition;
import ua.training.model.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public Optional<User> findById(int id) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findById(id);
        }
    }

    public List<User> findAllUsers() {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findAll();
        }
    }

    public List<User> findExhibitionUsers(Exhibition exhibition) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.getExhibitionUsers(exhibition);
        }
    }

    public Optional<User> isValid(String login, String password) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.isValid(login, password);
        }
    }

    public boolean createUser(User user) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.create(user);
        }
    }

    public boolean updateUser(User user) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.update(user);
        }
    }

    public boolean buyTicket(User user, int exhibitionId, int amount) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.buyTicket(user, exhibitionId, amount);
        }
    }

    public BigDecimal getUserBalance(User user) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.getUserBalance(user);
        }
    }

    public BigDecimal balanceReplenishment(BigDecimal amount, User user) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.balanceReplenishment(amount, user);
        }
    }
}
