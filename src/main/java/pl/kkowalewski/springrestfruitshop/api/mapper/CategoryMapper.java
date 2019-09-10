package pl.kkowalewski.springrestfruitshop.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.kkowalewski.springrestfruitshop.api.model.CategoryDto;
import pl.kkowalewski.springrestfruitshop.model.Category;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto categoryToCategoryDTO(Category category);
}
