package ua.training.model.entity;

import lombok.Getter;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@ToString()
@Builder
public class User {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Map<Exhibition, Integer> exhibitionsTickets;
    private ROLE role;
    private BigDecimal balance;

    public enum ROLE {
        USER, ADMIN, UNKNOWN
    }
}
