package comn.firstproject.demo.springprac.assesment.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import comn.firstproject.demo.springprac.assesment.enums.RatingValues;
import comn.firstproject.demo.springprac.assesment.enums.RatingsType;

import javax.persistence.*;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "rating_type")
    @Enumerated(EnumType.STRING)
    private RatingsType ratingType;

    @Column(name = "rating_value")
    @Enumerated(EnumType.ORDINAL)
    private RatingValues ratingValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    Customers customer;

    Rating() {

    }

    public int getId() {
        return id;
    }

    public RatingsType getRatingType() {
        return ratingType;
    }

    public RatingValues getRatingValue() {
        return ratingValue;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }
}
