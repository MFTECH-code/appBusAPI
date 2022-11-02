package br.com.appbus.api.model.dto.user;

import java.time.LocalDate;

public record UpdateUserDTO(
        String name,
        String password,
        String email,
        String phone,
        Integer indicatedFriends,
        Integer score
) {
}
