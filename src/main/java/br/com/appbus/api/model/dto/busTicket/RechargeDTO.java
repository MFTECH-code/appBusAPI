package br.com.appbus.api.model.dto.busTicket;

public record RechargeDTO(
        String ticketNumber,
        Double value
) {
}
