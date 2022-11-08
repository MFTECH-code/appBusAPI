package br.com.appbus.api.model.repository;

import br.com.appbus.api.model.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<Bus> findByNumber(String number);
    Optional<List<Bus>> findByLine(String line);
}
