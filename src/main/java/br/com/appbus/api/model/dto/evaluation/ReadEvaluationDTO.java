package br.com.appbus.api.model.dto.evaluation;

public record ReadEvaluationDTO(
        Long userId,
        Long busId,
        String comment,
        Integer evaluationNote
) {
}
