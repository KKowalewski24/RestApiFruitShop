package pl.kkowalewski.springrestfruitshop.controller.ver1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kkowalewski.springrestfruitshop.AppConstant;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.category.CategoryDto;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.category.CategoryListDto;
import pl.kkowalewski.springrestfruitshop.service.category.CategoryService;

@RestController
@RequestMapping(AppConstant.CATEGORY_ROOT_PATH)
public class CategoryController {

    /*------------------------ FIELDS REGION ------------------------*/
    private final CategoryService categoryService;

    /*------------------------ METHODS REGION ------------------------*/
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({AppConstant.ROOT, AppConstant.SLASH})
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDto getAllCategories() {
        return new CategoryListDto(categoryService.getAllCategories());
    }

    @GetMapping(AppConstant.SLASH + AppConstant.NAME)
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getCategoryByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }
}
