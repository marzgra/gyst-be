package com.gyst.controller;

import com.gyst.dto.TaskDto;
import com.gyst.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/today")
    public ResponseEntity<List<TaskDto>> getTodayTasks() {
        return ResponseEntity.ok(taskService.getTasksForToday());
    }
}
