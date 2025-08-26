package com.ebizworld.genstory.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ebizworld.genstory.dto.request.ChatRequest;
import com.ebizworld.genstory.dto.response.ApiResponse;
import com.ebizworld.genstory.dto.response.StoryResponse;
import com.ebizworld.genstory.service.ChatServive;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor // Tạo constructor tự động với tất cả các trường thay cho @Autowired
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatController {
    ChatServive chatServive;

    @PostMapping("/chat")
    ApiResponse<StoryResponse> chat(@RequestBody @Valid ChatRequest request) {
        ApiResponse<StoryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(chatServive.chat(request));
        return apiResponse;
    }

    @PostMapping("/chat-with-image")
    String chatWithImage(@RequestParam("file") MultipartFile file, @RequestParam("message") String message) {
        return chatServive.chatWithImage(file, message);
    }
}
