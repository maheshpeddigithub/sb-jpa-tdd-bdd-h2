package com.example.sb.tdd.controller;

import com.example.sb.tdd.model.Author;
import com.example.sb.tdd.service.AuthorService;
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

    private AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAuthors() {
        return new ResponseEntity(service.getAuthors(), HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable("id") Integer id) {
        return new ResponseEntity(service.getAuthor(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        return new ResponseEntity(service.createAuthor(author), HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable("id") Integer id, @RequestBody Author author) {
        return new ResponseEntity(service.updateAuthor(id, author), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") Integer id) {
        service.deleteAuthor(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
