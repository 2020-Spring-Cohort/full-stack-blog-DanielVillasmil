package org.wcci.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Arrays;
import java.util.Collection;

@Entity
public class Tag {
    @Id
    @GeneratedValue
    private String name;
    private Long id;
    @ManyToMany
    private Collection<Post> posts;


    public Tag(String name, Post... posts){
        this.name = name;
        this.posts = Arrays.asList(posts);
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    public Tag(String name){
        this.name = name;
    }

    public Tag(){}

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (name != null ? !name.equals(tag.name) : tag.name != null) return false;
        return id != null ? id.equals(tag.id) : tag.id == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
