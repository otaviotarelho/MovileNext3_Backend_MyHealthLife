package edu.otaviotarelho.users.domain.dto;

import java.io.Serializable;
import java.util.List;

public class RelationshipDTO implements Serializable {

    private UserDTO user;
    private List<UserDTO> usersRelated;

    public RelationshipDTO() {
    }

    public UserDTO getUser() {
        return user;
    }

    public RelationshipDTO setUser(UserDTO user) {
        this.user = user;
        return this;
    }

    public List<UserDTO> getUsersRelated() {
        return usersRelated;
    }

    public RelationshipDTO setUsersRelated(List<UserDTO> usersRelated) {
        this.usersRelated = usersRelated;
        return this;
    }
}
