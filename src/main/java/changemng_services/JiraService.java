package changemng_services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import changemng_entities.Jira;



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
		 entityManager.remove(deletedJira);
		
	}
	
	
	public void updateJiray(Jira jira) {
		
		entityManager.merge(jira);
		
	}
	
	
	public List<Jira> searchByProjectManagerName(String projectManagerName){
		return entityManager.createQuery("select j from Jira j where UPPER(j.projectManager) LIKE '%" + projectManagerName + "%'", Jira.class).getResultList();
	
	}
	
	
	
	

}
