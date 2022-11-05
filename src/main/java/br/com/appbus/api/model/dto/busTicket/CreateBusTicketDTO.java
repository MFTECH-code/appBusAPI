package br.com.appbus.api.model.dto.busTicket;

import br.com.appbus.api.model.BusTicketType;

public record CreateBusTicketDTO(
        String ticketNumber,
        BusTicketType ticketType
) {
}
