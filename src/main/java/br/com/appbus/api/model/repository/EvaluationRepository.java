package br.com.appbus.api.model.repository;

import br.com.appbus.api.model.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
}
