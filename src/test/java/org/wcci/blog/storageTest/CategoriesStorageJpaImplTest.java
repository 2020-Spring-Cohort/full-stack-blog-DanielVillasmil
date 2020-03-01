package org.wcci.blog.storageTest;

import org.junit.jupiter.api.Test;
import org.wcci.blog.models.Category;
import org.wcci.blog.storage.CategoriesStorageJpaImpl;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.Repositories.CategoryRepository;


import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CategoriesStorageJpaImplTest {

    @Test
    public void shouldStoreCategory(){
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        CategoryStorage underTest = new CategoriesStorageJpaImpl(categoryRepository);
        Category testCategory = new Category("HardShell");
        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(testCategory));
        underTest.store(testCategory);
        verify(categoryRepository).save(testCategory);
        assertThat(underTest.getAll()).contains(testCategory);
    }
    @Test
    public void shouldRetrieveSinglePostByName() {
        CategoryRepository mockRepo = mock(CategoryRepository.class);
        Category testCategory1 = new Category("water");
        Category testCategory2 = new Category("soda");
        CategoryStorage underTest = new CategoriesStorageJpaImpl(mockRepo);
        underTest.store(testCategory1);
        underTest.store(testCategory2);
        Optional<Category> testCategory1Optional = Optional.of(testCategory1);
        when(mockRepo.findCategoryByName("SoftShell")).thenReturn(testCategory1Optional);

        Optional<Category> testCategory2Optional = Optional.of(testCategory2);
        when(mockRepo.findCategoryByName("HardShell")).thenReturn(testCategory2Optional);

        Category retrievedCategory1 = underTest.findCategoryByName("SoftShell");
        Category retrievedCategory2 = underTest.findCategoryByName("HardShell");
        assertThat(retrievedCategory1).isEqualTo(testCategory1);
        assertThat(retrievedCategory2).isEqualTo(testCategory2);
    }
}
