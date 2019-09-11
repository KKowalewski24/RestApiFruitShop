package pl.kkowalewski.springrestfruitshop.service.category;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.kkowalewski.springrestfruitshop.api.ver1.mapper.CategoryMapper;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.category.CategoryDto;
import pl.kkowalewski.springrestfruitshop.model.Category;
import pl.kkowalewski.springrestfruitshop.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long CATEGORY_ID = 1L;
    private static final String NAME = "ABC";

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryService categoryService;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        categoryService = new CategoryServiceImpl(
                CategoryMapper.INSTANCE, categoryRepository);
    }

    private List<Category> prepareCategoryList(Category... categories) {
        List<Category> categoryList = new ArrayList<>();

        for (Category it : categories) {
            categoryList.add(it);
        }

        return categoryList;
    }

    @Test
    public void getAllCategoriesTest() {
        when(categoryRepository.findAll())
                .thenReturn(prepareCategoryList(new Category(), new Category()));

        assertEquals(2, categoryService.getAllCategories().size());
    }

    @Test
    public void getCategoryByNameTest() {
        when(categoryRepository.findByName(anyString()))
                .thenReturn(new Category(CATEGORY_ID, NAME));

        CategoryDto categoryDto = categoryService.getCategoryByName(NAME);

        assertEquals(CATEGORY_ID, categoryDto.getId());
        assertEquals(NAME, categoryDto.getName());
    }
}
    