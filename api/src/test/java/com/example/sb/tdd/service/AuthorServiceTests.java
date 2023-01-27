package com.example.sb.tdd.service;

import com.example.sb.tdd.model.Author;
import com.example.sb.tdd.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTests {

    @Mock
    private AuthorRepository repository;

    @InjectMocks
    private AuthorService service;

    @Test
    public void testGetAuthors() {
        when(repository.findAll()).thenReturn(List.of(Author.builder().id(1).name("n1").build()));

        Author author = service.getAuthors().get(0);
        assertThat(author.getId()).isEqualTo(1);
        assertThat(author.getName()).isEqualTo("n1");
    }

    @Test
    public void testGetAuthor() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(Author.builder().id(1).name("n1").build()));

        Author author = service.getAuthor(1);
        assertThat(author.getId()).isEqualTo(1);
        assertThat(author.getName()).isEqualTo("n1");
    }

    @Test
    public void testPostAuthor() {
        when(repository.save(any(Author.class))).thenReturn(Author.builder().id(1).name("n1").build());

        Author author = service.createAuthor(Author.builder().id(1).name("n1").build());
        assertThat(author.getId()).isEqualTo(1);
        assertThat(author.getName()).isEqualTo("n1");
    }

    @Test
    public void testUpdateAuthor() {
        when(repository.findById(anyInt())).thenReturn(Optional.ofNullable(Author.builder().id(1).name("n1").build()));
        when(repository.save(any(Author.class))).thenReturn(Author.builder().id(1).name("n11").build());

        Author author = service.updateAuthor(1, Author.builder().name("n11").build());
        assertThat(author.getId()).isEqualTo(1);
        assertThat(author.getName()).isEqualTo("n11");
    }

    @Test
    public void testDeleteAuthor() {
        doNothing().when(repository).deleteById(anyInt());

        service.deleteAuthor(1);
        verify(repository).deleteById(1);
    }

}
