package com.ebizworld.genstory.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChapterCreationRequest {
    @NotBlank
    @Size(min = 3, message = "INVALID_CHAPTER_TITLE")
    @JsonProperty("title")
    String title;

    @Min(value = 1, message = "INVALID_CHAPTER_NUMBER")
    @JsonProperty("chapterNumber")
    int chapterNumber;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    @NotBlank
    @Size(min = 5, message = "INVALID_CHAPTER_CONTENT") // có thể bỏ nếu không cần ràng buộc min
    String content;

    @JsonProperty("createAt")
    LocalDate createAt;
}