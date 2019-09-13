package pl.kkowalewski.springrestfruitshop.controller.ver1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.category.CategoryDto;
import pl.kkowalewski.springrestfruitshop.controller.RestResponseEntityExceptionHandler;
import pl.kkowalewski.springrestfruitshop.exception.ResourceNotFoundException;
import pl.kkowalewski.springrestfruitshop.service.category.CategoryService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.CATEGORY_ROOT_PATH;
import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.SLASH;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long CATEGORY_ID_ONE = 1L;
    private static final Long CATEGORY_ID_TWO = 2L;
    private static final String CATEGORY_NAME_ONE = "ABC";
    private static final String CATEGORY_NAME_TWO = "CDE";

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private MockMvc mockMvc;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    private List<CategoryDto> prepareCategoryDtoList(CategoryDto... categoryDtos) {
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        for (CategoryDto it : categoryDtos) {
            categoryDtoList.add(it);
        }

        return categoryDtoList;
    }

    @Test
    public void getAllCategoriesTest() throws Exception {
        when(categoryService.getAllCategories())
                .thenReturn(prepareCategoryDtoList(
                        new CategoryDto(CATEGORY_ID_ONE, CATEGORY_NAME_ONE),
                        new CategoryDto(CATEGORY_ID_TWO, CATEGORY_NAME_TWO)
                ));

        mockMvc.perform(get(CATEGORY_ROOT_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryDtoList", hasSize(2)));
    }

    @Test
    public void getCategoryByNameTest() throws Exception {
        when(categoryService.getCategoryByName(anyString()))
                .thenReturn(new CategoryDto(CATEGORY_ID_ONE, CATEGORY_NAME_ONE));

        mockMvc.perform(get(CATEGORY_ROOT_PATH + SLASH + CATEGORY_NAME_ONE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(CATEGORY_NAME_ONE)));
    }

    @Test
    public void getCategoryByNameNotFoundTest() throws Exception {
        when(categoryService.getCategoryByName(anyString()))
                .thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CATEGORY_ROOT_PATH + SLASH + "abc")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound());
    }
}
    