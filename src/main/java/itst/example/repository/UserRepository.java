package itst.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import itst.example.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
}
