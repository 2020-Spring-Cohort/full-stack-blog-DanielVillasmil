package org.wcci.blog.controllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.controller.PostController;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Post;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.PostStorage;
import org.wcci.blog.storage.TagStorage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PostControllerTest {

    private PostController underTest;
    private Model model;
    private AuthorStorage mockAuthorStorage;
    private CategoryStorage mockCategoryStorage;
    private TagStorage mockTagStorage;
    private PostStorage mockPostStorage;
    private Post testPost;
    private MockMvc mockMvc;
    private Author testAuthor;
    private Category testCategory;

    @BeforeEach
    void setUp(){
        mockPostStorage = mock(PostStorage.class);
        mockCategoryStorage = mock(CategoryStorage.class);
        mockTagStorage = mock(TagStorage.class);
        mockAuthorStorage = mock(AuthorStorage.class);
        underTest = new PostController(mockPostStorage, mockAuthorStorage, mockCategoryStorage, mockTagStorage);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        model = mock(Model.class);
        Category testCategory = new Category("TacoBarbacoa");
        Author testAuthor = new Author("Nancy Testa");
        testPost = new Post("","");
        when(mockPostStorage.findPostById(1L)).thenReturn(testPost);
    }

    @Test
    public void displayPostReturnPostTemplate(){
        String result = underTest.displayPost(1L, model);
        assertThat(result).isEqualTo("postPage");
    }

    @Test
    public void displayPostMappingIsCorrect() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/posts/single-post/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("postPage"))
                .andExpect(model().attributeExists("post"))
                .andExpect(model().attribute("post", testPost));

    }
    @Test
    public void postInteractsWithDependenciesCorrectly(){
        underTest.displayPostFromAuthorPage((long) 1, model);
        verify(mockPostStorage).findPostById(1L);
        verify(model).addAttribute("post", testPost);
    }
    @Test
    public void addPostShouldRedirect() throws Exception{
        mockMvc.perform(post("/post/add")
                .param("author", "user")
                .param("category", "softShell")
                .param("postTitle", "Barbacoa")
                .param("postBody", "i love this"))
                .andExpect(status().is3xxRedirection());
        verify(mockPostStorage).store(new Post("user","softShell", "Barbacoa", "i love this"));
    }
}
