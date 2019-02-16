package edu.otaviotarelho.users.service;

import edu.otaviotarelho.users.controller.exception.OperationNotSupportedByThisUserException;
import edu.otaviotarelho.users.domain.Relationships;
import edu.otaviotarelho.users.domain.User;
import edu.otaviotarelho.users.domain.dto.RelationshipDTO;
import edu.otaviotarelho.users.domain.dto.UserDTO;
import edu.otaviotarelho.users.domain.enumerator.UserType;
import edu.otaviotarelho.users.repository.RelationshipRepository;
import edu.otaviotarelho.users.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class RelationshipService {

    private UserRepository userRepository;
    private RelationshipRepository repository;
    private User user;
    private User userProfessional;

    @Autowired
    public RelationshipService(UserRepository userRepository, RelationshipRepository repository) {
        this.userRepository = userRepository;
        this.repository = repository;
    }

    public RelationshipDTO create(Long idUser, Long idProfessional){
        getUsersOperation(idUser, idProfessional);

        verifyOperation(user, userProfessional);

        Relationships relationships = new Relationships().setUser(idUser).setProfessional(idProfessional);

        repository.save(relationships);

        return new RelationshipDTO().setUser( new ModelMapper().map(user, UserDTO.class)).
                                                                setUsersRelated(Arrays.asList(new ModelMapper().map(userRepository, UserDTO.class)));
    }

    public void delete(Long idUser, Long idProfessional){
        getUsersOperation(idUser, idProfessional);

        verifyOperation(user, userProfessional);

        Relationships relationships = new Relationships().setUser(idUser).setProfessional(idProfessional);

        repository.delete(relationships);
    }

    public RelationshipDTO findAllRelationshipsOfUser(){

        return null;
    }

    private void getUsersOperation(Long idUser, Long idProfessional) {
        user = userRepository.findById(idUser).orElseThrow(() -> new ObjectNotFoundException(new User().setId(idUser), ""));
        userProfessional = userRepository.findById(idUser).orElseThrow(() -> new ObjectNotFoundException(new User().setId(idProfessional), ""));
    }

    private void verifyOperation(User user, User userProfessional) {
        if (user.getType() != UserType.USER || (userProfessional.getType() != UserType.DOCTOR && userProfessional.getType() != UserType.PERSONAL_TRAINER)) {
            throw new OperationNotSupportedByThisUserException("Operation not allowed");
        }
    }

}
