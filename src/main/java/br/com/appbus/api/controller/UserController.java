package br.com.appbus.api.controller;

import br.com.appbus.api.model.dto.user.CreateUserDTO;
import br.com.appbus.api.model.dto.user.ReadUserDTO;
import br.com.appbus.api.model.dto.user.UpdateUserDTO;
import br.com.appbus.api.model.entity.User;
import br.com.appbus.api.model.mapper.UserMapper;
import br.com.appbus.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ReadUserDTO> create(@RequestBody CreateUserDTO userDTO) {
        try {
            var res = service.create(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header("findRoute", "/api/user/" + res.getId())
                    .body(UserMapper.readUser(res));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadUserDTO> getById(@PathVariable Long id) {
        try {
            var res = service.getById(id);
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<Collection<ReadUserDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody UpdateUserDTO userDTO, @PathVariable Long id) {
        try {
            service.update(userDTO, id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
