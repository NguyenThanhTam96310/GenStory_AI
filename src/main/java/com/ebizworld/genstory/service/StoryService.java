package com.ebizworld.genstory.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.ebizworld.genstory.dto.request.StoryCreationRequest;
import com.ebizworld.genstory.dto.request.StoryUpdateRequest;
import com.ebizworld.genstory.dto.response.StoryResponse;
import com.ebizworld.genstory.entity.Chapter;
import com.ebizworld.genstory.entity.Story;
import com.ebizworld.genstory.exception.AppException;
import com.ebizworld.genstory.exception.ErrorCode;
import com.ebizworld.genstory.mapper.ChapterMapper;
import com.ebizworld.genstory.mapper.StoryMapper;
import com.ebizworld.genstory.repository.StoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.AccessLevel;

@Service
@Slf4j
@RequiredArgsConstructor // Tạo constructor tự động với tất cả các trường thay cho @Autowired
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // gán mặc định cho các trường là final và private
public class StoryService {
    StoryRepository storyRepository;
    StoryMapper storyMapper;
    ChapterMapper chapterMapper;

    public StoryResponse createStory(StoryCreationRequest request) {

        if (storyRepository.existsByHashContent(request.getHashContent()))
            throw new AppException(ErrorCode.STORY_ALREADY_EXISTS);
        Story story = storyMapper.toStory(request);
        // Xử lý danh sách chương
        if (request.getChapters() != null && !request.getChapters().isEmpty()) {
            List<Chapter> chapters = chapterMapper.toChapterList(request.getChapters());
            chapters.forEach(chapter -> chapter.setStory(story));
            story.setChapters(chapters);
        }

        // Lưu câu chuyện vào cơ sở dữ liệu
        Story savedStory = storyRepository.save(story);
        return storyMapper.toStoryResponse(savedStory);
    }

    public StoryResponse updateStory(String id, StoryUpdateRequest request) {
        Story story = storyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.STORY_NOT_FOUND));
        storyMapper.updateStory(story, request);
        return storyMapper.toStoryResponse(storyRepository.save(story));
    }

    public StoryResponse getStoryById(String id) {
        return storyMapper.toStoryResponse(storyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.STORY_NOT_FOUND)));
    }

    public List<StoryResponse> getAllStory() {
        return storyMapper.toStoryResponse(storyRepository.findAll());
    }

    public void deleteStoryById(String id) {
        storyRepository.deleteById(id);
    }
}
