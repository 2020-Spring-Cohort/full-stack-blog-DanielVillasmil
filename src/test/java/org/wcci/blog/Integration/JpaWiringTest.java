package org.wcci.blog.Integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Post;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storage.Repositories.AuthorRepo;
import org.wcci.blog.storage.Repositories.CategoryRepository;
import org.wcci.blog.storage.Repositories.PostRepo;
import org.wcci.blog.storage.Repositories.TagRepo;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext
@DataJpaTest
public class JpaWiringTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private TagRepo tagRepo;
    @Autowired
    private TestEntityManager entityManager;



    @Test
    public void authorShouldListOfAllPosts(){
        Author testAuthor = new Author("Name");
        Category testCategory = new Category("softShell");
        Post testPost = new Post(testAuthor, testCategory,"Barbacoa", "This is nasty!");

        authorRepo.save(testAuthor);
        categoryRepository.save(testCategory);
        postRepo.save(testPost);

        entityManager.flush();
        entityManager.clear();

        Optional<Author> retrievedAuthorsOptional = authorRepo.findById(testAuthor.getId());
        Author retrievedAuthor = retrievedAuthorsOptional.get();
        Post retrievePost = postRepo.findPostById(testPost.getId()).get();

        assertThat(retrievedAuthor.getPosts()).contains(testPost);

    }

    @Test
    public void categoryShouldHaveListOfPosts(){
        Category testCategory = new Category("HardShell");
        Author testAuthor = new Author("Obi Wan Kenobi");
        Post testPost = new Post(testAuthor, testCategory, "title", "body");

        authorRepo.save(testAuthor);
        categoryRepository.save(testCategory);
        postRepo.save(testPost);

        entityManager.flush();
        entityManager.clear();

        Optional<Author> retrievedAuthorOptional = authorRepo.findById(testAuthor.getId());
        Optional<Category> retrievedCategoryOptional = categoryRepository.findById(testCategory.getId());
        Author retrievedAuthor = retrievedAuthorOptional.get();
        Category retrievedCategory = retrievedCategoryOptional.get();
        Post retrievedPost = postRepo.findById(testPost.getId()).get();
        assertThat(retrievedCategory.getPosts()).contains(testPost);
    }
    @Test
    public void tagsShouldBeAbleToHaveMultiplePosts() {
        Author testAuthor = new Author("user");
        Category testCategory = new Category("water");
        Post testPost1 = new Post(testAuthor, testCategory, "postTitle1", "body");
        Post testPost2 = new Post(testAuthor, testCategory, "postTitle2", "body");
        Post testPost3 = new Post(testAuthor, testCategory, "postTitle3", "body");

        Tag testTag1 = new Tag("#fresh", testPost1, testPost2);
        Tag testTag2 = new Tag("#cool", testPost1, testPost3);

        authorRepo.save(testAuthor);
        categoryRepository.save(testCategory);
        postRepo.save(testPost1);
        postRepo.save(testPost2);
        postRepo.save(testPost3);

        tagRepo.save(testTag1);
        tagRepo.save(testTag2);


        entityManager.flush();
        entityManager.clear();

        Post retrievedPost = postRepo.findById(testPost1.getId()).get();
        Tag retrievedTag1 = tagRepo.findById(testTag1.getId()).get();
        Tag retrievedTag2 = tagRepo.findById(testTag2.getId()).get();
        assertThat(retrievedPost.getTag()).contains(testTag1, testTag2);
        assertThat(retrievedTag1.getPosts()).contains(testPost1, testPost2);
        assertThat(retrievedTag2.getPosts()).contains(testPost1, testPost3);
    }
}
