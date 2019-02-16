package edu.otaviotarelho.users.ObjectMock;

import edu.otaviotarelho.users.domain.User;
import edu.otaviotarelho.users.domain.enumerator.UserType;

import java.time.LocalTime;

public class UserMock {

    private User user;

    public UserMock() {
        this.user = new edu.otaviotarelho.users.domain.User();
        user.setEmail("teste@test.com.br");
        user.setName("Otavio");
        user.setSurname("Tarelho");
        user.setType(UserType.USER);
        user.setUsername("otaviotarelho");
        user.setPassword("password");
        user.setId(Long.valueOf(1));
        user.setSignupDate(LocalTime.now());
    }

    public UserMock withoutId(){
        this.user.setId(null);
        return this;
    }

    public UserMock withId(Long id){
        this.user.setId(id);
        return this;
    }

    public UserMock withoutEmail(){
        this.user.setEmail("");
        return this;
    }

    public UserMock withoutLogin(){
        this.user.setUsername("");
        return this;
    }

    public UserMock withoutPassword(){
        this.user.setPassword("");
        return this;
    }

    public UserMock withUserType(UserType type){
        this.user.setType(type);
        return this;
    }

    public User buildUser(){
            return user;
        }

}
