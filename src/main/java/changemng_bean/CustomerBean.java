package changemng_bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import changemng_entities.Customer;
import changemng_entities.Product;
import changemng_services.CustomerService;

@Named
@SessionScoped
public class CustomerBean implements Serializable{
	
	
	//private static final long serialVersionUID = 1L;
	

	@Inject
	private CustomerService customerService;

	private Customer customer;
	
	private List<Customer> customers;
	
	private int customerId;
	
	private Product product;
	

	@PostConstruct
	public void init() {
		
		this.customers = customerService.getAllCustomers();
		this.customer = new Customer();
		this.product = new Product();
		
	}
	
	
	public String saveNewCustomer() {
		
		customerService.addCustomer(customer, product);
		return null;
				
	}

	public void deleteCustomer(int customerId) {
		customerService.deleteCustomer(customerId);
		init();
		
	}


	public CustomerService getCustomerService() {
		return customerService;
	}


	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public List<Customer> getCustomers() {
		return customers;
	}


	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
}
