package com.example.spring.datajpa.springbootdatajpa.repository;

import com.example.spring.datajpa.springbootdatajpa.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByPublished(boolean published);

    @Query(value = "SELECT * FROM Tutorials where title like %:title%",nativeQuery = true)
    List<Tutorial> findByTitleContaining(String title);
}
