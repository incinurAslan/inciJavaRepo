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
	private Product product;
	
	
	public List<Customer> getAllCustomers()
	{
		return entityManager.createQuery("select c from Customer c", Customer.class).getResultList();
	}
	
	
	public void addCustomer(Customer customer, Product product)
	{
		entityManager.persist(product);
		//entityManager.merge(product);
		entityManager.persist(customer);
		customer.getCustomerProducts().add(product);
		//Yeni ürün eklenebiliyor müşteri için ancak varolan ürünlerden seçim yapılmıyor. 
		//selectOne ile varolan ürünlerden seçtir...
	}
	
	

	public void deleteCustomer(int customerID) {
		 Customer deletedCustomer =  entityManager.find(Customer.class, customerID);
		 entityManager.remove(deletedCustomer);
		
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	
	
}
