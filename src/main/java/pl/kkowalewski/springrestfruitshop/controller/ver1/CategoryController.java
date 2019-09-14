package pl.kkowalewski.springrestfruitshop.controller.ver1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.category.CategoryDto;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.category.CategoryListDto;
import pl.kkowalewski.springrestfruitshop.service.category.CategoryService;

import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.CATEGORY_ROOT_PATH;
import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.NAME;
import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.ROOT;
import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.SLASH;

@Api(description = "Category API")
@RestController
@RequestMapping(CATEGORY_ROOT_PATH)
public class CategoryController {

    /*------------------------ FIELDS REGION ------------------------*/
    private final CategoryService categoryService;

    /*------------------------ METHODS REGION ------------------------*/
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "View List of Categories")
    @GetMapping({ROOT, SLASH})
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDto getAllCategories() {
        return new CategoryListDto(categoryService.getAllCategories());
    }

    @ApiOperation(value = "Get Category By Name")
    @GetMapping(SLASH + NAME)
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getCategoryByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }
}
