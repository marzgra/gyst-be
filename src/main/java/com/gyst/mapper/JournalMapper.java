package com.gyst.mapper;

import com.gyst.dto.JournalDto;
import com.gyst.entity.JournalEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public interface JournalMapper {

    @Mapping(source = "moods", target = "moods", qualifiedByName = "mapMoodsToString")
    JournalEntry toEntity(JournalDto journalDto);

    @Mapping(source = "moods", target = "moods", qualifiedByName = "mapMoods")
    JournalDto toDto(JournalEntry journalEntry);

    @Named("mapMoods")
    static List<String> mapMoods(String moods) {
        return Arrays.asList(moods.split(","));
    }

    @Named("mapMoodsToString")
    static String mapMoodsToString(List<String> moods) {
        StringBuilder moodsStr = new StringBuilder();
        moods.forEach(mood -> moodsStr.append(",").append(mood));
        return moodsStr.toString();
    }
}
