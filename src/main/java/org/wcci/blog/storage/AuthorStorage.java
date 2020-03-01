package org.wcci.blog.storage;

import org.wcci.blog.models.Author;

import java.util.Collection;

public interface AuthorStorage {

    void store(Author authorToStore);

    Collection<Author> getAll();

    Author findAuthorById(long authorId);
    Author findAuthorByName(String name);
}
