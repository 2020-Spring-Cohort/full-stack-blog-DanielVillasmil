package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.models.Author;
import org.wcci.blog.storage.Repositories.AuthorRepo;

import java.util.Collection;

@Service
public class AuthorStorageJpaImpl implements AuthorStorage {

    AuthorRepo authorRepo;

    public AuthorStorageJpaImpl(AuthorRepo authorRepo){
        this.authorRepo =authorRepo;
    }

    @Override
    public void store(Author authorToStore) {

    }

    @Override
    public Collection<Author> getAll() {
        return (Collection<Author>) authorRepo.findAll();
    }

    @Override
    public Author findAuthorById(long id) {
        return authorRepo.findById(id).get();
    }

    @Override
    public Author findAuthorByName(String name) {
        return authorRepo.findByName(name).get();
    }




}
