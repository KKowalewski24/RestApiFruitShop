package pl.kkowalewski.springrestfruitshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kkowalewski.springrestfruitshop.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
