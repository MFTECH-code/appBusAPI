package br.com.appbus.api.model.dto.evaluation;

public record UpdateEvaluationDTO(
        String comment,
        Integer evaluationNote
) {
}
