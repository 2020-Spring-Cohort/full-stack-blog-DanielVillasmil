package org.wcci.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.Collections;

@Entity
public class Tag {
    @Id
    @GeneratedValue
    private String name;
    private Long id;
    @ManyToMany
    private Collection<Post> posts;


    public Tag(String name, Post posts){
        this.name = name;
        this.posts = Collections.singletonList(posts);
    }

    public Tag(){}

    public Collection<Post> getPosts(){
        return posts;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
