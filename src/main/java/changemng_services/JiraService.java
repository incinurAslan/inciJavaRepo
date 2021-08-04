package changemng_services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import changemng_entities.Customer;
import changemng_entities.CustomerInvoice;
import changemng_entities.Jira;
import changemng_entities.Product;
import changemng_entities.SupplierInvoice;

@Stateless
public class JiraService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private Customer customer;
	private Product product;
	
	private CustomerInvoice customerInvoice;
	private SupplierInvoice suuplierInvoice;
	

	public List<Jira> getAllJiras()
	{
		return entityManager.createQuery("select j from Jira j", Jira.class).getResultList();
	}
	
	
	
	public void addJira(Jira jira)//, Customer customer, Product product, CustomerInvoice customerInvoice, SupplierInvoice supplierInvoice)
	{
		entityManager.persist(jira);
	}
	

	public void deleteJira(int jiraID) {
		 Jira deletedJira =  entityManager.find(Jira.class, jiraID);
		 entityManager.remove(deletedJira);
		
	}
	
	
	public void updateJiray(Jira jira) {
		
		entityManager.merge(jira);
		
	}
	
	
	

}
