package com.example.spring.datajpa.springbootdatajpa.repository;

import com.example.spring.datajpa.springbootdatajpa.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findTagsByTutorialsId(Long tutorialId);
}
