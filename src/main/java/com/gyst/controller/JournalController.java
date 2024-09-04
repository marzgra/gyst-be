package com.gyst.controller;

import com.gyst.dto.JournalDto;
import com.gyst.service.JournalService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journals")
@AllArgsConstructor
public class JournalController {

    private JournalService journalService;

    @GetMapping("/all")
    public ResponseEntity<List<JournalDto>> getAllJournals() {
        return ResponseEntity.ok(journalService.getAllJournals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalDto> getJournalById(@PathVariable Long id) {
        return journalService.getJournalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public JournalDto createJournal(@RequestBody JournalDto journal) {
        return journalService.createJournal(journal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JournalDto> updateJournal(@PathVariable Long id, @RequestBody JournalDto journal) {
        return ResponseEntity.ok(journalService.updateJournal(id, journal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJournal(@PathVariable Long id) {
        journalService.deleteJournal(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public Page<JournalDto> getJournalsByDateRange(
            @RequestParam("startDate") LocalDateTime startDate,
            @RequestParam("endDate") LocalDateTime endDate,
            Pageable pageable) {
        return journalService.getJournalsByDateRange(startDate, endDate, pageable);
    }

}
