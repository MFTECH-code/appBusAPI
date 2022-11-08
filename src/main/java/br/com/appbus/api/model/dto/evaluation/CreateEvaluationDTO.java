package br.com.appbus.api.model.dto.evaluation;

import br.com.appbus.api.model.entity.Bus;
import br.com.appbus.api.model.entity.User;

public record CreateEvaluationDTO(
        User user,
        Bus bus,
        String comment,
        Integer evaluationNote
) {
}
