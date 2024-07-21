package com.gyst.repository;

import com.gyst.entity.JournalEntry;
import org.springframework.data.repository.CrudRepository;

public interface JournalRepository extends CrudRepository<JournalEntry, Long> {
}
