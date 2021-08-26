package changemng_bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import changemng_entities.Customer;
import changemng_entities.Jira;
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
	
	private Customer selectedCustomer;
	
	private List<Customer> customersByName;

	@PostConstruct
	public void init() {
		
		this.customers = customerService.getAllCustomers();
		//this.customers = customerService.searchByCustomerName(" ");
		this.customer = new Customer();
		this.product = new Product();
		this.selectedCustomer = new Customer();
		//this.customersByName = customerService.searchByCustomerName(" ");
		
	}
	
	
	public String saveNewCustomer() {
		
		customerService.addCustomer(customer);
		init();
		return "GetAllCustomers";
				
	}

	public String deleteCustomer(int customerId) {
		customerService.deleteCustomer(customerId);
		init();
		return "GetAllCustomers";
		
	}
	
	public String updateCustomer(Customer customer) {
		
		selectedCustomer.setCustomerName(selectedCustomer.getCustomerName());
		selectedCustomer.setMandayRate(selectedCustomer.getMandayRate());
		
		customerService.updateCustomer(customer);
		init();
		
		return "Successful";
		//return "GetAllCustomers";
		
	}
	
	public String updateCustomer2() {
		customerService.updateCustomer(selectedCustomer);
		return "/GetAllCustomers.xhtml?faces-redirect=true";
	}
	
	
	public String viewCustomer(Customer customer) {
		
		selectedCustomer = customer;
		
		return "/UpdateCustomer.xhtml?faces-redirect=true"; 
		
	}
	
	
	public List<Customer> getCustomersByCustName(){
		
		customers = customerService.getAllCustomers();
		if(customer.getCustomerName() != null) {

			customers = customerService.searchByCustomerName(customer.getCustomerName());
			
		}
		return customers;
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


	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}


	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}


	public List<Customer> getCustomersByName() {
		return customersByName;
	}


	public void setCustomersByName(List<Customer> customersByName) {
		this.customersByName = customersByName;
	}

	
}
