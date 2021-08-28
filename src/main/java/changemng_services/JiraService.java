package changemng_services;

import java.util.List;

import javax.ejb.Stateless;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import changemng_entities.Customer;
import changemng_entities.Jira;
import changemng_entities.Product;



@Stateless
public class JiraService {
	
	@PersistenceContext
	private EntityManager entityManager;
	

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
	 
			/*
			 * for (Product prod : deletedJira.getJiraProducts()) {
			 * 
			 * entityManager.remove("SELECT p FROM Product WHERE p.productNo = " +
			 * prod.getProductNo()); //product'ın kendisini siliyor mu kontrol et
			 * 
			 * }
			 * 
			 * for (Customer cust : deletedJira.getJiraCustomers()) {
			 * 
			 * entityManager.remove("SELECT c FROM Customer WHERE c.customerNo = " +
			 * cust.getCustomerNo());
			 * 
			 * 
			 * }
			 */
		entityManager.remove(deletedJira);

	}
	
	
	public void updateJiray(Jira jira) {
		
		entityManager.merge(jira);
		
	}
	
	
	
	public List<Jira> searchByProjectManagerName(String projectManagerName){
		  return entityManager.createQuery("select j from Jira j where UPPER(j.projectManager) LIKE '%" + projectManagerName + "%'", Jira.class).getResultList();
	  
	}
	 
	
	
	public List<Jira> searchByJiraNumber(String jiraNo){
		return entityManager.createQuery("select j from Jira j where UPPER(j.jiraNo) LIKE '%" + jiraNo + "%'", Jira.class).getResultList();
	
	}
	
	
	

}
