package com.gyst.service;

import com.gyst.dto.JournalDto;
import com.gyst.entity.JournalEntry;
import com.gyst.mapper.JournalMapper;
import com.gyst.repository.JournalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JournalService {
    private final JournalRepository journalRepository;
    private final JournalMapper journalMapper;

    public JournalDto createEntry(JournalDto journalDto) {
        JournalEntry saved = journalRepository.save(journalMapper.toEntity(journalDto));
        return journalMapper.toDto(saved);
    }
}