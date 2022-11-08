package br.com.appbus.api.model.dto.bus;

import br.com.appbus.api.model.BusState;
import br.com.appbus.api.model.BusType;

public record CreateBusDTO(
        String number,
        String line,
        String region,
        Double rate,
        Boolean accessible,
        BusState state,
        BusType type
) {
}
