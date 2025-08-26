package com.ebizworld.genstory.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class ChatRequest {

    @NotBlank
    @Size(min = 3, message = "INVALID_REQUEST_DESCRIPTION")
    @JsonProperty("description")
    String description;

    @Size(min = 5, message = "INVALID_REQUEST_GENRE")
    @JsonProperty("genre")
    String genre;

    @Min(value = 1000, message = "INVALID_REQUEST_STORY_LENGTH")
    @JsonProperty("chapterLength")
    int chapterLength;

    @Min(value = 0, message = "INVALID_REQUEST_NUMBER_OF_CHAPTERS")
    @JsonProperty("numberOfChapters")
    int numberOfChapters;

    @Min(value = 3, message = "INVALID_REQUEST_READER_AGE")
    @JsonProperty("readerAge")
    int readerAge;
}
