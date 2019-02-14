package edu.otaviotarelho.users.repository;

import edu.otaviotarelho.users.domain.User;
import edu.otaviotarelho.users.domain.enumerator.UserType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {

    @Transactional(readOnly = true)
    Optional<User> findByUsernameAndPassword(String username, String password);

    @Transactional(readOnly = true)
    List<User> findAllByType(UserType type);

    @Transactional(readOnly = true)
    Optional<User> findById(Long id);

}
