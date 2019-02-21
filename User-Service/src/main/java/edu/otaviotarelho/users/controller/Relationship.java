package edu.otaviotarelho.users.controller;

import edu.otaviotarelho.users.domain.dto.RelationshipDTO;
import edu.otaviotarelho.users.service.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.ResponseEntity.*;

@RestController
public class Relationship {

    private RelationshipService service;


    @Autowired
    public Relationship(RelationshipService service){
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RelationshipDTO> get(@PathVariable("id") Long id){
        RelationshipDTO relationshipDTO = service.findAllRelationshipsOfUser(id);
        return ok().body(relationshipDTO);
    }

    @PostMapping(value = "/{id}/{idProfessional}")
    public ResponseEntity<RelationshipDTO> insert(@PathVariable("id") Long idUser, @PathVariable("idProfessional") Long idProfessional){
        RelationshipDTO relationshipDTO = service.create(idUser, idProfessional);

        URI userUri = getUri(relationshipDTO);

        return created(userUri).body(relationshipDTO);
    }

    @DeleteMapping(value = "/{id}/{idProfessional}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long idUser, @PathVariable("idProfessional") Long idProfessional){
        service.delete(idUser, idProfessional);
        return noContent().build();
    }

    private URI getUri(RelationshipDTO relationshipDTO) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest().
                        path("/{id}").
                        buildAndExpand(relationshipDTO.getUser().getId())
                .toUri();
    }
}
