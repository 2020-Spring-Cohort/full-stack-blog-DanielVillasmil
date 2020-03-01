package org.wcci.blog.storageTest;

import org.junit.jupiter.api.Test;
import org.wcci.blog.models.Author;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.AuthorStorageJpaImpl;
import org.wcci.blog.storage.Repositories.AuthorRepo;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class AuthorsStorageJpaImplTest {
    @Test
    public void shouldStoreAuthor(){
        AuthorRepo authorRepo = mock(AuthorRepo.class);
        AuthorStorage underTest = new AuthorStorageJpaImpl(authorRepo);
        Author testAuthor = new Author("test");
        when(authorRepo.findAll()).thenReturn(Collections.singleton(testAuthor));
        underTest.store(testAuthor);
        verify(authorRepo).save(testAuthor);
        assertThat(underTest.getAll()).contains(testAuthor);
        }
    @Test
    public void shouldRetrieveSingleAuthorByName() {
        AuthorRepo mockRepo = mock(AuthorRepo.class);
        Author testAuthor1 = new Author("test");
        Author testAuthor2 = new Author("test2");
        AuthorStorage underTest = new AuthorStorageJpaImpl(mockRepo);
        underTest.store(testAuthor1);
        underTest.store(testAuthor2);
        Optional<Author> testAuthor1Optional = Optional.of(testAuthor1);
        when(mockRepo.findByName("test")).thenReturn(testAuthor1Optional);

        Optional<Author> testAuthor2Optional = Optional.of(testAuthor2);
        when(mockRepo.findByName("test2")).thenReturn(testAuthor2Optional);

        Author retrievedAuthor1 = underTest.findAuthorByName("test");
        Author retrievedAuthor2 = underTest.findAuthorByName("test2");
        assertThat(retrievedAuthor1).isEqualTo(testAuthor1);
        assertThat(retrievedAuthor2).isEqualTo(testAuthor2);
    }
    }

