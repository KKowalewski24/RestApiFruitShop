package pl.kkowalewski.springrestfruitshop.service.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kkowalewski.springrestfruitshop.api.v1.mapper.CategoryMapper;
import pl.kkowalewski.springrestfruitshop.api.v1.model.category.CategoryDto;
import pl.kkowalewski.springrestfruitshop.model.Category;
import pl.kkowalewski.springrestfruitshop.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long CATEGORY_ID = 1L;
    private static final String NAME = "ABC";

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryService categoryService;

    /*------------------------ METHODS REGION ------------------------*/
    @BeforeEach
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
    