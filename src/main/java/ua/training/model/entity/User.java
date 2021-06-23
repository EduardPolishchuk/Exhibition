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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<Exhibition, Integer> getExhibitionsTickets() {
        return exhibitionsTickets;
    }

    public void setExhibitionsTickets(Map<Exhibition, Integer> exhibitionsTickets) {
        this.exhibitionsTickets = exhibitionsTickets;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public enum ROLE {
        USER, ADMIN, UNKNOWN
    }
}
