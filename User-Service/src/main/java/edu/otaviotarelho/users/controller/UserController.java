package edu.otaviotarelho.users.controller;

import edu.otaviotarelho.users.domain.User;
import edu.otaviotarelho.users.domain.dto.UserDTO;
import edu.otaviotarelho.users.domain.enumerator.UserType;
import edu.otaviotarelho.users.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> get(@PathVariable("id") Long id){
        User user = service.getUserById(id);
        return ok().body(user);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@Valid @RequestBody User user){
        user = service.insert(user);

        URI userUri = getUri(user);

        UserDTO userDTO = new ModelMapper().map(user, UserDTO.class);

        return created(userUri).body(userDTO);
    }



    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id") Long id, @Valid @RequestBody User user) {
        user.setId(id);
        user = service.update(user);

        URI userUri = getUri(user);
        UserDTO userDTO = new ModelMapper().map(user, UserDTO.class);
        return created(userUri).body(userDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        User user = new User();
        user.setId(id);
        service.delete(user);
        return noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllByType(@PathVariable("type") UserType type){
        Iterable<User> users = service.findAllUsersByType(type);

        Type listType = new TypeToken<List<UserDTO>>() {}.getType();
        List<UserDTO> userDTOS = new ModelMapper().map(users, listType);

        return ok().body(userDTOS);
    }

    private URI getUri(User user) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest().
                        path("/{id}").
                        buildAndExpand(user.getId())
                .toUri();
    }

}
