package br.com.appbus.api.controller;

import br.com.appbus.api.model.dto.evaluation.CreateEvaluationFromRequestDTO;
import br.com.appbus.api.model.dto.evaluation.ReadEvaluationDTO;
import br.com.appbus.api.model.mapper.EvaluationMapper;
import br.com.appbus.api.service.BusService;
import br.com.appbus.api.service.EvaluationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/evaluation")
public class EvaluationController {
    private final EvaluationService service;
    private final BusService busService;

    public EvaluationController(EvaluationService service, BusService busService) {

        this.service = service;
        this.busService = busService;
    }

    @PostMapping
    public ResponseEntity<ReadEvaluationDTO> create(@RequestBody CreateEvaluationFromRequestDTO evaluationDTO) {
        try {
            var res = service.create(evaluationDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(EvaluationMapper.readEvaluation(res));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<Collection<ReadEvaluationDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/bus/{number}")
    public ResponseEntity<Collection<ReadEvaluationDTO>> getBusEvaluations(@PathVariable String number) {
        try {
            var res = busService.getBusEvaluations(number);
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
