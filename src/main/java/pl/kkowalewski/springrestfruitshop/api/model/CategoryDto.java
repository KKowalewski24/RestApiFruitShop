package pl.kkowalewski.springrestfruitshop.api.model;

public class CategoryDto {

    /*------------------------ FIELDS REGION ------------------------*/
    private Long id;
    private String name;

    /*------------------------ METHODS REGION ------------------------*/
    public CategoryDto() {
    }

    public CategoryDto(Long id) {
        this.id = id;
    }

    public CategoryDto(String name) {
        this.name = name;
    }

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
