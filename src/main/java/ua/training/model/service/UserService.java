package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;

import java.util.Optional;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public Optional<User> findById (int id){
        try (UserDao userDao = daoFactory.createUserDao()){
            return userDao.findById(id);
        }
    }

    public boolean isValid(String login, String password){
        try(UserDao userDao = daoFactory.createUserDao()){
            return userDao.isValid(login, password);
        }
    }
}
