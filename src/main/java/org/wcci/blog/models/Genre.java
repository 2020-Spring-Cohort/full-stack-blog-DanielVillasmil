package org.wcci.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Genre {

    @Id
    @GeneratedValue
    private String name;
    private Long id;

    @OneToMany(mappedBy = "genre")
    private Collection<Post> posts;

    public Genre(String name){
        this.name = name;
    }

    public Genre(){}

    public String getName() {
        return name;
    }
    public Long getId() {
        return id;
    }
}
