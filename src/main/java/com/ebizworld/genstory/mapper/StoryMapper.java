package com.ebizworld.genstory.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ebizworld.genstory.dto.reponse.StoryResponse;
import com.ebizworld.genstory.dto.request.StoryCreationRequest;
import com.ebizworld.genstory.dto.request.StoryUpdateRequest;
import com.ebizworld.genstory.entity.Story;

@Mapper(componentModel = "spring", uses = { ChapterMapper.class })
public interface StoryMapper {
    @Mapping(target = "chapters", ignore = true)
    Story toStory(StoryCreationRequest request);

    StoryResponse toStoryResponse(Story story);

    List<StoryResponse> toStoryResponse(List<Story> stories);

    void updateStory(@MappingTarget Story story, StoryUpdateRequest request);
}
