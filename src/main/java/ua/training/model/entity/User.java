package ua.training.model.entity;

import java.math.BigDecimal;
import java.util.Map;

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

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public static class UserBuilder {
       private User newUser;

        public UserBuilder() {
            newUser = new User();
        }

        public UserBuilder id(int id){
            newUser.id = id;
            return this;
        }

        public UserBuilder login(String login){
            newUser.login = login;
            return this;
        }

        public UserBuilder email(String email){
            newUser.email = email;
            return this;
        }

        public UserBuilder password(String password){
            newUser.password = password;
            return this;
        }

        public UserBuilder balance(BigDecimal balance){
            newUser.balance = balance;
            return this;
        }

        public UserBuilder exhibitionsTickets(Map<Exhibition, Integer> exhibitionsTickets){
            newUser.exhibitionsTickets = exhibitionsTickets;
            return this;
        }

        public UserBuilder firstName(String firstName){
            newUser.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName){
            newUser.lastName = lastName;
            return this;
        }

        public UserBuilder role(ROLE role){
            newUser.role = role;
            return this;
        }

        public User build(){
            return newUser;
        }
    }

    public enum ROLE {
        USER, ADMIN, UNKNOWN
    }
}
