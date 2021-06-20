package ua.training.model.dao;

import ua.training.model.entity.User;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    public Optional<User> isValid(String login, String password);
    public boolean buyTicket(User user, int exhibitionId, int amount);
    public BigDecimal getUserBalance(User user);
    public BigDecimal balanceReplenishment(BigDecimal amount, User user);
}
