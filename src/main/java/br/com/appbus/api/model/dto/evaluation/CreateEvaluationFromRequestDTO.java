package br.com.appbus.api.model.dto.evaluation;

public record CreateEvaluationFromRequestDTO(
        Long userId,
        Long busId,
        String comment,
        Integer evaluationNote
) {
}
