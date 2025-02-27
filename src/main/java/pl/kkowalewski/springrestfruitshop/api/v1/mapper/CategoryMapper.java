package pl.kkowalewski.springrestfruitshop.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.kkowalewski.springrestfruitshop.api.v1.model.category.CategoryDto;
import pl.kkowalewski.springrestfruitshop.model.Category;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto categoryToCategoryDto(Category category);
}
