package com.example.utl;

import com.example.entity.CategoryEntity;
import com.example.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testSaveCategory() {
        // Given
        CategoryEntity category = new CategoryEntity();
        category.setName("Test Category");

        // When
        CategoryEntity savedCategory = categoryRepository.save(category);

        // Then
        assertEquals("Test Category", savedCategory.getName());
        assertTrue(savedCategory.getId() > 0);
    }

    @Test
    public void testFindCategoryById() {
        // Given
        CategoryEntity category = new CategoryEntity();
        category.setName("Test Category");
        entityManager.persist(category);

        // When
        Optional<CategoryEntity> foundCategory = categoryRepository.findById(category.getId());

        // Then
        assertTrue(foundCategory.isPresent());
        assertEquals("Test Category", foundCategory.get().getName());
    }

    @Test
    public void testUpdateCategory() {
        // Given
        CategoryEntity category = new CategoryEntity();
        category.setName("Original Category Name");
        entityManager.persist(category);

        // When
        int updatedRows = categoryRepository.update(category.getId(), "Updated Category Name");

        // Then
        assertEquals(1, updatedRows);
        Optional<CategoryEntity> updatedCategory = categoryRepository.findById(category.getId());
        assertTrue(updatedCategory.isPresent());
        assertEquals("Updated Category Name", updatedCategory.get().getName());
    }

    @Test
    public void testDeleteCategory() {
        // Given
        CategoryEntity category = new CategoryEntity();
        category.setName("To Be Deleted");
        entityManager.persist(category);

        // When
        categoryRepository.deleteById(category.getId());

        // Then
        Optional<CategoryEntity> deletedCategory = categoryRepository.findById(category.getId());
        assertFalse(deletedCategory.isPresent());
    }

    @Test
    public void testFindAllCategories() {
        // Given
        CategoryEntity category1 = new CategoryEntity();
        category1.setName("Category 1");
        entityManager.persist(category1);

        CategoryEntity category2 = new CategoryEntity();
        category2.setName("Category 2");
        entityManager.persist(category2);

        // When
        List<CategoryEntity> categories = categoryRepository.findAll();

        // Then
        assertEquals(2, categories.size());
        assertEquals("Category 1", categories.get(0).getName());
        assertEquals("Category 2", categories.get(1).getName());
    }
}
