package comn.firstproject.demo.tempbin;


import comn.firstproject.demo.springprac.assesment.entities.Customers;
;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;


  //  public CustomerRepository(EntityManager entityManager) {
   //     this.entityManager = entityManager;
 //   }


    @Transactional
    public List<Customers> getAllCustomers() {

        //  entityManager.unwrap(Session.class);

        TypedQuery<Customers> query = entityManager.createQuery("from CustomerDetails ", Customers.class);

        return query.getResultList();
    }
}
