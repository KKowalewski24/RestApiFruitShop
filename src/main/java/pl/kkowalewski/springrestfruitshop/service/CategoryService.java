package pl.kkowalewski.springrestfruitshop.service;

import pl.kkowalewski.springrestfruitshop.api.ver1.model.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryByName(String name);
}
