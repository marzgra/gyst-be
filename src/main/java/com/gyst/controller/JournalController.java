package com.gyst.controller;

import com.gyst.dto.JournalDto;
import com.gyst.service.JournalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/journal")
@AllArgsConstructor
public class JournalController {
    private final JournalService journalService;

    @PostMapping("/create")
    public ResponseEntity<JournalDto> createEntry(@RequestBody JournalDto journalDto) {
        return ResponseEntity.ok(journalService.createEntry(journalDto));
    }
}
