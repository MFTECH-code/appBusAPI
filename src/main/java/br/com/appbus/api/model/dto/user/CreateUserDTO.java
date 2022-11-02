package br.com.appbus.api.model.dto.user;

import java.time.LocalDate;

public record CreateUserDTO(
        String name,
        String password,
        String email,
        String phone,
        String document,
        LocalDate birthDate,
        Integer indicatedFriends,
        Integer score
) {
}
