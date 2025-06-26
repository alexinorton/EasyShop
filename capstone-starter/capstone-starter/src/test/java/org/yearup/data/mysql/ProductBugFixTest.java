package org.yearup.data.mysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yearup.models.Category;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MySqlCategoryDaoTest extends BaseDaoTestClass
{
    private MySqlCategoryDao dao;

    @BeforeEach
    public void setup()
    {
        dao = new MySqlCategoryDao(dataSource);
    }

    @Test
    public void getAllCategories_shouldReturn_allCategories()
    {
        // arrange
        int expectedCount = 3; // Based on test data

        // act
        List<Category> categories = dao.getAllCategories();

        // assert
        assertEquals(expectedCount, categories.size(), "Should return all categories from database");
    }

    @Test
    public void getById_shouldReturn_theCorrectCategory()
    {
        // arrange
        int categoryId = 1;
        String expectedName = "Electronics";

        // act
        Category actual = dao.getById(categoryId);

        // assert
        assertNotNull(actual);
        assertEquals(expectedName, actual.getName(), "Should return the correct category");
        assertEquals(categoryId, actual.getCategoryId());
    }

    @Test
    public void getById_shouldReturn_null_whenCategoryDoesNotExist()
    {
        // arrange
        int nonExistentId = 999;

        // act
        Category actual = dao.getById(nonExistentId);

        // assert
        assertNull(actual, "Should return null for non-existent category");
    }

    @Test
    public void create_shouldReturn_newCategory()
    {
        // arrange
        Category newCategory = new Category();
        newCategory.setName("Test Category");
        newCategory.setDescription("Test Description");

        // act
        Category created = dao.create(newCategory);

        // assert
        assertNotNull(created);
        assertTrue(created.getCategoryId() > 0, "Should have a generated ID");
        assertEquals(newCategory.getName(), created.getName());
        assertEquals(newCategory.getDescription(), created.getDescription());
    }

    @Test
    public void update_shouldModify_existingCategory()
    {
        // arrange
        int categoryId = 1;
        Category updatedCategory = new Category();
        updatedCategory.setCategoryId(categoryId);
        updatedCategory.setName("Updated Electronics");
        updatedCategory.setDescription("Updated Description");

        // act
        dao.update(categoryId, updatedCategory);
        Category actual = dao.getById(categoryId);

        // assert
        assertNotNull(actual);
        assertEquals("Updated Electronics", actual.getName());
        assertEquals("Updated Description", actual.getDescription());
    }

    @Test
    public void delete_shouldRemove_existingCategory()
    {
        // arrange
        Category newCategory = new Category();
        newCategory.setName("To Delete");
        newCategory.setDescription("Will be deleted");
        Category created = dao.create(newCategory);
        int categoryId = created.getCategoryId();

        // act
        dao.delete(categoryId);
        Category deleted = dao.getById(categoryId);

        // assert
        assertNull(deleted, "Category should be deleted");
    }
}