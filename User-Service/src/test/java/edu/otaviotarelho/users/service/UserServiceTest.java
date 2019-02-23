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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
        List<User> users = getUsersList(UserType.USER);

        Page<User> page = new PageImpl<>(users);

        PageRequest pageRequest = PageRequest.of(0, 24, Sort.Direction.ASC, "name");

        when(repository.findAllByType(UserType.USER, pageRequest)).thenReturn(page);

        assertEquals(page, userService.findAllUsersByType(UserType.USER, pageRequest));
    }

    @Test
    void shouldReturnAListOfUserByTypePersonalTrainer(){
        List<User> users = getUsersList(UserType.PERSONAL_TRAINER);

        Page<User> page = new PageImpl<>(users);

        PageRequest pageRequest = PageRequest.of(0, 24, Sort.Direction.ASC, "name");

        when(repository.findAllByType(UserType.PERSONAL_TRAINER, pageRequest)).thenReturn(page);

        assertEquals(page, userService.findAllUsersByType(UserType.PERSONAL_TRAINER, pageRequest));
    }

    @Test
    void shouldReturnAListOfUserByTypeDoctor(){
        List<User> users = getUsersList(UserType.DOCTOR);

        Page<User> page = new PageImpl<>(users);

        PageRequest pageRequest = PageRequest.of(0, 24, Sort.Direction.ASC, "name");

        when(repository.findAllByType(UserType.DOCTOR, pageRequest)).thenReturn(page);

        assertEquals(page, userService.findAllUsersByType(UserType.DOCTOR, pageRequest));
    }

    @Test
    void shouldReturnAListOfUserByTypeAdmin(){
        List<User> users = getUsersList(UserType.ADMIN);

        Page<User> page = new PageImpl<>(users);

        PageRequest pageRequest = PageRequest.of(0, 24, Sort.Direction.ASC, "name");

        when(repository.findAllByType(UserType.ADMIN, pageRequest)).thenReturn(page);

        assertEquals(page, userService.findAllUsersByType(UserType.ADMIN, pageRequest));
    }

    private List<User> getUsersList(UserType type) {
        User user = new UserMock().withUserType(type).buildUser();
        return Arrays.asList(user, user, user, user,  user);
    }

}