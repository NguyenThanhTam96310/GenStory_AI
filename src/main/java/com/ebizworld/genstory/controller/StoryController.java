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

import com.ebizworld.genstory.dto.request.StoryCreationRequest;
import com.ebizworld.genstory.dto.request.StoryUpdateRequest;
import com.ebizworld.genstory.dto.response.ApiResponse;
import com.ebizworld.genstory.dto.response.StoryResponse;
import com.ebizworld.genstory.service.StoryService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/stories")
@RequiredArgsConstructor // Tạo constructor tự động với tất cả các trường thay cho @Autowired
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // gán mặc định cho các trường là final và private
public class StoryController {
    StoryService storyService;

    @PostMapping
    ApiResponse<StoryResponse> createStory(@RequestBody @Valid StoryCreationRequest request) {
        ApiResponse<StoryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(storyService.createStory(request));
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<StoryResponse>> getAllStory() {
        return ApiResponse.<List<StoryResponse>>builder()
                .result(storyService.getAllStory())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<StoryResponse> getStoryById(@PathVariable("id") String id) {
        return ApiResponse.<StoryResponse>builder()
                .result(storyService.getStoryById(id))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<StoryResponse> updateStory(
            @PathVariable("id") String id, @RequestBody @Valid StoryUpdateRequest request) {
        return ApiResponse.<StoryResponse>builder()
                .result(storyService.updateStory(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteStory(@PathVariable String id) {
        storyService.deleteStoryById(id);
        return ApiResponse.<String>builder()
                .result("Story with id " + id + " deleted successfully")
                .build();
    }
}
