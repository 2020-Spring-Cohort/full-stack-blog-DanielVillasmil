package org.wcci.blog.storageTest;

import org.junit.jupiter.api.Test;
import org.wcci.blog.models.Author;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.AuthorsStorageJpaImpl;
import org.wcci.blog.storage.Repositories.AuthorRepo;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class AuthorsStorageJpaImplTest {
    @Test
    public void shouldStoreAuthor(){
        AuthorRepo authorRepo = mock(AuthorRepo.class);
        AuthorStorage underTest = new AuthorsStorageJpaImpl(authorRepo);
        Author testAuthor = new Author("test test");
        when(authorRepo.findAll()).thenReturn(Collections.singleton(testAuthor));
        underTest.store(testAuthor);
        verify(authorRepo).save(testAuthor);
        assertThat(underTest.getAll()).contains(testAuthor);
        }
    @Test
    public void shouldRetrieveSingleAuthorByName() {
        AuthorRepo mockRepo = mock(AuthorRepo.class);
        Author testAuthor1 = new Author("Kenobi");
        Author testAuthor2 = new Author("Yoda");
        AuthorStorage underTest = new AuthorsStorageJpaImpl(mockRepo);
        underTest.store(testAuthor1);
        underTest.store(testAuthor2);
        Optional<Author> testAuthor1Optional = Optional.of(testAuthor1);
        when(mockRepo.findByName("Kenobi")).thenReturn(testAuthor1Optional);

        Optional<Author> testAuthor2Optional = Optional.of(testAuthor2);
        when(mockRepo.findByName("Yoda")).thenReturn(testAuthor2Optional);

        Author retrievedAuthor1 = underTest.findAuthorByName("Kenobi");
        Author retrievedAuthor2 = underTest.findAuthorByName("Yoda");
        assertThat(retrievedAuthor1).isEqualTo(testAuthor1);
        assertThat(retrievedAuthor2).isEqualTo(testAuthor2);
    }
    }

