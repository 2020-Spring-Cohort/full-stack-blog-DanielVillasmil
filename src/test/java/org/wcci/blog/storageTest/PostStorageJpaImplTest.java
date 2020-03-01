package org.wcci.blog.storageTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Post;
import org.wcci.blog.storage.PostStorage;
import org.wcci.blog.storage.PostStorageJpaImpl;
import org.wcci.blog.storage.Repositories.AuthorRepo;
import org.wcci.blog.storage.Repositories.CategoryRepository;
import org.wcci.blog.storage.Repositories.PostRepo;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class PostStorageJpaImplTest {
        private AuthorRepo authorRepo;
        private CategoryRepository categoryRepository;
        private PostRepo postRepo;
        private PostStorage postStorage;
        private Post testPost;

        @BeforeEach
        void setUp() {
            authorRepo = mock(AuthorRepo.class);
            categoryRepository = mock(CategoryRepository.class);
            postRepo = mock(PostRepo.class);
            postStorage = new PostStorageJpaImpl(authorRepo, categoryRepository, postRepo);
            Category testCategory = new Category("taco");
            Author testAuthor = new Author("user");
            testPost = new Post("test", "test");
        }
        @Test
        public void shouldFindPostById() {
            when(postRepo.findById(1L)).thenReturn(Optional.of(testPost));
            Post retrievedPost = (Post) postStorage.findPostById(1L);
            assertThat(retrievedPost).isEqualTo(testPost);
        }
        @Test
        public void shouldStorePost(){
            postStorage.store(testPost);
            verify(authorRepo).save(testPost.getAuthor());
            verify(categoryRepository).save(testPost.getCategory());
            verify(postRepo).save(testPost);
        }
    }
