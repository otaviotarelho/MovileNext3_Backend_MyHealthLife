package edu.otaviotarelho.users.repository;

import edu.otaviotarelho.users.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {

    @Transactional(readOnly = true)
    Optional<User> findByUsernameAndPassword(String email);

}
