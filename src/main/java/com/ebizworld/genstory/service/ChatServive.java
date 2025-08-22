package com.ebizworld.genstory.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ebizworld.genstory.dto.request.ChatRequest;
import com.ebizworld.genstory.dto.request.StoryCreationRequest;
import com.ebizworld.genstory.dto.response.StoryResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChatServive {

        private final ChatClient chatClient;

        @Autowired
        private StoryService storyService;

        public ChatServive(ChatClient.Builder builder) {
                chatClient = builder.build();
        }

        public StoryResponse chat(ChatRequest request) {
                // setting cho hệ thống
                SystemMessage systemMessage = new SystemMessage("""
                                  You are Genstory.Ai
                                  You should respond like a professional writer.
                                """);
                // dl người dùng gửi
                UserMessage userMessage = new UserMessage(request.message());

                Prompt prompt = new Prompt(systemMessage, userMessage);

                StoryCreationRequest storyrequest = chatClient
                                .prompt(prompt)
                                .call()
                                .entity(new ParameterizedTypeReference<StoryCreationRequest>() {
                                });

                // Gọi hàm tạo và lưu vào DB
                return storyService.createStory(storyrequest);
        }

        public String chatWithImage(MultipartFile file, String message) {
                Media media = Media.builder()
                                .mimeType(MimeTypeUtils.parseMimeType(file.getContentType()))
                                .data(file.getResource())
                                .build();

                ChatOptions chatOptions = ChatOptions.builder()
                                .temperature(1D)
                                .build();

                return chatClient.prompt()
                                .options(chatOptions)
                                .system("You are Genstory.Ai")
                                .user(promptUserspec -> promptUserspec.media(media)// nhận file
                                                .text(message))// nhận text
                                .call()
                                .content();
        }

}
