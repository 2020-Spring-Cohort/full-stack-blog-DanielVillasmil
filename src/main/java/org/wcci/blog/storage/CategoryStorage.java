package org.wcci.blog.storage;


import org.wcci.blog.models.Category;

import java.util.Collection;

public interface CategoryStorage {

    void store(Category category);

    Category findCategoryByName(String categoryName);

    Collection<Category> getAll();

    Category findCategoryById(long id);
}
