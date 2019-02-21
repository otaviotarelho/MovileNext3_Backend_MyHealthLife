package edu.otaviotarelho.users.service;

import edu.otaviotarelho.users.ObjectMock.UserMock;
import edu.otaviotarelho.users.controller.exception.OperationNotSupportedByThisUserException;
import edu.otaviotarelho.users.domain.Relationships;
import edu.otaviotarelho.users.domain.User;
import edu.otaviotarelho.users.domain.enumerator.UserType;
import edu.otaviotarelho.users.repository.RelationshipRepository;
import edu.otaviotarelho.users.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

class RelationshipServiceTest {

    @Spy
    List<Relationships> listOfRelationships = new LinkedList<>();

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

        when(userRepository.findById(any())).thenReturn(ofNullable(userOne), ofNullable(userTwo));

        Relationships relationships = new Relationships().setUser(userOne.getId()).setProfessional(userTwo.getId());

        when(repository.save(any())).thenReturn(relationships);

        assertDoesNotThrow(() -> service.create(userOne.getId(), userTwo.getId()));
     }

     @Test
     void shouldThrowExceptionIfUserOneIsNotFound(){
         User userOne = null;
         User userTwo = new UserMock().withId(Long.valueOf(2)).withUserType(UserType.PERSONAL_TRAINER).buildUser();

         when(userRepository.findById(any())).thenReturn(ofNullable(userOne), ofNullable(userTwo));

         assertThrows(ObjectNotFoundException.class, () -> service.create(Long.valueOf(30), userTwo.getId()));
     }

     @Test
     void shouldThrowExceptionIfUserTwoIsNotFound(){
         User userOne = new UserMock().withUserType(UserType.USER).buildUser();
         User userTwo = null;

         when(userRepository.findById(any())).thenReturn(ofNullable(userOne), ofNullable(userTwo));

         assertThrows(ObjectNotFoundException.class, () -> service.create(userOne.getId(), Long.valueOf(30)));
     }

     @Test
     void shouldThrowExceptionIfUserOneIsNotTypeUser(){
         User userOne = new UserMock().withUserType(UserType.DOCTOR).buildUser();
         User userTwo = new UserMock().withId(Long.valueOf(2)).withUserType(UserType.PERSONAL_TRAINER).buildUser();

         when(userRepository.findById(any())).thenReturn(ofNullable(userOne), ofNullable(userTwo));

         assertThrows(OperationNotSupportedByThisUserException.class, () -> service.create(userOne.getId(), Long.valueOf(30)));
     }

     @Test
     void shouldThrowExceptionIfUserTwoIsNotTypePersonalOrDoctor(){
         User userOne = new UserMock().withUserType(UserType.USER).buildUser();
         User userTwo = new UserMock().withId(Long.valueOf(2)).withUserType(UserType.USER).buildUser();

         when(userRepository.findById(any())).thenReturn(ofNullable(userOne), ofNullable(userTwo));

         assertThrows(OperationNotSupportedByThisUserException.class, () -> service.create(userOne.getId(), Long.valueOf(30)));
     }

    @Test
    void shouldDeleteUser() {
        User userOne = new UserMock().withUserType(UserType.USER).buildUser();
        User userTwo = new UserMock().withId(Long.valueOf(2)).withUserType(UserType.PERSONAL_TRAINER).buildUser();

        when(userRepository.findById(any())).thenReturn(ofNullable(userOne), ofNullable(userTwo));

        assertDoesNotThrow(()->service.delete(userOne.getId(), userTwo.getId()));
    }

    @Test
    void shouldReturnListOfUsersInRelationshipWithOneUser() {
        User userOne = new UserMock().buildUser();

        Relationships relationships = new Relationships().setUser(userOne.getId()).setProfessional(userOne.getId());

        listOfRelationships.addAll(Arrays.asList(relationships, relationships, relationships, relationships));

        when(repository.findAllByUser(userOne.getId())).thenReturn(listOfRelationships);

        when(userRepository.findById(any())).thenReturn(ofNullable(userOne));

        assertDoesNotThrow(() -> service.findAllRelationshipsOfUser(userOne.getId()));
    }
}