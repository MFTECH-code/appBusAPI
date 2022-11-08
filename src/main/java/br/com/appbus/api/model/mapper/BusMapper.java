package br.com.appbus.api.model.mapper;

import br.com.appbus.api.model.dto.bus.CreateBusDTO;
import br.com.appbus.api.model.dto.bus.ReadBusDTO;
import br.com.appbus.api.model.entity.Bus;

public class BusMapper {
    public static Bus createBus(CreateBusDTO busDTO) {
        return new Bus(
                busDTO.number(),
                busDTO.line(),
                busDTO.region(),
                busDTO.rate(),
                busDTO.accessible(),
                busDTO.state(),
                0.0,
                busDTO.type()
        );
    }

    public static ReadBusDTO readBus(Bus bus) {
        return new ReadBusDTO(
                bus.getId(),
                bus.getNumber(),
                bus.getLine(),
                bus.getRegion(),
                bus.getRate(),
                bus.getAccessible(),
                bus.getState(),
                bus.getAveragePoints(),
                bus.getType()
        );
    }
}
