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

    private static final String prompt =
            """
				Tạo một câu truyện dựa trên thông tin sau:
			- Ý tưởng: ${idea}
			- Thể loại: ${genre.join(", ")}
			- Độ dài mỗi chương: khoảng ${length} ký tự
			- Số lượng chương là : ${numberOfChapters} chương
			- Đối tượng: ${getAgeGroupDescription(ageGroup)} tuổi)
			- Ngôn ngữ: Tiếng Việt

			NGHIÊM CẤM các nội dung sau:
			- Bạo lực máu me chi tiết
			- Tình dục, gợi dục, hành vi nhạy cảm
			- Phân biệt chủng tộc, kỳ thị giới tính
			- Ngôn từ tục tĩu, khiêu khích
			- Nội dung chính trị cực đoan

			YÊU CẦU VỀ PHONG CÁCH:
			- Giọng kể kỳ ảo, sâu lắng, giàu cảm xúc
			- Ưu tiên miêu tả không gian và nội tâm nhân vật
			- Hạn chế hội thoại
			- Không cần giải thích logic phép thuật
			- Ngôn ngữ trong sáng, giàu hình ảnh
			- Cấu trúc: mở đầu - diễn biến - cao trào - kết thúc
			TRẢ VỀ:
			title: chỉ trả về tên chương không có số chương trong đó
							""";

    public ChatServive(ChatClient.Builder builder) {
        chatClient = builder.build();
    }

    public StoryResponse chat(ChatRequest request) {

        // setting cho hệ thống
        SystemMessage systemMessage = new SystemMessage(
                """
						You are Genstory.Ai
						You should respond like a professional writer.
						""");
        // Map dữ liệu từ request vào prompt
        String userPrompt = prompt.replace("${idea}", request.getDescription())
                .replace("${genre.join(\", \")}", request.getGenre())
                .replace("${length}", String.valueOf(request.getChapterLength()))
                .replace("${numberOfChapters}", String.valueOf(request.getNumberOfChapters()))
                .replace("${getAgeGroupDescription(ageGroup)}", "Độc giả " + request.getReaderAge() + "+");
        // dl người dùng gửi
        UserMessage userMessage = new UserMessage(userPrompt);

        Prompt prompt = new Prompt(systemMessage, userMessage);

        StoryCreationRequest storyrequest =
                chatClient.prompt(prompt).call().entity(new ParameterizedTypeReference<StoryCreationRequest>() {});

        // Gọi hàm tạo và lưu vào DB
        return storyService.createStory(storyrequest);
    }

    public String chatWithImage(MultipartFile file, String message) {
        Media media = Media.builder()
                .mimeType(MimeTypeUtils.parseMimeType(file.getContentType()))
                .data(file.getResource())
                .build();

        ChatOptions chatOptions = ChatOptions.builder().temperature(1D).build();

        return chatClient
                .prompt()
                .options(chatOptions)
                .system("You are Genstory.Ai")
                .user(promptUserspec -> promptUserspec
                        .media(media) // nhận file
                        .text(message)) // nhận text
                .call()
                .content();
    }
}
