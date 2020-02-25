package org.wcci.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Authors {
@Id
@GeneratedValue
    private String firstName;
    private String lastName;
    private Long id;
    @OneToMany(mappedBy = "authors")
    private Collection<Post> posts;


    private Authors(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Collection<Post>getPosts(){
        return posts;
    }

    private Authors(){}


    public String getName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getId() {
        return id;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
