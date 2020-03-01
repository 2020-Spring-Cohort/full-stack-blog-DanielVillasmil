package org.wcci.blog.storage;

import org.wcci.blog.models.Post;

import java.util.Collection;

public interface PostStorage {
    Post findPostById(long id);
    Post findPostByTitle(String title);

    void store(Post postToStore);
    Collection<Post> getAll();
}
