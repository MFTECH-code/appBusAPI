package br.com.appbus.api.model.mapper;

import br.com.appbus.api.model.dto.creditCard.CreateCreditCardDTO;
import br.com.appbus.api.model.dto.creditCard.ReadCreditCardDTO;
import br.com.appbus.api.model.entity.CreditCard;

public class CreditCardMapper {
    public static CreditCard createCreditCard(CreateCreditCardDTO creditCardDTO) {
        return new CreditCard(
                creditCardDTO.cardNumber(),
                creditCardDTO.titularName(),
                creditCardDTO.titularDocument(),
                creditCardDTO.cvv(),
                creditCardDTO.flag(),
                creditCardDTO.expirationDate());
    }

    public static ReadCreditCardDTO readCreditCard(CreditCard creditCard) {
        return new ReadCreditCardDTO(
                creditCard.getId(),
                creditCard.getCardNumber(),
                creditCard.getTitularName(),
                creditCard.getTitularDocument(),
                creditCard.getCvv(),
                creditCard.getFlag(),
                creditCard.getExpirationDate());
    }
}
