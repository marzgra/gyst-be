package com.gyst.controller;

import com.gyst.dto.GoalDto;
import com.gyst.service.GoalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/goals")
@AllArgsConstructor
public class GoalController {
    private final GoalService goalService;

    @PostMapping("/create")
    public ResponseEntity<GoalDto> createGoal(@RequestBody GoalDto goalDto) {
        return ResponseEntity.ok(goalService.createGoal(goalDto));
    }

}
