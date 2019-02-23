package edu.otaviotarelho.users.repository;

import edu.otaviotarelho.users.domain.User;
import edu.otaviotarelho.users.domain.enumerator.UserType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {

    @Transactional(readOnly = true)
    Optional<User> findByUsernameAndPassword(String username, String password);

    @Transactional(readOnly = true)
    Page<User> findAllByType(UserType type, PageRequest pageRequest);

    @Transactional(readOnly = true)
    Optional<User> findById(Long id);

}
