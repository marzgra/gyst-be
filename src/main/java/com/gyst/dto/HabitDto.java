package com.gyst.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitDto {
    private Long habitId;
    private String name;
    private int streak;
    private List<HabitExecutionDto> history;

}
