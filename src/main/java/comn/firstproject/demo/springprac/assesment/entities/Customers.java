package comn.firstproject.demo.springprac.assesment.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customers {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @Column(name = "phone", unique = true)
    @Pattern(regexp = "[89][0-9]{9}")
    private String phone;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    List<Rating> ratingList;

    @Transient
    private Double averageRating;

    public Customers() {

    }

    public Customers(Integer id, String name, String email, String phone, List<Rating> ratingList, Double averageRating) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.ratingList = ratingList;
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", ratingList=" + ratingList +
                ", averageRating=" + averageRating +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
