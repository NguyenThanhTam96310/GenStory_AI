package com.ebizworld.genstory.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebizworld.genstory.dto.request.ChapterCreationRequest;
import com.ebizworld.genstory.dto.request.ChapterUpdateRequest;
import com.ebizworld.genstory.dto.response.ChapterResponse;
import com.ebizworld.genstory.entity.Chapter;
import com.ebizworld.genstory.exception.AppException;
import com.ebizworld.genstory.exception.ErrorCode;
import com.ebizworld.genstory.mapper.ChapterMapper;
import com.ebizworld.genstory.repository.ChapterRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor // Tạo constructor tự động với tất cả các trường thay cho @Autowired
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // gán mặc định cho các trường là final và private
public class ChapterService {
    ChapterRepository chapterRepository;
    ChapterMapper chapterMapper;

    public ChapterResponse createChapter(ChapterCreationRequest request) {

        if (chapterRepository.existsByTitle(request.getTitle()))
            throw new AppException(ErrorCode.CHAPTER_ALREADY_EXISTS);
        if (chapterRepository.existsByChapterNumber(request.getChapterNumber()))
            throw new AppException(ErrorCode.CHAPTER_NUMBER_ALREADY_EXISTS);

        Chapter chapter = chapterMapper.toChapter(request);
        Chapter savChapter = chapterRepository.save(chapter);
        return chapterMapper.toChapterResponse(savChapter);
    }

    public ChapterResponse updateChapter(String id, ChapterUpdateRequest request) {
        Chapter chapter =
                chapterRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CHAPTER_NOT_FOUND));
        if (chapterRepository.existsByTitle(request.getTitle()))
            throw new AppException(ErrorCode.CHAPTER_ALREADY_EXISTS);
        if (chapterRepository.existsByChapterNumber(request.getChapterNumber()))
            throw new AppException(ErrorCode.CHAPTER_NUMBER_ALREADY_EXISTS);
        chapterMapper.updateChapter(chapter, request);
        return chapterMapper.toChapterResponse(chapterRepository.save(chapter));
    }

    public ChapterResponse getChapterById(String id) {
        return chapterMapper.toChapterResponse(
                chapterRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CHAPTER_NOT_FOUND)));
    }

    public List<ChapterResponse> getAllChapter() {
        return chapterMapper.toChapterResponse(chapterRepository.findAll());
    }

    public void deleteChapterById(String id) {
        chapterRepository.deleteById(id);
    }
}
