package ro.microservice.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.microservice.store.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
