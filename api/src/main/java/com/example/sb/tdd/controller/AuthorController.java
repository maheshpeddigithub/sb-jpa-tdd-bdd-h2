package com.example.sb.tdd.controller;

import com.example.sb.tdd.model.Author;
import com.example.sb.tdd.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/author")
public class AuthorController {

    private static final Logger log = LoggerFactory.getLogger(AuthorController.class);

    private AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAuthors() {
        log.info("getAuthors called");
        return new ResponseEntity<>(service.getAuthors(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable("id") Integer id) {
        log.info("getAuthor called");
        return new ResponseEntity<>(service.getAuthor(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        log.info("createAuthor called");
        return new ResponseEntity<>(service.createAuthor(author), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<Author> updateAuthorName(@PathVariable("id") Integer id, @RequestBody Author author) {
        log.info("updateAuthorName called");
        return new ResponseEntity<>(service.updateAuthor(id, author), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable("id") Integer id) {
        log.info("deleteAuthor called");
        service.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

}
