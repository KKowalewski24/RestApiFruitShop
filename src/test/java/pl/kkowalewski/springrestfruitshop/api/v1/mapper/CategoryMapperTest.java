package pl.kkowalewski.springrestfruitshop.api.v1.mapper;

import org.junit.jupiter.api.Test;
import pl.kkowalewski.springrestfruitshop.api.v1.model.category.CategoryDto;
import pl.kkowalewski.springrestfruitshop.model.Category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CategoryMapperTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long CATEGORY_ID = 1L;
    private static final String NAME = "ABC";

    private CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    /*------------------------ METHODS REGION ------------------------*/
    @Test
    public void categoryToCategoryDTOTest() {
        CategoryDto categoryDto = categoryMapper
                .categoryToCategoryDto(new Category(CATEGORY_ID, NAME));

        assertEquals(CATEGORY_ID, categoryDto.getId());
        assertEquals(NAME, categoryDto.getName());
    }

    @Test
    public void categoryToCategoryDTONullTest() {
        CategoryDto categoryDto = categoryMapper.categoryToCategoryDto(null);

        assertNull(categoryDto);
    }
}
    