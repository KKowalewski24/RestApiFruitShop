package pl.kkowalewski.springrestfruitshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kkowalewski.springrestfruitshop.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
