package br.com.appbus.api.service;

import br.com.appbus.api.model.dto.busTicket.RechargeDTO;
import br.com.appbus.api.model.dto.busTicket.UpdateBusTicketDTO;
import br.com.appbus.api.model.entity.BusTicket;
import br.com.appbus.api.model.repository.BusTicketRepository;
import org.springframework.stereotype.Service;

@Service
public class BusTicketService {

    private final BusTicketRepository repository;

    public BusTicketService(BusTicketRepository repository) {
        this.repository = repository;
    }

    public void recharge(RechargeDTO rechargeDTO) throws Exception {
        var busTicket = repository.findByTicketNumber(rechargeDTO.ticketNumber()).orElseThrow(() -> new Exception("Ticket not found"));
        var updatedBalance = busTicket.getBalance() + rechargeDTO.value();
        busTicket.setBalance(updatedBalance);
        repository.save(busTicket);
    }

    public void update(UpdateBusTicketDTO busTicketDTO, Long id) throws Exception {
        var busTicket = findBusTicket(id);
        busTicket.setTicketNumber(busTicketDTO.ticketNumber());
        busTicket.setTicketType(busTicketDTO.ticketType());
        repository.save(busTicket);
    }

    public void delete(Long id) throws Exception {
        var busTicket = findBusTicket(id);
        repository.delete(busTicket);
    }

    private BusTicket findBusTicket(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Ticket not found"));
    }
}
