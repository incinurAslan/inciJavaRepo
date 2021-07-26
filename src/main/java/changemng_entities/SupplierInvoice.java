package changemng_entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SupplierInvoice {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int suppInvoiceNo;
	private String supplierInvoiceStatus;
	private LocalDate supplierInvoiceDate;
	
	@OneToOne
	private Jira supplierInvoicedJira;
	

	
	public SupplierInvoice() {
		super();
	}
	


	public SupplierInvoice(String supplierInvoiceStatus, LocalDate supplierInvoiceDate) {
		super();
		this.supplierInvoiceStatus = supplierInvoiceStatus;
		this.supplierInvoiceDate = supplierInvoiceDate;
	}



	public int getSuppInvoiceNo() {
		return suppInvoiceNo;
	}


	public void setSuppInvoiceNo(int suppInvoiceNo) {
		this.suppInvoiceNo = suppInvoiceNo;
	}


	public String getSupplierInvoiceStatus() {
		return supplierInvoiceStatus;
	}


	public void setSupplierInvoiceStatus(String supplierInvoiceStatus) {
		this.supplierInvoiceStatus = supplierInvoiceStatus;
	}


	public LocalDate getSupplierInvoiceDate() {
		return supplierInvoiceDate;
	}


	public void setSupplierInvoiceDate(LocalDate supplierInvoiceDate) {
		this.supplierInvoiceDate = supplierInvoiceDate;
	}

	
	

	public Jira getSupplierInvoicedJira() {
		return supplierInvoicedJira;
	}



	public void setSupplierInvoicedJira(Jira supplierInvoicedJira) {
		this.supplierInvoicedJira = supplierInvoicedJira;
	}



	@Override
	public String toString() {
		return "SupplierInvoice [suppInvoiceNo=" + suppInvoiceNo + ", supplierInvoiceStatus=" + supplierInvoiceStatus
				+ ", supplierInvoiceDate=" + supplierInvoiceDate + ", supplierInvoicedJira=" + supplierInvoicedJira
				+ "]";
	}


	
	
	
	
}
