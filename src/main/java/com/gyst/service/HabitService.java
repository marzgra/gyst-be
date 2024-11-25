package com.gyst.service;

import com.gyst.dto.HabitDto;
import com.gyst.mapper.HabitMapper;
import com.gyst.repository.HabitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class HabitService {
    private final HabitRepository habitRepository;
    private final HabitMapper habitMapper;

    public List<HabitDto> getHabits() {
        return habitRepository.findAll().stream()
                .map(habitMapper::toDto)
                .toList();
    }
}
