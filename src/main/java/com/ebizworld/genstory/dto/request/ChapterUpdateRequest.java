package com.ebizworld.genstory.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ChapterUpdateRequest {

    @NotBlank
    @Size(min = 3, message = "INVALID_REQUEST_TITLE")
    @JsonProperty("title")
    String title;

    @NotNull(message = "INVALID_REQUEST_CHAPTER_NUMBER")
    @Min(value = 0)
    @JsonProperty("chapterNumber")
    Integer chapterNumber;

    @NotBlank
    @Size(min = 3, message = "INVALID_REQUEST_CONTENT")
    String content;
}
