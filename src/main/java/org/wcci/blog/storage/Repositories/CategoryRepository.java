package org.wcci.blog.storage.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.models.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findCategoryByName(String categoryName);
}
