package changemng_services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import changemng_entities.Customer;
import changemng_entities.InvoiceStatus;
import changemng_entities.SupplierInvoiceStatus;



@Stateless
public class SupplierInvoiceStatusService {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public List<SupplierInvoiceStatus> getAllSupplierInvoiceStatus()
	{
		return entityManager.createQuery("select si from SupplierInvoiceStatus si", SupplierInvoiceStatus.class).getResultList();
	}
	

	
	
	
	
}
