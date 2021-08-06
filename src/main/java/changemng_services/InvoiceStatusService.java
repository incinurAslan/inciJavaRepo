package changemng_services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import changemng_entities.Customer;
import changemng_entities.InvoiceStatus;

@Stateless
public class InvoiceStatusService {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public List<InvoiceStatus> getAllInvoiceStatus()
	{
		return entityManager.createQuery("select i from InvoiceStatus i", InvoiceStatus.class).getResultList();
	}
	
	
	
	
	
	
}
