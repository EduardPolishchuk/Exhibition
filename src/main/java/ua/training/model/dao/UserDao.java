package ua.training.model.dao;

import ua.training.model.entity.User;

public interface UserDao extends GenericDao<User> {

    public boolean isValid(String login, String password);

}
