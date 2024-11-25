package com.gyst.service;

import com.gyst.dto.TaskDto;
import com.gyst.entity.Task;
import com.gyst.mapper.TaskMapper;
import com.gyst.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public List<TaskDto> getTasksForToday() {
        List<Task> tasks = taskRepository.findByStatus(false);
        return tasks.stream().map(taskMapper::toDto).toList();
    }
}
