package comn.firstproject.demo.springprac.assesment.services;

import comn.firstproject.demo.springprac.assesment.dbrepo.CustomerRepo;
import comn.firstproject.demo.springprac.assesment.entities.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerRatingServiceHelper {

    @Autowired
    private CustomerRepo customerRepo;

    @CacheEvict(value = "customer")
    public Customers getCustomerByIdToAddRating(Integer customerId) {
        Optional<Customers> customers = customerRepo.findById(customerId);

        if (!customers.isPresent()) {
            throw new NoSuchElementException("No Such Customer with ID :" + customerId);
        }

        return customers.get();
    }

}
