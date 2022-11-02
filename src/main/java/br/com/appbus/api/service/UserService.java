package br.com.appbus.api.service;

import br.com.appbus.api.model.dto.user.CreateUserDTO;
import br.com.appbus.api.model.dto.user.ReadUserDTO;
import br.com.appbus.api.model.dto.user.UpdateUserDTO;
import br.com.appbus.api.model.entity.User;
import br.com.appbus.api.model.mapper.UserMapper;
import br.com.appbus.api.model.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                user.getAuthorities());
    }

    public User create(CreateUserDTO userDTO) {
        var user = UserMapper.createUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public List<ReadUserDTO> getAll() {
        return repository.findAll().stream().map(UserMapper::readUser).toList();
    }

    public ReadUserDTO getById(Long id) throws Exception {
        var user = findUser(id);
        return UserMapper.readUser(user);
    }

    public void update(UpdateUserDTO userDTO, Long id) throws Exception {
        var user = findUser(id);

        user.setName(userDTO.name());
        user.setPassword(userDTO.password());
        user.setEmail(userDTO.email());
        user.setPhone(userDTO.phone());
        user.setIndicatedFriends(userDTO.indicatedFriends());
        user.setScore(userDTO.score());

        repository.save(user);
    }

    public void delete(Long id) throws Exception {
        var user = findUser(id);
        repository.delete(user);
    }

    private User findUser(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("User not found"));
    }
}
