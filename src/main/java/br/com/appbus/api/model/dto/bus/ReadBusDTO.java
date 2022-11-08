package br.com.appbus.api.model.dto.bus;

import br.com.appbus.api.model.BusState;
import br.com.appbus.api.model.BusType;

public record ReadBusDTO(
        Long id,
        String number,
        String line,
        String region,
        Double rate,
        Boolean accessible,
        BusState state,
        Double averagePoints,
        BusType type
) {
}
