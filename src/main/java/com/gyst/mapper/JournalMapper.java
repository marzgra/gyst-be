package com.gyst.mapper;

import com.gyst.dto.JournalDto;
import com.gyst.entity.JournalEntry;
import org.mapstruct.Mapper;

@Mapper
public interface JournalMapper {

    JournalEntry toEntity(JournalDto journalDto);

    JournalDto toDto(JournalEntry journalEntry);
}
