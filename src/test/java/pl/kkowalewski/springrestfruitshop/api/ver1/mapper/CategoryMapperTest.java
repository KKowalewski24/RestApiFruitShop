package pl.kkowalewski.springrestfruitshop.api.ver1.mapper;

import org.junit.Test;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.category.CategoryDto;
import pl.kkowalewski.springrestfruitshop.model.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CategoryMapperTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long CATEGORY_ID = 1L;
    private static final String NAME = "ABC";

    private CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    /*------------------------ METHODS REGION ------------------------*/
    @Test
    public void categoryToCategoryDTOTest() {
        CategoryDto categoryDto = categoryMapper
                .categoryToCategoryDTO(new Category(CATEGORY_ID, NAME));

        assertEquals(CATEGORY_ID, categoryDto.getId());
        assertEquals(NAME, categoryDto.getName());
    }

    @Test
    public void categoryToCategoryDTONullTest() {
        CategoryDto categoryDto = categoryMapper.categoryToCategoryDTO(null);

        assertNull(categoryDto);
    }
}
    