package com.example.sb.tdd.service;

import com.example.sb.tdd.model.Author;
import com.example.sb.tdd.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private static final Logger log = LoggerFactory.getLogger(AuthorService.class);

    private AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<Author> getAuthors() {
        log.info("getAuthors called");
        return repository.findAll();
    }

    public Author getAuthor(Integer id) {
        log.info("getAuthor called");
        return repository.findById(id).get();
    }

    public Author createAuthor(Author author) {
        log.info("createAuthor called");
        return repository.save(author);
    }

    public Author updateAuthor(Integer id, Author author) {
        log.info("updateAuthor called");
        Optional<Author> retrievedAuthor = repository.findById(id);
        retrievedAuthor.ifPresent( authorToUpdate -> {
            authorToUpdate.setName(author.getName());
            repository.save(authorToUpdate);
        });
        return retrievedAuthor.orElse(null);
    }

    public void deleteAuthor(Integer id) {
        log.info("deleteAuthor called");
        repository.deleteById(id);
    }

}
