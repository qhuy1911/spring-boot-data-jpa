package com.example.spring.datajpa.springbootdatajpa.controller;

import com.example.spring.datajpa.springbootdatajpa.model.Comment;
import com.example.spring.datajpa.springbootdatajpa.repository.CommentRepository;
import com.example.spring.datajpa.springbootdatajpa.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByTutorialId(@PathVariable("tutorialId") long tutorialId) throws Exception {
        if (!tutorialRepository.existsById(tutorialId)) {
            throw new Exception("Not found Tutorial with id = " + tutorialId);
        }
        List<Comment> comments = commentRepository.findByTutorialId(tutorialId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            return new ResponseEntity<>(comment.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable("tutorialId") long tutorialId,@RequestBody Comment comment) throws Exception {
        Comment _comment = tutorialRepository.findById(tutorialId).map(tutorial -> {
            comment.setTutorial(tutorial);
            return commentRepository.save(comment);
        }).orElseThrow(() ->  new Exception("Not found Tutorial with id = " + tutorialId));
        return new ResponseEntity<>(_comment, HttpStatus.CREATED);
    }
}
