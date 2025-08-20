package com.ebizworld.genstory.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public enum ErrorCode {

    // ================= STORY (1000 - 1099) =================
    STORY_ALREADY_EXISTS(1001, "Story already exists", HttpStatus.BAD_REQUEST),
    STORY_NOT_FOUND(1002, "Story not found", HttpStatus.NOT_FOUND),

    // ================= CHAPTER (1100 - 1199) =================
    CHAPTER_ALREADY_EXISTS(1101, "Chapter already exists", HttpStatus.BAD_REQUEST),
    CHAPTER_NUMBER_ALREADY_EXISTS(1102, "Chapter number already exists", HttpStatus.BAD_REQUEST),
    CHAPTER_NOT_FOUND(1103, "Chapter not found", HttpStatus.NOT_FOUND),

    // ================= REQUEST VALIDATION (2000 - 2099) =================
    INVALID_REQUEST_TITLE(2001, "Title must be at least 5 characters long", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST_GENRE(2002, "Genre must contain at least 5 characters", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST_STORY_LENGTH(2003, "Story length must be at least 1000", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST_DESCRIPTION(2004, "Description must contain at least 3 characters", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST_NUMBER_OF_CHAPTERS(2005, "Number of chapters must be at least 1", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST_READER_AGE(2006, "Reader age must be at least 3", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST_HASH_CONTENT(2007, "Hash content must have at least 3 characters", HttpStatus.BAD_REQUEST),

    INVALID_REQUEST_CHAPTER_NUMBER(2008, "Chapter number is required", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST_CONTENT(2009, "Content must be at least 1000 characters", HttpStatus.BAD_REQUEST),

    // ================= SECURITY / AUTH (3000 - 3099) =================
    UNAUTHORIZED(3001, "Unauthorized", HttpStatus.UNAUTHORIZED),
    INVALID_KEY(3002, "Invalid key", HttpStatus.BAD_REQUEST),

    // ================= SYSTEM (9000 - 9999) =================
    UNKNOWN_ERROR(9999, "An unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int code;
    private final String message;
    private final HttpStatus statusCode;

    ErrorCode(int code, String message, HttpStatus statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
