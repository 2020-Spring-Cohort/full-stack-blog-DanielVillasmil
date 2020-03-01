package org.wcci.blog.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    private String body;
    private String title;
    @ManyToOne
    private Author author;
    private String datePublished;
    @ManyToOne
    private Category category;
    @ManyToMany(mappedBy = "posts")
    private Collection<Tag> tag;

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
        this.datePublished = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM dd yyyy h:mm a"));
    }

    public Post(){
    }

    public Post(Author author, Category category, String title, String body, Tag... tag){
        this.author = author;
        this.category = category;
        this.title = title;
        this.body = body;
        this.tag = Arrays.asList(tag);
    }
    public Post(Author author, Category category, String title, String body) {
        this.author = author;
        this.category = category;
        this.title = title;
        this.body = body;
    }
    public Post(String author, String category, String postTitle, String postBody) {
        this.author = new Author(author);
        this.category = new Category(category);
        this.title = postTitle;
        this.body = postBody;
    }

    public Long getId() {
        return id;
    }



    public String getBody() {
        return body;
    }



    public String getTitle() {
        return title;
    }



    public Author getAuthor() {
        return author;
    }



    public String getDatePublished() {
        return datePublished;
    }



    public Category getCategory() {
        return category;
    }



    public Collection<Tag> getTag() {
        return tag;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != null ? !id.equals(post.id) : post.id != null) return false;
        if (body != null ? !body.equals(post.body) : post.body != null) return false;
        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        if (author != null ? !author.equals(post.author) : post.author != null) return false;
        if (datePublished != null ? !datePublished.equals(post.datePublished) : post.datePublished != null)
            return false;
        return category != null ? category.equals(post.category) : post.category == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (datePublished != null ? datePublished.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
