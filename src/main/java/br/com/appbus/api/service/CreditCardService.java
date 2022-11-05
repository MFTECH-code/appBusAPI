package br.com.appbus.api.service;

import br.com.appbus.api.model.dto.creditCard.UpdateCreditCardDTO;
import br.com.appbus.api.model.entity.CreditCard;
import br.com.appbus.api.model.repository.CreditCardRepository;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {
    private final CreditCardRepository repository;

    public CreditCardService(CreditCardRepository repository) {
        this.repository = repository;
    }

    public void update(UpdateCreditCardDTO creditCardDTO, Long id) throws Exception {
        var creditCard = findById(id);
        creditCard.setCardNumber(creditCardDTO.cardNumber());
        creditCard.setCvv(creditCardDTO.cvv());
        creditCard.setFlag(creditCardDTO.flag());
        creditCard.setExpirationDate(creditCardDTO.expirationDate());
        creditCard.setTitularDocument(creditCardDTO.titularDocument());
        creditCard.setTitularName(creditCardDTO.titularName());
        repository.save(creditCard);
    }

    public void delete(Long id) throws Exception {
        var creditCard = findById(id);
        repository.delete(creditCard);
    }

    private CreditCard findById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("CreditCard not found"));
    }
}
