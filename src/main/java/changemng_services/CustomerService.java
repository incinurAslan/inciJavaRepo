package changemng_services;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import changemng_entities.Customer;
import changemng_entities.Jira;

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
		 
		 for(Jira j : deletedCustomer.getCustomerJiras()) {
			 
			 if(j != null) {
				 FacesContext.getCurrentInstance().addMessage("Sorry!",
							new FacesMessage("Sorry!", "The customer has registered jiras inside. Cannot be deleted!"));
				
				 return;
				 
			 }
			 }
		 
			 entityManager.remove(deletedCustomer);	
			 
		 }
		

	
	
	
	  public void deleteCustomer2(int customerID) {
	  
	  Customer deletedCustomer = entityManager.find(Customer.class, customerID);
	  
	  for(Jira j : deletedCustomer.getCustomerJiras()) {
	  
	  if(j != null) { FacesContext.getCurrentInstance().addMessage("Sorry!", new
	  FacesMessage("Sorry!",
	  "The customer has registered jiras inside. Cannot be deleted!"));
	  
	  }else { entityManager.
	  createQuery("delete from Customer c where c.customerNo = :customerId").
	  setParameter("customerId", customerID) .executeUpdate();
	  
	  } }
	  
	  }
	 
	
	

	public void updateCustomer(Customer customer) {
		
		entityManager.merge(customer);
		
	}
	
	public List<Customer> searchByCustomerName(String customerName){
		return entityManager.createQuery("select c from Customer c where UPPER(c.customerName) LIKE '%" + customerName + "%'", Customer.class).getResultList();
	
	}
	
}
