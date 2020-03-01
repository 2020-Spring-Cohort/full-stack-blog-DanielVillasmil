package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.models.Category;
import org.wcci.blog.storage.Repositories.CategoryRepository;

import java.util.Collection;

    @Service
    public class CategoriesStorageJpaImpl implements CategoryStorage {

        CategoryRepository categoryRepository;

        public CategoriesStorageJpaImpl(CategoryRepository categoryRepository) {
            this.categoryRepository = categoryRepository;
        }

        @Override
        public Collection<Category> getAll(){
            return (Collection<Category>) categoryRepository.findAll();
        }

        @Override
        public Category findCategoryById(long id) {
            return categoryRepository.findById(id).get();
        }

        @Override
        public void store(Category category) {
            categoryRepository.save(category);
        }

        @Override
        public Category findCategoryByName(String categoryName){
            return categoryRepository.findCategoryByName(categoryName).get();
        }
    }
