package br.com.appbus.api.model.mapper;

import br.com.appbus.api.model.dto.evaluation.CreateEvaluationDTO;
import br.com.appbus.api.model.dto.evaluation.ReadEvaluationDTO;
import br.com.appbus.api.model.entity.Evaluation;

public class EvaluationMapper {

    public static Evaluation createEvaluation(CreateEvaluationDTO evaluationDTO) {
        return new Evaluation(
                evaluationDTO.user(),
                evaluationDTO.bus(),
                evaluationDTO.comment(),
                evaluationDTO.evaluationNote()
        );
    }

    public static ReadEvaluationDTO readEvaluation(Evaluation evaluation) {
        return new ReadEvaluationDTO(
                evaluation.getUser().getId(),
                evaluation.getBus().getId(),
                evaluation.getComment(),
                evaluation.getEvaluationNote()
        );
    }
}
