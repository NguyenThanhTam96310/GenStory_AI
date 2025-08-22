package com.ebizworld.genstory.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ebizworld.genstory.dto.request.ChapterCreationRequest;
import com.ebizworld.genstory.dto.request.ChapterUpdateRequest;
import com.ebizworld.genstory.dto.response.ChapterResponse;
import com.ebizworld.genstory.entity.Chapter;

@Mapper(componentModel = "spring")
public interface ChapterMapper {
    @Mapping(target = "story", ignore = true)
    Chapter toChapter(ChapterCreationRequest request);

    List<Chapter> toChapterList(List<ChapterCreationRequest> requests);

    ChapterResponse toChapterResponse(Chapter chapter);

    List<ChapterResponse> toChapterResponse(List<Chapter> chapters);

    void updateChapter(@MappingTarget Chapter chapter, ChapterUpdateRequest request);
}
