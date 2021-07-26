package changemng_entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CustomerInvoice {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int invoiceNo;
	private String customerInvoiceStatus;
	private LocalDate customerInvoiceDate;
	
	
	public CustomerInvoice() {
		super();
	}
	
	@OneToOne
	private Jira invoicedJira;


	public CustomerInvoice(String customerInvoiceStatus, LocalDate customerInvoiceDate) {
		super();
		this.customerInvoiceStatus = customerInvoiceStatus;
		this.customerInvoiceDate = customerInvoiceDate;
	}


	public int getInvoiceNo() {
		return invoiceNo;
	}
	
	
	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	
	public String getCustomerInvoiceStatus() {
		return customerInvoiceStatus;
	}
	
	
	public void setCustomerInvoiceStatus(String customerInvoiceStatus) {
		this.customerInvoiceStatus = customerInvoiceStatus;
	}
	
	
	public LocalDate getCustomerInvoiceDate() {
		return customerInvoiceDate;
	}
	
	
	public void setCustomerInvoiceDate(LocalDate customerInvoiceDate) {
		this.customerInvoiceDate = customerInvoiceDate;
	}

	

	public Jira getInvoicedJira() {
		return invoicedJira;
	}


	public void setInvoicedJira(Jira invoicedJira) {
		this.invoicedJira = invoicedJira;
	}

	
	

	@Override
	public String toString() {
		return "CustomerInvoice [invoiceNo=" + invoiceNo + ", customerInvoiceStatus=" + customerInvoiceStatus
				+ ", customerInvoiceDate=" + customerInvoiceDate + "]";
	}
	


	
}
