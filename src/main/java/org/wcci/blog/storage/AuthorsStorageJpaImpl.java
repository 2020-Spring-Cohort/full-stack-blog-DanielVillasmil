package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.models.Author;
import org.wcci.blog.storage.Repositories.AuthorRepo;

import java.util.Collection;

@Service
public class AuthorsStorageJpaImpl implements AuthorStorage {
    AuthorRepo authorRepo;

    public AuthorsStorageJpaImpl(AuthorRepo authorRepo){
        this.authorRepo = authorRepo;
    }

    @Override
    public void store(Author authorToStore) {
        authorRepo.save(authorToStore);

    }
    @Override
    public Collection<Author> getAll(){
        return (Collection<Author>) authorRepo.findAll();
    }

    @Override
    public Author findAuthorById(long id) {
        return authorRepo.findById(id).get();
    }

    @Override
    public Author findAuthorByName(String name){
        return authorRepo.findByName(name).get();
    }
}
