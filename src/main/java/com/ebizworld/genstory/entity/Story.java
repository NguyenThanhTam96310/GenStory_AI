package com.ebizworld.genstory.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "stories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String title;
    String genre;
    int chapterLength;
    String description;
    int numberOfChapters;
    int readerAge;
    String hashContent;

    @Column(name = "create_at", updatable = false)
    LocalDate createAt;

    @OneToMany(
            mappedBy = "story",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    List<Chapter> chapters;
}
