package ro.microservice.inventory.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.microservice.inventory.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Collection<Product> findByCode(final String prodCode);
}
