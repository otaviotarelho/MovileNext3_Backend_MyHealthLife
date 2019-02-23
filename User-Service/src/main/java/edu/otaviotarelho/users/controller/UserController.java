package edu.otaviotarelho.users.controller;

import edu.otaviotarelho.users.domain.User;
import edu.otaviotarelho.users.domain.dto.UserDTO;
import edu.otaviotarelho.users.domain.enumerator.UserType;
import edu.otaviotarelho.users.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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

    @GetMapping(value = "/all/{type}")
    public ResponseEntity<Page<UserDTO>> findAllByType(@PathVariable("type") UserType type,
                                                       @RequestParam(value="page", defaultValue="0") Integer page,
                                                       @RequestParam(value="linesPerPage", defaultValue="24") Integer linePerPage,
                                                       @RequestParam(value="orderBy", defaultValue="name") String orderBy,
                                                       @RequestParam(value="direction", defaultValue="ASC") String direction){
        PageRequest pageRequest = PageRequest.of(page, linePerPage, Sort.Direction.valueOf(orderBy), direction);

        Iterable<User> users = service.findAllUsersByType(type, pageRequest);

        Page<UserDTO> userDTOS = new ModelMapper().map(users, new TypeToken<List<UserDTO>>() {}.getType());

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
