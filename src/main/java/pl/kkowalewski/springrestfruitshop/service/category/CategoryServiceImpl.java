package pl.kkowalewski.springrestfruitshop.service.category;

import org.springframework.stereotype.Service;
import pl.kkowalewski.springrestfruitshop.api.ver1.mapper.CategoryMapper;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.category.CategoryDto;
import pl.kkowalewski.springrestfruitshop.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    /*------------------------ FIELDS REGION ------------------------*/
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    /*------------------------ METHODS REGION ------------------------*/
    public CategoryServiceImpl(CategoryMapper categoryMapper,
                               CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
    }
}
