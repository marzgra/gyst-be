package com.gyst.service;

import com.gyst.dto.JournalDto;
import com.gyst.entity.JournalEntry;
import com.gyst.mapper.JournalMapper;
import com.gyst.repository.JournalRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JournalService {

    private JournalRepository journalRepository;
    private JournalMapper mapper;

    public List<JournalDto> getAllJournals() {
        return journalRepository.findAll()
                .stream().map(entry -> mapper.toDto(entry))
                .toList();
    }

    public Optional<JournalDto> getJournalById(Long id) {
        return journalRepository.findById(id)
                .flatMap(entry -> Optional.ofNullable(mapper.toDto(entry)));
    }

    public JournalDto createJournal(JournalDto journal) {
        journal.setCreationDate(LocalDateTime.now());
        JournalEntry savedEntity = journalRepository.save(mapper.toEntity(journal));
        return mapper.toDto(savedEntity);
    }

    public JournalDto updateJournal(Long id, JournalDto updatedJournal) {
        return journalRepository.findById(id)
                .map(journal -> {
                    journal.setEntry(updatedJournal.getEntry());
                    return mapper.toDto(journalRepository.save(journal));
                }).orElseThrow();
    }

    public void deleteJournal(Long id) {
        journalRepository.deleteById(id);
    }

    public Page<JournalDto> getJournalsByDateRange(LocalDateTime startDate,
                                                     LocalDateTime endDate,
                                                     Pageable pageable) {
        Page<JournalEntry> journalsPage = journalRepository.findByCreationDateBetween(startDate, endDate, pageable);
        return journalsPage.map(mapper::toDto);
    }
}
