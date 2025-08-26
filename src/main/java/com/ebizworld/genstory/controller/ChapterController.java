package com.ebizworld.genstory.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebizworld.genstory.dto.request.ChapterCreationRequest;
import com.ebizworld.genstory.dto.request.ChapterUpdateRequest;
import com.ebizworld.genstory.dto.response.ApiResponse;
import com.ebizworld.genstory.dto.response.ChapterResponse;
import com.ebizworld.genstory.service.ChapterService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/chapters")
@RequiredArgsConstructor // Tạo constructor tự động với tất cả các trường thay cho @Autowired
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // gán mặc định cho các trường là final và private
public class ChapterController {
    ChapterService chapterService;

    @PostMapping
    ApiResponse<ChapterResponse> createStory(@RequestBody @Valid ChapterCreationRequest request) {
        ApiResponse<ChapterResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(chapterService.createChapter(request));
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<ChapterResponse>> getAllChapter() {
        return ApiResponse.<List<ChapterResponse>>builder()
                .result(chapterService.getAllChapter())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<ChapterResponse> getChapterById(@PathVariable("id") String id) {
        return ApiResponse.<ChapterResponse>builder()
                .result(chapterService.getChapterById(id))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<ChapterResponse> updateChapter(
            @PathVariable("id") String id, @RequestBody @Valid ChapterUpdateRequest request) {
        return ApiResponse.<ChapterResponse>builder()
                .result(chapterService.updateChapter(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteStory(@PathVariable String id) {
        chapterService.deleteChapterById(id);
        return ApiResponse.<String>builder()
                .result("Chapter with id " + id + " deleted successfully")
                .build();
    }
}
