package org.wcci.blog.Integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.PostStorage;
import org.wcci.blog.storage.TagStorage;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DirtiesContext
@ExtendWith(SpringExtension.class)
@WebMvcTest
public class WebLayerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CategoryStorage mockCategoryStorage;
    @MockBean
    AuthorStorage mockAuthorStorage;
    @MockBean
    TagStorage mockTagStorage;
    @MockBean
    PostStorage mockPostStorage;

    @Test
    public void categoriesShouldBeOkAndReturnCategoryWithAllAttributes() throws Exception {
       mockMvc.perform(get("/category/all-categories"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(view().name("categories"))
               .andExpect(model().attributeExists("categories"));
    }
    @Test
    public void authorShouldBeOkAndReturnAuthorWithAllAttributes() throws Exception {
        mockMvc.perform(get("/author/all-authors"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("authors"))
                .andExpect(model().attributeExists("authors"));
    }
    @Test void postShouldBeOkAndReturnPostWithAllAttributes() throws Exception{
        mockMvc.perform(get("/post/all-posts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("posts"))
                .andExpect(model().attributeExists("posts"));
    }
    @Test void tagsShouldBeOkAndReturnTagsWithAllAttributes() throws Exception{
        mockMvc.perform(get("/tag/all-tags"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("tags"))
                .andExpect(model().attributeExists("tags"));
    }


}
