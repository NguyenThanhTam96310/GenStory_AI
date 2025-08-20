package com.ebizworld.genstory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebizworld.genstory.entity.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, String> {
    boolean existsByTitle(String title);

    boolean existsByChapterNumber(int chapterNumber);
}
