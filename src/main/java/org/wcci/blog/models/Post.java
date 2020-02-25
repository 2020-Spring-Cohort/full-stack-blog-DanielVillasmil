package org.wcci.blog.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    private String title;;
    private String body;
    @ManyToOne
    private Authors authors;
    private String datePublished;
    @ManyToOne
    private Genre genre;
    @ManyToMany(mappedBy = "posts")
    private Collection<Tag> tags;

    public Post(String title, String body, String datePublished) {
        this.title = title;
        this.body = body;
        this.datePublished = datePublished;
    }


    public Post(){}


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Authors getAuthors() {
        return authors;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public Genre getGenre() {
        return genre;
    }
}
