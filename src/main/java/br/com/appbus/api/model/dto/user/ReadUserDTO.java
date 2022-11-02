package br.com.appbus.api.model.dto.user;

import java.time.LocalDate;

public record ReadUserDTO(
        Long id,
        String name,
        String email,
        String phone,
        String document,
        LocalDate birthDate,
        Integer indicatedFriends,
        Integer score
) {
}
