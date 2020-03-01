package org.wcci.blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Post;
import org.wcci.blog.storage.AuthorsStorageJpaImpl;
import org.wcci.blog.storage.CategoriesStorageJpaImpl;
import org.wcci.blog.storage.PostStorageJpaImpl;


@Component
public class Populator implements CommandLineRunner {

    private final CategoriesStorageJpaImpl categoriesStorageJpa;
    private final AuthorsStorageJpaImpl authorsStorageJpa;
    private final PostStorageJpaImpl postStorageJpa;

    public Populator(CategoriesStorageJpaImpl categoriesStorageJpa, AuthorsStorageJpaImpl authorsStorageJpa, PostStorageJpaImpl postStorageJpa){
        this.categoriesStorageJpa = categoriesStorageJpa;
        this.authorsStorageJpa = authorsStorageJpa;
        this.postStorageJpa = postStorageJpa;
    }


    @Override
    public void run(String... args) {

        Category hardShell = new Category("Hard Shell Tacos");
        categoriesStorageJpa.store(hardShell);
        Category softShell = new Category("Soft Shell Tacos");
        categoriesStorageJpa.store(softShell);

        Author ben = new Author("Ben Williams");
        authorsStorageJpa.store(ben);
        Author kenobi = new Author("Obi Wan Kenobi");
        authorsStorageJpa.store(kenobi);
        Author palpatine = new Author("Palpatine, The Emperor");
        authorsStorageJpa.store(palpatine);

        Post carneAsada = new Post("Carne Asada", "This is awesome!");
        postStorageJpa.store(carneAsada);
        Post beefTongue = new Post("Beef Tongue", "I absolutely love taking this from Anakin");
        postStorageJpa.store(beefTongue);
        Post barbacoa = new Post("Barbacoa", "I love me some barbacoa tacos");
        postStorageJpa.store(barbacoa);
    }
}
