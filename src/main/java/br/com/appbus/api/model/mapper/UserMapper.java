package br.com.appbus.api.model.mapper;

import br.com.appbus.api.model.Roles;
import br.com.appbus.api.model.dto.user.CreateUserDTO;
import br.com.appbus.api.model.dto.user.ReadUserDTO;
import br.com.appbus.api.model.dto.user.UpdateUserDTO;
import br.com.appbus.api.model.entity.Role;
import br.com.appbus.api.model.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class UserMapper {
    public static User createUser(CreateUserDTO userDTO) {
        var user = new User(
                userDTO.name(),
                userDTO.password(),
                userDTO.email(),
                userDTO.phone(),
                userDTO.document(),
                userDTO.birthDate(),
                userDTO.indicatedFriends(),
                userDTO.score());

        var roles = new ArrayList<Role>();
        roles.add(new Role(Roles.ROLE_USER));
        user.setRoles(roles);
        return user;
    }

    public static ReadUserDTO readUser(User user) {
        return new ReadUserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getDocument(),
                user.getBirthDate(),
                user.getIndicatedFriends(),
                user.getScore());
    }

    public static User updateUser(UpdateUserDTO userDTO) {
        return new User(
                userDTO.name(),
                userDTO.password(),
                userDTO.email(),
                userDTO.phone(),
                userDTO.indicatedFriends(),
                userDTO.score());
    }
}
