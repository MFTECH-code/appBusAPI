package br.com.appbus.api.model.dto.creditCard;

import br.com.appbus.api.model.CreditCardFlag;

import java.time.LocalDate;

public record CreateCreditCardDTO(
        String cardNumber,
        String titularName,
        String titularDocument,
        String cvv,
        CreditCardFlag flag,
        LocalDate expirationDate
) {
}
