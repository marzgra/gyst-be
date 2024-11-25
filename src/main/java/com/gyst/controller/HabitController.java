package com.gyst.controller;

import com.gyst.dto.HabitDto;
import com.gyst.service.HabitService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/habits")
public class HabitController {
    private final HabitService habitService;

    @GetMapping
    public ResponseEntity<List<HabitDto>> getHabits() {
        return ResponseEntity.ok(habitService.getHabits());
    }
}
