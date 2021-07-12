package comn.firstproject.demo.springprac.assesment;


import comn.firstproject.demo.springprac.assesment.entities.Customers;
import comn.firstproject.demo.springprac.assesment.entities.Rating;
import comn.firstproject.demo.springprac.assesment.services.CustomerRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CustomerRatingApi {

    private final Integer PRODUCT_WEIGHT = 2;
    private final Integer USER_WEIGHT = 1;


    @Autowired
    private CustomerRatingService customerRatingService;

    public void setCustomerRatingService(CustomerRatingService customerRatingService) {
        this.customerRatingService = customerRatingService;
    }

    @GetMapping("/customers")
    public List<Customers> getAllCustomers() {

        return customerRatingService.getAllCustomers();
    }

    @GetMapping("/customer/{customerId}")
    public Customers getCustomerById(@PathVariable Integer customerId) {
        Customers fetchedCustomers = customerRatingService.getCustomerByIdForDisplay(customerId);
        return fetchedCustomers;
    }

    @GetMapping("/customer/{customerId}/ratings")
    public List<Rating> getCustomerRatingsById(@PathVariable Integer customerId) {
        Customers fetchedCustomers = customerRatingService.getCustomerByIdForDisplay(customerId);
        return fetchedCustomers.getRatingList();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/customer")
    public Customers addNewCustomer(@Valid @RequestBody Customers customers) {
        return customerRatingService.saveANewCustomer(customers);
    }

    @PostMapping("/customer/{id}/rating")
    public Rating makeNewRatingById(@PathVariable int id, @RequestBody Rating rating) {
        return customerRatingService.addANewRating(id, rating);
    }

    @GetMapping("/ratings")
    public List<Rating> getAllRatings() {
        return customerRatingService.getAllRatings();
    }

}
