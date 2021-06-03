package ua.training.model.entity;

import lombok.Getter;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

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
    private List<Exhibition> exhibitions;
    private ROLE role;
    private BigDecimal balance;

    public enum ROLE {
        USER, ADMIN, UNKNOWN
    }
}
