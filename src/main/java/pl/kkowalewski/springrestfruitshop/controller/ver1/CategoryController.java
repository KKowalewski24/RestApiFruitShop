package pl.kkowalewski.springrestfruitshop.controller.ver1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.CategoryDto;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.CategoryListDto;
import pl.kkowalewski.springrestfruitshop.service.CategoryService;

import static pl.kkowalewski.springrestfruitshop.controller.ver1.CategoryController.ROOT_PATH;

@Controller
@RequestMapping(ROOT_PATH)
public class CategoryController {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String API = "api";
    public static final String VER1 = "ver1";
    public static final String CATEGORIES = "categories";
    public static final String ROOT_PATH = "/" + API + "/" + VER1 + "/" + CATEGORIES + "/";

    private final CategoryService categoryService;

    /*------------------------ METHODS REGION ------------------------*/
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryListDto> getAllCategories() {
        return new ResponseEntity<>(new CategoryListDto(
                categoryService.getAllCategories()), HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<CategoryDto> getCategoryByName(@PathVariable String name) {
        return new ResponseEntity<>(
                categoryService.getCategoryByName(name), HttpStatus.OK);
    }
}
