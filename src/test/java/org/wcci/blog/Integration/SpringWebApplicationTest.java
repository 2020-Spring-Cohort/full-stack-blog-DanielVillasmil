package org.wcci.blog.Integration;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.wcci.blog.models.Category;
import org.wcci.blog.storage.CategoryStorage;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext
@SpringBootTest
@AutoConfigureMockMvc

public class SpringWebApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReceiveOkFromCategoryEndPoint() throws Exception {
        mockMvc.perform(get("/category/all-categories"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void shouldReceiveOkFromAuthorsEndPoint() throws Exception{
        mockMvc.perform(get("/authors/all-authors"))
                .andDo(print()).andExpect(status().isOk());
    }
    @Test
    public void shouldReceiveOkFromPostsEndPoint() throws Exception {
        mockMvc.perform(get("/posts/all-posts"))
                .andDo(print()).andExpect(status().isOk());
    }
    @Test
    public void shouldReceiveOkFromTagEndPoint() throws Exception {
        mockMvc.perform(get("/tag/all-tags"))
                .andDo(print()).andExpect(status().isOk());
    }
}
