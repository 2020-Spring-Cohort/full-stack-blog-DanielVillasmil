package org.wcci.blog.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private String name;
    private Long id;

    @OneToMany(mappedBy = "category")
    private Collection<Post> posts;


    public Category(String name){
        this.name = name;
    }

    public Category(){}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (name != null ? !name.equals(category.name) : category.name != null)
            return false;
        return id != null ? id.equals(category.id) : category.id == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}

