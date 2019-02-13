package edu.otaviotarelho.users.domain.dto;

import edu.otaviotarelho.users.domain.enumerator.UserType;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private UserType type;

    public Long getId() {
        return id;
    }

    public UserDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public UserDTO setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserType getType() {
        return type;
    }

    public UserDTO setType(UserType type) {
        this.type = type;
        return this;
    }
}
