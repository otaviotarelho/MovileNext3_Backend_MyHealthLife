package edu.otaviotarelho.users.service;


import edu.otaviotarelho.users.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnErrorIfUsernameOrPasswordIsEmpty(){

    }

    @Test
    void shouldReturnUserIfUsernameOrPasswordIsCorrect(){

    }

    @Test
    void shouldThrowAnExceptionIfUserNotFoundById(){

    }

    @Test
    void shouldInsertUser(){

    }

    @Test
    void shouldUpdateUser(){

    }

    @Test
    void shouldReturnAListOfUserByTypeUser(){

    }

    @Test
    void shouldReturnAListOfUserByTypePersonalTrainer(){

    }

    @Test
    void shouldReturnAListOfUserByTypeDoctor(){

    }

    @Test
    void shouldReturnAListOfUserByTypeAdmin(){

    }

}