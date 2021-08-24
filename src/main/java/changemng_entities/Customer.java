package changemng_entities;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Customer {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerNo;
	private String customerName;
	private double mandayRate;
	

	/*
	 * @ManyToMany private List<Product> customerProducts = new
	 * ArrayList<Product>();
	 */
	
	@ManyToMany(mappedBy = "jiraCustomers" ,cascade = {CascadeType.ALL}, fetch = FetchType.EAGER) 
	private List<Jira> customerJiras = new ArrayList<Jira>();
		

	public Customer() {
		super();
	}


	public Customer(String customerName, double mandayRate) {
		super();
		this.customerName = customerName;
		this.mandayRate = mandayRate;
	}



	public int getCustomerNo() {
		return customerNo;
	}



	public void setCustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}



	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public double getMandayRate() {
		return mandayRate;
	}


	public void setMandayRate(double mandayRate) {
		this.mandayRate = mandayRate;
	}

	/*
	 * 
	 * public List<Product> getCustomerProducts() { return customerProducts; }
	 * 
	 * 
	 * public void setCustomerProducts(List<Product> customerProducts) {
	 * this.customerProducts = customerProducts; }
	 */
	
	
	public List<Jira> getCustomerJiras() {
		return customerJiras;
	}


	public void setCustomerJiras(List<Jira> customerJiras) {
		this.customerJiras = customerJiras;
	}


	@Override
	public String toString() {
		return "Customer [customerNo=" + customerNo + ", customerName=" + customerName+  "]";
	}

	
}
