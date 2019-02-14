package edu.otaviotarelho.users.service;


import edu.otaviotarelho.users.ObjectMock.UserMock;
import edu.otaviotarelho.users.domain.User;
import edu.otaviotarelho.users.domain.enumerator.UserType;
import edu.otaviotarelho.users.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

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
        User user = new UserMock().withoutLogin().withoutPassword().buildUser();
        when(repository.save(any())).thenThrow(ConstraintViolationException.class);

        assertThrows(ConstraintViolationException.class, () -> userService.insert(user));
    }

    @Test
    void shouldReturnUserIfUsernameOrPasswordIsCorrect(){
        User user = new UserMock().buildUser();
        when(repository.findByUsernameAndPassword(anyString(), anyString())).thenReturn(Optional.ofNullable(user));

        assertEquals(Optional.ofNullable(user), Optional.ofNullable(userService.getUserByUsernameAndPassword(user)));
    }

    @Test
    void shouldThrowAnExceptionIfUserNotFoundById(){
        User user = new UserMock().buildUser();
        when(repository.findById(anyLong())).thenThrow(ObjectNotFoundException.class);

        assertThrows(ObjectNotFoundException.class, () -> userService.getUserById(user.getId()));
    }

    @Test
    void shouldInsertUser(){
        User user = new UserMock().buildUser();
        when(repository.save(any())).thenReturn(user);

        assertEquals(user, userService.insert(user));
    }

    @Test
    void shouldUpdateUser(){
        User user = new UserMock().buildUser();
        when(repository.save(any())).thenReturn(user);

        assertEquals(user, userService.update(user));
    }

    @Test
    void shouldReturnAListOfUserByTypeUser(){
        List<User> users = Arrays.asList(
                new UserMock().withUserType(UserType.USER).buildUser(),
                new UserMock().withUserType(UserType.USER).buildUser(),
                new UserMock().withUserType(UserType.USER).buildUser(),
                new UserMock().withUserType(UserType.USER).buildUser(),
                new UserMock().withUserType(UserType.USER).buildUser()
        );

        when(repository.findAllByType(any())).thenReturn(users);

        assertEquals(users, userService.findAllUsersByType(UserType.USER));
    }

    @Test
    void shouldReturnAListOfUserByTypePersonalTrainer(){
        List<User> users = Arrays.asList(
                new UserMock().withUserType(UserType.PERSONAL_TRAINER).buildUser(),
                new UserMock().withUserType(UserType.PERSONAL_TRAINER).buildUser(),
                new UserMock().withUserType(UserType.PERSONAL_TRAINER).buildUser(),
                new UserMock().withUserType(UserType.PERSONAL_TRAINER).buildUser(),
                new UserMock().withUserType(UserType.PERSONAL_TRAINER).buildUser()
        );

        when(repository.findAllByType(any())).thenReturn(users);

        assertEquals(users, userService.findAllUsersByType(UserType.PERSONAL_TRAINER));
    }

    @Test
    void shouldReturnAListOfUserByTypeDoctor(){
        List<User> users = Arrays.asList(
                new UserMock().withUserType(UserType.DOCTOR).buildUser(),
                new UserMock().withUserType(UserType.DOCTOR).buildUser(),
                new UserMock().withUserType(UserType.DOCTOR).buildUser(),
                new UserMock().withUserType(UserType.DOCTOR).buildUser(),
                new UserMock().withUserType(UserType.DOCTOR).buildUser()
        );

        when(repository.findAllByType(any())).thenReturn(users);

        assertEquals(users, userService.findAllUsersByType(UserType.DOCTOR));
    }

    @Test
    void shouldReturnAListOfUserByTypeAdmin(){
        List<User> users = Arrays.asList(
                new UserMock().withUserType(UserType.ADMIN).buildUser(),
                new UserMock().withUserType(UserType.ADMIN).buildUser(),
                new UserMock().withUserType(UserType.ADMIN).buildUser(),
                new UserMock().withUserType(UserType.ADMIN).buildUser(),
                new UserMock().withUserType(UserType.ADMIN).buildUser()
        );

        when(repository.findAllByType(any())).thenReturn(users);

        assertEquals(users, userService.findAllUsersByType(UserType.ADMIN));
    }

}