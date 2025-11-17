package itst.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import itst.example.model.Address;

public interface AdressRepository extends JpaRepository<Address, Integer> {
    
}
