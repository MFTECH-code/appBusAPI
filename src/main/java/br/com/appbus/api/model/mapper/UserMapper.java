package br.com.appbus.api.model.mapper;

import br.com.appbus.api.model.dto.user.CreateUserDTO;
import br.com.appbus.api.model.dto.user.ReadUserDTO;
import br.com.appbus.api.model.dto.user.UpdateUserDTO;
import br.com.appbus.api.model.entity.User;

import java.time.LocalDate;

public class UserMapper {
    public static User createUser(CreateUserDTO userDTO) {
        return new User(
                userDTO.name(),
                userDTO.password(),
                userDTO.email(),
                userDTO.phone(),
                userDTO.document(),
                userDTO.birthDate(),
                userDTO.indicatedFriends(),
                userDTO.score());
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
