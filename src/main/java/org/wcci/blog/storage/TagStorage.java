package org.wcci.blog.storage;


import org.wcci.blog.models.Tag;
import java.util.Collection;

public interface TagStorage {


    Collection<Tag> getAll();

    Tag findTagById(long tagId);
    Tag findTagByName(String name);

    void add(Tag tag);
}
