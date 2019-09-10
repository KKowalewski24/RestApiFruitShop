package pl.kkowalewski.springrestfruitshop.api.mapper;

import org.junit.Test;
import pl.kkowalewski.springrestfruitshop.api.model.CategoryDto;
import pl.kkowalewski.springrestfruitshop.model.Category;

import static org.junit.Assert.assertEquals;

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
}
    