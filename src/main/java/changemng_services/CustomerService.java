package changemng_services;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import changemng_entities.Customer;

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
		 
		// deletedCustomer.setCustomerJiras(null); //jiralara dokunmadan customer'ı silmesi için deniyorum
		 
		 if(deletedCustomer.getCustomerJiras() != null) {
			 
			FacesContext.getCurrentInstance().addMessage("Sorry!",
						new FacesMessage("Sorry!", "The customer has registered jiras inside. Cannot be deleted!"));
			
		 }
		 entityManager.remove(deletedCustomer);	
		 
		 
	}

	public void updateCustomer(Customer customer) {
		
		entityManager.merge(customer);
		
	}
	
	public List<Customer> searchByCustomerName(String customerName){
		return entityManager.createQuery("select c from Customer c where UPPER(c.customerName) LIKE '%" + customerName + "%'", Customer.class).getResultList();
	
	}
	
}
