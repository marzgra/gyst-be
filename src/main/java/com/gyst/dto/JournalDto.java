package com.gyst.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalDto {
    private Long id;
    private LocalDateTime creationDate;
    private String entry;
}
