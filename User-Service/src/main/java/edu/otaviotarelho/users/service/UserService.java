package edu.otaviotarelho.users.service;

import edu.otaviotarelho.users.domain.User;
import edu.otaviotarelho.users.domain.enumerator.UserType;
import edu.otaviotarelho.users.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User insert(User user){
        return repository.save(user);
    }

    public User update(User user){
        return repository.save(user);
    }

    public void delete(User user){
        repository.delete(user);
    }

    public List<User> findAllUsersByType(UserType type){
        return repository.findAllByType(type);
    }

    public User getUserByUsernameAndPassword(User user){
        return repository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).orElseThrow(() -> new ObjectNotFoundException(user, "User not found"));
    }

    public User getUserById(Long id){
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(new User(), "User not found"));
    }
}
