package com.example.sb.tdd.service;

import com.example.sb.tdd.model.Author;
import com.example.sb.tdd.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<Author> getAuthors() {
        return repository.findAll();
    }

    public Author getAuthor(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Author createAuthor(Author author) {
        return repository.save(author);
    }

    public Author updateAuthor(Integer id, Author author) {
        Optional<Author> retrievedAuthor = repository.findById(id);
        retrievedAuthor.ifPresent( authorToUpdate -> {
            authorToUpdate.setName(author.getName());
            repository.save(authorToUpdate);
        });
        return retrievedAuthor.orElse(null);
    }

    public void deleteAuthor(Integer id) {
        repository.deleteById(id);
    }

}
