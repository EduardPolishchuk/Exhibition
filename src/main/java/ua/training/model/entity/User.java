package ua.training.model.entity;

import lombok.Getter;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class User {
    private int id;
    private String login;
    private String password;
    private List<Exposition> expositions;
    private ROLE role;

    public enum ROLE {
        USER, ADMIN, UNKNOWN
    }
}
