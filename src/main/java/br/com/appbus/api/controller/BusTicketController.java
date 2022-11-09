package br.com.appbus.api.controller;

import br.com.appbus.api.model.dto.busTicket.RechargeDTO;
import br.com.appbus.api.model.dto.busTicket.UpdateBusTicketDTO;
import br.com.appbus.api.service.BusTicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/busTicket")
public class BusTicketController {

    private final BusTicketService service;

    public BusTicketController(BusTicketService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> recharge(@RequestBody RechargeDTO rechargeDTO) {
        try {
            service.recharge(rechargeDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody UpdateBusTicketDTO busTicketDTO, @PathVariable Long id) {
        try {
            service.update(busTicketDTO, id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
