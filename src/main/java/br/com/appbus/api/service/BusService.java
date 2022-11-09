package br.com.appbus.api.service;

import br.com.appbus.api.model.dto.bus.CreateBusDTO;
import br.com.appbus.api.model.dto.bus.ReadBusDTO;
import br.com.appbus.api.model.dto.bus.UpdateBusDTO;
import br.com.appbus.api.model.dto.evaluation.ReadEvaluationDTO;
import br.com.appbus.api.model.entity.Bus;
import br.com.appbus.api.model.mapper.BusMapper;
import br.com.appbus.api.model.mapper.EvaluationMapper;
import br.com.appbus.api.model.repository.BusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {
    private final BusRepository repository;
    private final EvaluationService evaluationService;

    public BusService(BusRepository repository, EvaluationService evaluationService) {
        this.repository = repository;
        this.evaluationService = evaluationService;
    }

    public Bus create(CreateBusDTO busDTO) {
        var bus = BusMapper.createBus(busDTO);
        return repository.save(bus);
    }

    public List<ReadBusDTO> getAll() {
        return repository.findAll().stream().map(BusMapper::readBus).toList();
    }

    public ReadBusDTO getByNumber(String number) throws Exception {
        var bus = repository.findByNumber(number).orElseThrow(() -> new Exception("Bus not found"));
        return BusMapper.readBus(bus);
    }

    public List<ReadBusDTO> getByLine(String line) throws Exception {
        var bus = repository.findByLine(line).orElseThrow(() -> new Exception("Bus not found"));
        return bus.stream().map(BusMapper::readBus).toList();
    }

    public List<ReadEvaluationDTO> getBusEvaluations(String number) throws Exception {
        var bus = repository.findByNumber(number).orElseThrow(() -> new Exception("Bus not found"));
        return evaluationService.getAll()
                .stream()
                .filter(e -> e.getBus().getId() == bus.getId())
                .map(EvaluationMapper::readEvaluation)
                .toList();
    }

    public void update(UpdateBusDTO busDTO, Long id) throws Exception {
        var bus = findBus(id);
        bus.setRate(busDTO.rate());
        bus.setAccessible(busDTO.accessible());
        bus.setState(busDTO.state());
        bus.setType(busDTO.type());
        repository.save(bus);
    }

    public void delete(Long id) throws Exception {
        var bus = findBus(id);
        repository.delete(bus);
    }

    private Bus findBus(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Bus not found"));
    }

}
