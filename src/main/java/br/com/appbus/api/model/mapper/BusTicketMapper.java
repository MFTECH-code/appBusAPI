package br.com.appbus.api.model.mapper;

import br.com.appbus.api.model.dto.busTicket.CreateBusTicketDTO;
import br.com.appbus.api.model.dto.busTicket.ReadBusTicketDTO;
import br.com.appbus.api.model.entity.BusTicket;

public class BusTicketMapper {
    public static BusTicket createBusTicket(CreateBusTicketDTO busTicketDTO) {
        return new BusTicket(
                busTicketDTO.ticketNumber(),
                busTicketDTO.ticketType(),
                0.0);
    }

    public static ReadBusTicketDTO readBusTicket(BusTicket busTicket) {
        return new ReadBusTicketDTO(
                busTicket.getId(),
                busTicket.getTicketNumber(),
                busTicket.getTicketType(),
                busTicket.getBalance()
        );
    }
}
