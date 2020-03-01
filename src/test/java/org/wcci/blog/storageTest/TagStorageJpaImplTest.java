package org.wcci.blog.storageTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Post;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storage.Repositories.TagRepo;
import org.wcci.blog.storage.TagStorage;
import org.wcci.blog.storage.TagStorageJpaImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class TagStorageJpaImplTest {
    private TagRepo tagRepo;
    private TagStorage underTest;
    private Tag testTag;

    @BeforeEach
    void setUp() {
        tagRepo = mock(TagRepo.class);
        underTest = new TagStorageJpaImpl(tagRepo);
        Author testAuthor = new Author("user");
        Category testCategory = new Category("taco");
        Post testPost = new Post("test", "test");
        testTag = new Tag("#tasty", testPost);
    }
    @Test
    public void shouldFindTagByName() {
        when(tagRepo.findByName("#tasty")).thenReturn(Optional.of(testTag));
        Tag retrievedTag = underTest.findTagByName("#tasty");
        assertThat(retrievedTag).isEqualTo(testTag);
    }
    @Test
    public void shouldFindTagById() {
        when(tagRepo.findById(1L)).thenReturn(Optional.of(testTag));
        Tag retrievedTag = underTest.findTagById(1L);
        assertThat(retrievedTag).isEqualTo(testTag);
    }
}