package pl.kkowalewski.springrestfruitshop.service.category;

import pl.kkowalewski.springrestfruitshop.api.v1.model.category.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryByName(String name);
}
