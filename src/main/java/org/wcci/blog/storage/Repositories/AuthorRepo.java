package org.wcci.blog.storage.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.models.Author;

import java.util.Optional;

public interface AuthorRepo extends CrudRepository<Author, Long> {

    Optional<Author> findByName(String name);
}
