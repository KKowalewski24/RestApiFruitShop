package pl.kkowalewski.springrestfruitshop.api.ver1.model.category;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CategoryListDto that = (CategoryListDto) o;

        return new EqualsBuilder()
                .append(categoryDtoList, that.categoryDtoList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(categoryDtoList)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("categoryDtoList", categoryDtoList)
                .toString();
    }
}
