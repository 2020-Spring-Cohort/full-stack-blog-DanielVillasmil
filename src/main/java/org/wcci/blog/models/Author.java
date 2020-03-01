package org.wcci.blog.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(mappedBy = "author")
    private Collection<Post> posts;

    private String name;


    public Author(){}

    public Author(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId(){
        return id;
    }

    public Collection<Post> getPosts(){
        return posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (name != null ? !name.equals(author.name) : author.name != null) return false;
        return id != null ? id.equals(author.id) : author.id == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Authors{" +
                "fullName='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
