package changemng_services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import changemng_entities.CustomerInvoice;


@Stateless
public class CustomerInvoiceService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public List<CustomerInvoice> getAllCustomerInvoices()
	{
		return entityManager.createQuery("select c from CustomerInvoice c", CustomerInvoice.class).getResultList();
	}
	
	
	
	public void addCustomerInvoice(CustomerInvoice customerInvoice)
	{

		entityManager.persist(customerInvoice);

	}
	
	
	
	

}
