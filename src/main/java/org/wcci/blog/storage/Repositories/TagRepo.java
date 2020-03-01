package org.wcci.blog.storage.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.models.Tag;

import java.util.Optional;

public interface TagRepo extends CrudRepository<Tag, Long> {
    Optional<Tag> findByName(String tagName);
}
