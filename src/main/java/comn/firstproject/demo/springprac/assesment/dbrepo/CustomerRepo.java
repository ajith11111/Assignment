package comn.firstproject.demo.springprac.assesment.dbrepo;

import comn.firstproject.demo.springprac.assesment.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customers, Integer> {
    Optional<Customers> findByName(String customerName);
    
}
