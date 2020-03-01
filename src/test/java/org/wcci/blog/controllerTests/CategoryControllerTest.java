package org.wcci.blog.controllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.controller.CategoryController;
import org.wcci.blog.models.Category;
import org.wcci.blog.storage.CategoryStorage;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CategoryControllerTest {
    private MockMvc mockMvc;
    private CategoryController underTest;
    private CategoryStorage mockStorage;
    private Model mockModel;

    @BeforeEach
    public void setUp(){
        mockModel = mock(Model.class);
        mockStorage = mock(CategoryStorage.class);
        underTest = new CategoryController(mockStorage);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    public void shouldGoToIndividualEndPoint() throws Exception {
        Category testCategory = new Category("testTacos");
        when(mockStorage.findCategoryById(1)).thenReturn(testCategory);
        mockMvc.perform(get("/category/1/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("category"))
                .andExpect(model().attributeExists("category"))
                .andExpect(model().attribute("category", testCategory));
    }
    @Test
    public void shouldReturnOneCategory(){
        Category testCategory = new Category("testTacos");
        when(mockStorage.findCategoryById(1)).thenReturn(testCategory);
        underTest.displaySingleCategoryFromPage(1,mockModel);
        verify(mockStorage).findCategoryById(1);
        verify(mockModel).addAttribute("category", testCategory);
    }
    @Test
    public void endPointShouldDisplayAllCategories() throws Exception{
        Category testCategory = new Category("testTacos");

        List<Category> categoryList = Collections.singletonList(testCategory);
        when(mockStorage.getAll()).thenReturn(categoryList);
        mockMvc.perform(get("/category/all-categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("categories"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("categories", categoryList));
    }
    @Test
    public void addCategoryShouldRedirect() throws Exception{
        mockMvc.perform(post("/category/add/").param("name", "testTacos"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        verify(mockStorage).store(new Category("testTacos"));
    }

}
