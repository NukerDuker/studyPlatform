package ru.skillfactory.studyPlatform.service;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skillfactory.studyPlatform.entity.Score;
import ru.skillfactory.studyPlatform.repository.ScoresRepo;

@Data
@Service
public class ScoresService {

    private final ScoresRepo scoresRepo;

    /**
     * Method to make score for lesson to student
     * @param score - json representation of Score object.
     * @return json representation of Score object or error message.
     */
    public ResponseEntity<Object> makeScore(Score score) {
        return ResponseEntity.ok(scoresRepo.save(score));
    }

}
