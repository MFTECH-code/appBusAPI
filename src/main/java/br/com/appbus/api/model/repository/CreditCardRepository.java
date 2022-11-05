package br.com.appbus.api.model.repository;

import br.com.appbus.api.model.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
