package com.ebizworld.genstory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebizworld.genstory.entity.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, String> {
    boolean existsByHashContent(String hashContent);
}
