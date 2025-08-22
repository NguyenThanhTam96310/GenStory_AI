package com.ebizworld.genstory.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)

public class StoryResponse {
    String id;
    String title;
    String genre;
    int chapterLength;
    String description;
    String numberOfChapters;
    int readerAge;
    LocalDate createAt;

    List<ChapterResponse> chapters;
}
