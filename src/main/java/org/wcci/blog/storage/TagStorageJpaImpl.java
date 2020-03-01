package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storage.Repositories.TagRepo;

import java.util.Collection;

@Service
public class TagStorageJpaImpl implements TagStorage {

    private TagRepo tagRepo;

    public TagStorageJpaImpl(TagRepo tagRepo) {
        this.tagRepo = tagRepo;
    }
    @Override
    public Collection<Tag> getAll(){
        return (Collection<Tag>) tagRepo.findAll();
    }

    @Override
    public void add(Tag tag){
        tagRepo.save(tag);
    }

    @Override
    public Tag findTagByName(String name){
        return tagRepo.findByName(name).get();
    }
    @Override
    public Tag findTagById(long id) {
        return tagRepo.findById(id).get();
    }
}
