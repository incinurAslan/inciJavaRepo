package changemng_services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import changemng_entities.CustomerInvoice;
import changemng_entities.SupplierInvoice;


@Stateless
public class SupplierInvoiceService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public List<SupplierInvoice> getAllSupplierInvoices()
	{
		return entityManager.createQuery("select s from SupplierInvoice s", SupplierInvoice.class).getResultList();
	}
	
	
	
	public void addSupplierInvoice(SupplierInvoice supplierInvoice)
	{

		entityManager.persist(supplierInvoice);

	}
	
	
	
	

}
