package ru.skillfactory.studyPlatform.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillfactory.studyPlatform.entity.Score;
import ru.skillfactory.studyPlatform.jsonModels.MakeStudentScore;
import ru.skillfactory.studyPlatform.service.ScoresService;

@Data
@RestController
@RequestMapping("/api/v1/scores")
public class ScoresController {

    private final ScoresService scoresService;

    @PostMapping("/make")
    public ResponseEntity<Object> makeScore(@RequestBody MakeStudentScore scoreAndStudentId) {
        return scoresService.makeScore(scoreAndStudentId);
    }
}
