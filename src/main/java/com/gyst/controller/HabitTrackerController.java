package com.gyst.controller;

import com.gyst.dto.JournalDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/habits")
@AllArgsConstructor
public class HabitTrackerController {

    private HabitService habitService;


    @PostMapping
    public HabitDto createHabit(@RequestBody HabitDto habitToCreate) {
        return habitService.createHabit(habitToCreate);
    }

    @PutMapping
    public HabitDto updateHabit(@PathVariable Long habitId, @RequestBody HabitDto habitToUpdate) {
        return habitService.updateHabit(habitToUpdate);
    }

    @PutMapping
    public HabitDto archiveHabit(@PathVariable Long habitId) {
        return habitService.achiveHabit(habitId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<HabitDto>> getUsersActiveHabits(@PathVariable Long userId) {
        return ResponseEntity.ok(habitService.getUsersActiveHabits(userId));
    }

    @GetMapping("/filter")
    public Page<JournalDto> getStatsForUsersHabit(
            @RequestParam("startDate") LocalDateTime startDate,
            @RequestParam("endDate") LocalDateTime endDate,
            @PathVariable Long userId,
            @PathVariable Long habitId) {
        return habitService.getHabitExecutions(startDate, endDate, habitId);
    }

    @GetMapping("/filter")
    public Page<JournalDto> getStatsForUsersHabit(
            @RequestParam("startDate") LocalDateTime startDate,
            @RequestParam("endDate") LocalDateTime endDate,
            @PathVariable Long userId) {
        return habitService.getHabitExecutions(startDate, endDate, userId);
    }

    // TODO użyć spring principal etc
}
