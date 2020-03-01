package org.wcci.blog.storage.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.models.Post;

import java.util.Optional;

public interface PostRepo extends CrudRepository<Post, Long> {

    Post findByTitle(String title);

    Optional<Post> findPostById(Long id);
}
