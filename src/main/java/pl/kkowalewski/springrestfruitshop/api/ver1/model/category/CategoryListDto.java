package pl.kkowalewski.springrestfruitshop.api.ver1.model.category;

import pl.kkowalewski.springrestfruitshop.api.ver1.model.category.CategoryDto;

import java.util.List;

public class CategoryListDto {

    /*------------------------ FIELDS REGION ------------------------*/
    private List<CategoryDto> categoryDtoList;

    /*------------------------ METHODS REGION ------------------------*/
    public CategoryListDto() {
    }

    public CategoryListDto(List<CategoryDto> categoryDtoList) {
        this.categoryDtoList = categoryDtoList;
    }

    public List<CategoryDto> getCategoryDtoList() {
        return categoryDtoList;
    }

    public void setCategoryDtoList(List<CategoryDto> categoryDtoList) {
        this.categoryDtoList = categoryDtoList;
    }
}
