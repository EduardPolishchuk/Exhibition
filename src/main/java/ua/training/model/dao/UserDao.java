package ua.training.model.dao;

import ua.training.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    public Optional<User> isValid(String login, String password);

}
