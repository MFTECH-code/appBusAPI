package br.com.appbus.api.controller;

import br.com.appbus.api.model.dto.evaluation.CreateEvaluationFromRequestDTO;
import br.com.appbus.api.model.dto.evaluation.ReadEvaluationDTO;
import br.com.appbus.api.model.dto.evaluation.UpdateEvaluationDTO;
import br.com.appbus.api.model.mapper.EvaluationMapper;
import br.com.appbus.api.service.BusService;
import br.com.appbus.api.service.EvaluationService;
import br.com.appbus.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/evaluation")
public class EvaluationController {
    private final EvaluationService service;
    private final BusService busService;
    private final UserService userService;

    public EvaluationController(EvaluationService service, BusService busService, UserService userService) {

        this.service = service;
        this.busService = busService;
        this.userService = userService;
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
        return ResponseEntity.ok(
                service.getAll().stream().map(EvaluationMapper::readEvaluation).toList()
        );
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

    @GetMapping("/user/{id}")
    public ResponseEntity<Collection<ReadEvaluationDTO>> getUserEvaluations(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUserEvaluations(id));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{userId}/{busId}")
    public ResponseEntity<Object> update(@RequestBody UpdateEvaluationDTO evaluationDTO, @PathVariable Long userId, @PathVariable Long busId) {
        try {
            service.update(evaluationDTO, userId, busId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{userId}/{busId}")
    public ResponseEntity<Object> delete(@PathVariable Long userId, @PathVariable Long busId) {
        try {
            service.delete(userId, busId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
