package comn.firstproject.demo.springprac.assesment.services;

import comn.firstproject.demo.springprac.assesment.dbrepo.CustomerRepo;
import comn.firstproject.demo.springprac.assesment.dbrepo.RatingRepo;
import comn.firstproject.demo.springprac.assesment.entities.Customers;
import comn.firstproject.demo.springprac.assesment.entities.Rating;
import comn.firstproject.demo.springprac.assesment.enums.RatingsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerRatingService {

    private final Integer PRODUCT_WEIGHT = 2;
    private final Integer USER_WEIGHT = 1;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private RatingRepo ratingRepo;

    // Needed a CustomerRatingServiceHelper as @CacheEvict was not working when both @Cacheable and @CacheEvict are in same class
    @Autowired
    private CustomerRatingServiceHelper customerRatingServiceHelper;

    @Cacheable("customer")
    public Customers getCustomerByIdForDisplay(Integer customerId) {
        Optional<Customers> customers = customerRepo.findById(customerId);
        if (!customers.isPresent()) {
            throw new NoSuchElementException("No Such Customer Present with ID :" + customerId);
        }
    //    System.out.println(">>   Ran this function : getCustomerByIdForDisplay");
        Customers fetchedCustomers = customers.get();
        if (fetchedCustomers.getRatingList().size() > 0) {
            fetchedCustomers.setAverageRating(getAverageRating(fetchedCustomers.getRatingList()));
        }
        return fetchedCustomers;
    }

   // @CacheEvict(value = "customer", allEntries = true)
    public Customers getCustomerByIdToAddRating(Integer customerId) {

    /*    Optional<Customers> customers = customerRepo.findById(customerId);

        if (!customers.isPresent()) {
            throw new NoSuchElementException("No Such Customer with ID :" + customerId);
        }

     */
        return customerRatingServiceHelper.getCustomerByIdToAddRating(customerId);
                //customers.get();
    }

    public Customers saveANewCustomer(Customers customers) {
        return customerRepo.save(customers);
    }

    public List<Customers> getAllCustomers() {
        List<Customers> customers = customerRepo.findAll();
        for (Customers customer : customers) {
            if (customer.getRatingList().size() > 0) {
                customer.setAverageRating(getAverageRating(customer.getRatingList()));
            }
        }

        return customers;
    }

    public Rating addANewRating(Integer customerId, Rating newRating) {
        Customers customers = getCustomerByIdToAddRating(customerId);
        newRating.setCustomer(customers);
        return ratingRepo.save(newRating);
    }

    public List<Rating> getAllRatings() {

        return ratingRepo.findAll();
    }

    private Double getAverageRating(List<Rating> ratingList) {
        Double rating;
        Integer sum = 0, ratingSum = 0;
        for (int i = 0; i < ratingList.size(); i++) {
            if (ratingList.get(i).getRatingType().equals(RatingsType.product)) {
                sum += PRODUCT_WEIGHT * ratingList.get(i).getRatingValue().ordinal();
                ratingSum += PRODUCT_WEIGHT;
            } else {
                sum += USER_WEIGHT * ratingList.get(i).getRatingValue().ordinal();
                ratingSum += USER_WEIGHT;

            }
        }
        rating = new Double(sum) / ratingSum;
        rating = Math.round(rating * 100.0d) / 100.0;
        return rating;
    }

}
