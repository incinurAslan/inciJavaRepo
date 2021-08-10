package changemng_services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import changemng_entities.Customer;
import changemng_entities.Product;

@Stateless
public class CustomerService {

	
	@PersistenceContext
	private EntityManager entityManager;

	public List<Customer> getAllCustomers()
	{
		return entityManager.createQuery("select c from Customer c", Customer.class).getResultList();
	}
	
	
	/*
	 * public List<Customer> getAllCustomersByJiraNr(int jiraId){ return
	 * entityManager.
	 * createQuery("select c from Customer c where customerJiras_jiraNr = jiraId",
	 * Customer.class).getResultList(); }
	 */
	
	
	public void addCustomer(Customer customer)
	{

		entityManager.persist(customer);

	}
	
	

	public void deleteCustomer(int customerID) {
		 Customer deletedCustomer =  entityManager.find(Customer.class, customerID);
		 entityManager.remove(deletedCustomer);
		
	}



	
	
	
}
