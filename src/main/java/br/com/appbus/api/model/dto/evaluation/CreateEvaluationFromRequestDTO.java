package br.com.appbus.api.model.dto.evaluation;

public record CreateEvaluationDTO(
        Long userId,
        Long busId,
        String comment,
        Integer evaluationNote
) {
}
