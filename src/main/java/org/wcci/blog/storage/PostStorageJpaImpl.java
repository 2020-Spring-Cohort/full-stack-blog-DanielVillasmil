package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.models.Post;
import org.wcci.blog.storage.Repositories.AuthorRepo;
import org.wcci.blog.storage.Repositories.CategoryRepository;
import org.wcci.blog.storage.Repositories.PostRepo;

import java.util.Collection;

@Service
public class PostStorageJpaImpl implements PostStorage {
    private PostRepo postRepo;
    private AuthorRepo authorRepo;
    private CategoryRepository categoryRepository;

    public PostStorageJpaImpl(AuthorRepo authorRepo, CategoryRepository categoryRepository, PostRepo postRepo){
        this.authorRepo = authorRepo;
        this.categoryRepository = categoryRepository;
        this.postRepo = postRepo;
    }

    @Override
    public Collection<Post> getAll(){
        return (Collection<Post>) postRepo.findAll();
    }

    @Override
    public Post findPostById(long id) {
        return postRepo.findById(id).get();
    }
    @Override
    public Post findPostByTitle(String title){
        return postRepo.findByTitle(title);
    }

    @Override
    public void store(Post postToStore) {
        authorRepo.save(postToStore.getAuthor());
        categoryRepository.save(postToStore.getCategory());
        postRepo.save(postToStore);

    }
}
