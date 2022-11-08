package br.com.appbus.api.controller;

import br.com.appbus.api.model.dto.bus.CreateBusDTO;
import br.com.appbus.api.model.dto.bus.ReadBusDTO;
import br.com.appbus.api.model.dto.bus.UpdateBusDTO;
import br.com.appbus.api.model.dto.user.UpdateUserDTO;
import br.com.appbus.api.model.mapper.BusMapper;
import br.com.appbus.api.service.BusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/bus")
public class BusController {
    private final BusService service;

    public BusController(BusService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ReadBusDTO> create(@RequestBody CreateBusDTO busDTO) {
        try {
            var res = service.create(busDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(BusMapper.readBus(res));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<ReadBusDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<ReadBusDTO> getByNumber(@PathVariable String number) {
        try {
            var res = service.getByNumber(number);
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/line/{line}")
    public ResponseEntity<Collection<ReadBusDTO>> getByLine(@PathVariable String line) {
        try {
            var res = service.getByLine(line);
            return ResponseEntity.ok(res);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody UpdateBusDTO busDTO, @PathVariable Long id) {
        try {
            service.update(busDTO, id);
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
