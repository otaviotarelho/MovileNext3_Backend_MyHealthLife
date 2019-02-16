package edu.otaviotarelho.users.repository;

import edu.otaviotarelho.users.domain.Relationships;
import org.springframework.data.repository.CrudRepository;

public interface RelationshipRepository extends CrudRepository<Relationships, Long> {

}
