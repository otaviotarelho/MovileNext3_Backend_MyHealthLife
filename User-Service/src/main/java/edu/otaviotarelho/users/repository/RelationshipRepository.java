package edu.otaviotarelho.users.repository;

import edu.otaviotarelho.users.domain.Relationships;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RelationshipRepository extends CrudRepository<Relationships, Long> {

    @Transactional(readOnly = true)
    List<Relationships> findAllByUser(Long user);
}
