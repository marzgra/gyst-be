package com.gyst.mapper;

import com.gyst.dto.JournalDto;
import com.gyst.entity.JournalEntry;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JournalMapper {

    JournalEntry toEntity(JournalDto journalDto);

    JournalDto toDto(JournalEntry journalEntry);
}
