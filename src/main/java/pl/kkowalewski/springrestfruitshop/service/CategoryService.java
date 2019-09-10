package pl.kkowalewski.springrestfruitshop.service;

import pl.kkowalewski.springrestfruitshop.api.model.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryByName(String name);
}
