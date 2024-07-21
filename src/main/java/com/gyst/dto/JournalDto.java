package com.gyst.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JournalDto {
    private Long id;
    private LocalDateTime creationDate;
    private String entry;
}
