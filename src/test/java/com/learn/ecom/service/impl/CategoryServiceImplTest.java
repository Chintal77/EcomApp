package com.learn.ecom.service.impl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.learn.ecom.model.Category;
import com.learn.ecom.repository.CategoryRepository;

public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCategory() {
        Category category = new Category();
        category.setId(1);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category savedCategory = categoryService.saveCategory(category);
        assertNotNull(savedCategory);
        assertEquals(1, savedCategory.getId());
    }

    @Test
    public void testGetAllCategories() {
        Category category1 = new Category();
        Category category2 = new Category();
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));

        List<Category> categories = categoryService.getAllCategory();
        assertEquals(2, categories.size());
    }

    @Test
    public void testDeleteCategory() {
        Category category = new Category();
        category.setId(1);
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));

        Boolean isDeleted = categoryService.deleteCategory(1);
        assertTrue(isDeleted);
        verify(categoryRepository, times(1)).delete(category);
    }

    @Test
    public void testGetCategoryById() {
        Category category = new Category();
        category.setId(1);
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));

        Category foundCategory = categoryService.getCategoryById(1);
        assertNotNull(foundCategory);
        assertEquals(1, foundCategory.getId());
    }
}
