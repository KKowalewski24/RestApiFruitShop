package pl.kkowalewski.springrestfruitshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kkowalewski.springrestfruitshop.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
