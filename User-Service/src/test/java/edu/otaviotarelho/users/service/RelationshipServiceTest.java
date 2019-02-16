package edu.otaviotarelho.users.service;

import edu.otaviotarelho.users.ObjectMock.UserMock;
import edu.otaviotarelho.users.controller.exception.OperationNotSupportedByThisUserException;
import edu.otaviotarelho.users.domain.Relationships;
import edu.otaviotarelho.users.domain.User;
import edu.otaviotarelho.users.domain.enumerator.UserType;
import edu.otaviotarelho.users.repository.RelationshipRepository;
import edu.otaviotarelho.users.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Matchers.any;

class RelationshipServiceTest {

    @Mock
    private RelationshipRepository repository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RelationshipService service;


    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldCreateRelationshipWithUserAndProfessional() {
        User userOne = new UserMock().withUserType(UserType.USER).buildUser();
        User userTwo = new UserMock().withId(Long.valueOf(2)).withUserType(UserType.PERSONAL_TRAINER).buildUser();

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.ofNullable(userOne), Optional.ofNullable(userTwo));

        Relationships relationships = new Relationships().setUser(userOne.getId()).setProfessional(userTwo.getId());

        Mockito.when(repository.save(any())).thenReturn(relationships);

        Assertions.assertDoesNotThrow(() -> service.create(userOne.getId(), userTwo.getId()));
     }

     @Test
     void shouldThrowExceptionIfUserOneIsNotFound(){
         User userOne = null;
         User userTwo = new UserMock().withId(Long.valueOf(2)).withUserType(UserType.PERSONAL_TRAINER).buildUser();

         Mockito.when(userRepository.findById(any())).thenReturn(Optional.ofNullable(userOne), Optional.ofNullable(userTwo));

         Assertions.assertThrows(ObjectNotFoundException.class, () -> service.create(Long.valueOf(30), userTwo.getId()));
     }

     @Test
     void shouldThrowExceptionIfUserTwoIsNotFound(){
         User userOne = new UserMock().withUserType(UserType.USER).buildUser();
         User userTwo = null;

         Mockito.when(userRepository.findById(any())).thenReturn(Optional.ofNullable(userOne), Optional.ofNullable(userTwo));

         Assertions.assertThrows(ObjectNotFoundException.class, () -> service.create(userOne.getId(), Long.valueOf(30)));
     }

     @Test
     void shouldThrowExceptionIfUserOneIsNotTypeUser(){
         User userOne = new UserMock().withUserType(UserType.DOCTOR).buildUser();
         User userTwo = new UserMock().withId(Long.valueOf(2)).withUserType(UserType.PERSONAL_TRAINER).buildUser();

         Mockito.when(userRepository.findById(any())).thenReturn(Optional.ofNullable(userOne), Optional.ofNullable(userTwo));

         Assertions.assertThrows(OperationNotSupportedByThisUserException.class, () -> service.create(userOne.getId(), Long.valueOf(30)));
     }

     @Test
     void shouldThrowExceptionIfUserTwoIsNotTypePersonalOrDoctor(){
         User userOne = new UserMock().withUserType(UserType.USER).buildUser();
         User userTwo = new UserMock().withId(Long.valueOf(2)).withUserType(UserType.USER).buildUser();

         Mockito.when(userRepository.findById(any())).thenReturn(Optional.ofNullable(userOne), Optional.ofNullable(userTwo));

         Assertions.assertThrows(OperationNotSupportedByThisUserException.class, () -> service.create(userOne.getId(), Long.valueOf(30)));
     }

    @Test
    void shouldDeleteUser() {
        User userOne = new UserMock().withUserType(UserType.USER).buildUser();
        User userTwo = new UserMock().withId(Long.valueOf(2)).withUserType(UserType.PERSONAL_TRAINER).buildUser();

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.ofNullable(userOne), Optional.ofNullable(userTwo));

        Assertions.assertDoesNotThrow(()->service.delete(userOne.getId(), userTwo.getId()));
    }

    @Test
    void shouldReturnListOfUsersInRelationshipWithUserOne() {

    }
}