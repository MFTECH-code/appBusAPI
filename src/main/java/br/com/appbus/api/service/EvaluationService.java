package br.com.appbus.api.service;

import br.com.appbus.api.model.dto.evaluation.CreateEvaluationDTO;
import br.com.appbus.api.model.dto.evaluation.CreateEvaluationFromRequestDTO;
import br.com.appbus.api.model.dto.evaluation.ReadEvaluationDTO;
import br.com.appbus.api.model.dto.evaluation.UpdateEvaluationDTO;
import br.com.appbus.api.model.entity.Bus;
import br.com.appbus.api.model.entity.Evaluation;
import br.com.appbus.api.model.entity.User;
import br.com.appbus.api.model.mapper.EvaluationMapper;
import br.com.appbus.api.model.repository.BusRepository;
import br.com.appbus.api.model.repository.EvaluationRepository;
import br.com.appbus.api.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;
    private final BusRepository busRepository;

    public EvaluationService(EvaluationRepository evaluationRepository, UserRepository userRepository, BusRepository busRepository) {
        this.evaluationRepository = evaluationRepository;
        this.userRepository = userRepository;
        this.busRepository = busRepository;
    }

    public Evaluation create(CreateEvaluationFromRequestDTO evaluationDTO) throws Exception {
        var keys = getKeys(evaluationDTO.userId(), evaluationDTO.busId());
        var createEvaluation = new CreateEvaluationDTO(
                (User) keys.get("user"),
                (Bus) keys.get("bus"),
                evaluationDTO.comment(),
                evaluationDTO.evaluationNote()
        );
        return evaluationRepository.save(EvaluationMapper.createEvaluation(createEvaluation));
    }

    public List<Evaluation> getAll() {
        return evaluationRepository.findAll();
    }

    public void update(UpdateEvaluationDTO evaluationDTO, Long userId, Long busId) throws Exception {
        var evaluation = getAll()
                .stream()
                .filter(e -> e.getUser().getId() == userId && e.getBus().getId() == busId)
                .findFirst().orElseThrow(() -> new Exception("Evaluation not found"));

        evaluation.setComment(evaluationDTO.comment());
        evaluation.setEvaluationNote(evaluationDTO.evaluationNote());

        evaluationRepository.save(evaluation);
    }

    public void delete(Long userId, Long busId) throws Exception {
        var evaluation = getAll()
                .stream()
                .filter(e -> e.getUser().getId() == userId && e.getBus().getId() == busId)
                .findFirst().orElseThrow(() -> new Exception("Evaluation not found"));

        evaluationRepository.delete(evaluation);
    }

    private HashMap<String, Object> getKeys(Long userId, Long busId) throws Exception {
        var keys = new HashMap<String, Object>();
        var bus = busRepository.findById(busId).orElseThrow(() -> new Exception("Bus not found"));
        var user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
        keys.put("user", user);
        keys.put("bus", bus);
        return keys;
    }
}
