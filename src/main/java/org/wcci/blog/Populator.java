package org.wcci.blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Post;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storage.*;


@Component
public class Populator implements CommandLineRunner {

    private AuthorStorage authorStorage;
    private CategoryStorage categoryStorage;

    private PostStorage postStorage;
    private TagStorage tagStorage;

    public Populator (AuthorStorage authorsStorage, CategoryStorage categoryStorage, PostStorage postStorage, TagStorage tagStorage){
        this.authorStorage = authorsStorage;
        this.categoryStorage = categoryStorage;
        this.postStorage = postStorage;
        this.tagStorage = tagStorage;
    }


    @Override
    public void run(String... args) throws Exception {


        Author ben = new Author("Ben Williams");
        authorStorage.store(ben);

        Category hardShell = new Category("Hard Shell Tacos");
        categoryStorage.store(hardShell);

//        Category softShell = new Category("Soft Shell Tacos");
//        categoryStorage.store(softShell);



//        Author kenobi = new Author("Obi Wan Kenobi");
//        authorStorage.store(kenobi);

        Post carneAsada = new Post(ben, hardShell,"Carne Asada", "This is awesome!");
        postStorage.store(carneAsada);

//        Post beefTongue = new Post(kenobi,"Beef Tongue", "I absolutely love taking this from Anakin");
//        postStorage.store(beefTongue);


        Tag tasty = new Tag("Tasty", carneAsada);
        tagStorage.add(tasty);
    }
}
