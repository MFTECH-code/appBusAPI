package br.com.appbus.api.model.dto.busTicket;

import br.com.appbus.api.model.BusTicketType;

public record ReadBusTicketDTO(
        Long id,
        String ticketNumber,
        BusTicketType ticketType,
        Double balance
) {
}
