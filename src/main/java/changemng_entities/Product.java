package changemng_entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productNo;
	private String productName;
	
	@ManyToMany
	private List<Jira> productJiras = new ArrayList<Jira>();
		

	@ManyToMany(mappedBy = "customerProducts", cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
	private List<Customer> productCustomers = new ArrayList<Customer>();
	
	
	public Product() {
		super();
	}


	public Product(String productName) {
		super();
		this.productName = productName;
	}
	
	
	public int getProductNo() {
		return productNo;
	}
	
	
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	
	
	public String getProductName() {
		return productName;
	}
	
	
	public void setProductName(String productName) {
		this.productName = productName;
	}	

	
	public List<Jira> getProductJiras() {
		return productJiras;
	}


	public void setProductJiras(List<Jira> productJiras) {
		this.productJiras = productJiras;
	}


	public List<Customer> getProductCustomers() {
		return productCustomers;
	}


	public void setProductCustomers(List<Customer> productCustomers) {
		this.productCustomers = productCustomers;
	}


	@Override
	public String toString() {
		return productNo + " - " + productName;
	}


	
	
}
