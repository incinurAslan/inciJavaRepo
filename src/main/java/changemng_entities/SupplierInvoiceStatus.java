package changemng_entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SupplierInvoiceStatus {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int supplierInvoiceStatusNo;
	private String supplierInvoiceStatus;
	
	
	@OneToOne(mappedBy = "SupplierInvoiceStatusOfJira" ,cascade = {CascadeType.ALL})
	private Jira jiraSupplierStatus;

	
	public SupplierInvoiceStatus() {
		super();
	}


	public SupplierInvoiceStatus(String supplierInvoiceStatus) {
		super();
		this.supplierInvoiceStatus = supplierInvoiceStatus;
	}


	public int getSupplierInvoiceStatusNo() {
		return supplierInvoiceStatusNo;
	}


	public void setSupplierInvoiceStatusNo(int supplierInvoiceStatusNo) {
		this.supplierInvoiceStatusNo = supplierInvoiceStatusNo;
	}


	public String getSupplierInvoiceStatus() {
		return supplierInvoiceStatus;
	}


	public void setSupplierInvoiceStatus(String supplierInvoiceStatus) {
		this.supplierInvoiceStatus = supplierInvoiceStatus;
	}


	public Jira getJiraSupplierStatus() {
		return jiraSupplierStatus;
	}


	public void setJiraSupplierStatus(Jira jiraSupplierStatus) {
		this.jiraSupplierStatus = jiraSupplierStatus;
	}


	@Override
	public String toString() {
		return "SupplierInvoiceStatus [supplierInvoiceStatusNo=" + supplierInvoiceStatusNo + ", supplierInvoiceStatus="
				+ supplierInvoiceStatus + "]";
	}

	
	
	
	

}
