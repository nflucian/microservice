package ro.microservice.store.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.microservice.store.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    Collection<Product> findByCategoryId(final Long categId);
    Collection<Product> findByCode(final String code);
}
