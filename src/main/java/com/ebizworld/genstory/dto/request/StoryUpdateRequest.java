package com.ebizworld.genstory.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StoryUpdateRequest {
    @NotBlank
    @Size(min = 3, message = "INVALID_REQUEST_TITLE")
    String title;

    @NotBlank
    @Size(min = 5, message = "INVALID_REQUEST_GENRE")
    String genre;

    @Min(value = 1000, message = "INVALID_REQUEST_STORY_LENGTH")
    int chapterLength;

    @NotBlank
    @Size(min = 3, message = "INVALID_REQUEST_DESCRIPTION")
    String description;

    @Min(value = 1, message = "INVALID_REQUEST_NUMBER_OF_CHAPTERS")
    int numberOfChapters;

    @Min(value = 3, message = "INVALID_REQUEST_READER_AGE")
    int readerAge;

    @NotBlank
    @Size(min = 3, message = "INVALID_REQUEST_HASH_CONTENT")
    String hashContent;

}
