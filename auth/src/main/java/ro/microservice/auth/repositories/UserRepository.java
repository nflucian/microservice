package ro.microservice.auth.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.microservice.auth.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findOneByUsername(String username);
}
