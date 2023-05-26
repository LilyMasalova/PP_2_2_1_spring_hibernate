package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import jakarta.persistence.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   public List<User> getUserByCar() {
      Query query = sessionFactory.getCurrentSession().createQuery("from User user where user.car.model=:paramModel and user.car.series=:paramSeries");
      query.setParameter("paramModel", "Ford");
      query.setParameter("paramSeries", 555);
      return query.getResultList();

   }

}
