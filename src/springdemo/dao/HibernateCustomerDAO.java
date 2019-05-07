package springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springdemo.entity.Customer;

@Repository
public class HibernateCustomerDAO implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Customer> customersQuesry = session.createQuery("from Customer order by lastName", Customer.class);
		
		List<Customer> customers = customersQuesry.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer newCustomer) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(newCustomer);
	}

	@Override
	public Customer getCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Customer customer = session.get(Customer.class, id);
		
		return customer;
	}

}
