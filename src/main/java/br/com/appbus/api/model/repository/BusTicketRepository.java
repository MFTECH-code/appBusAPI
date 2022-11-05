package br.com.appbus.api.model.repository;

import br.com.appbus.api.model.entity.BusTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusTicketRepository extends JpaRepository<BusTicket, Long> {
    Optional<BusTicket> findByTicketNumber(String ticketNumber);
}
