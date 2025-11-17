package itst.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import itst.example.model.Role;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Optional<Role> findByAuthority(String role);
}
