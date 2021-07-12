package comn.firstproject.demo.springprac.assesment.dbrepo;

import comn.firstproject.demo.springprac.assesment.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RatingRepo extends JpaRepository<Rating, Integer> {

}
