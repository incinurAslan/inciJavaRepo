package changemng_entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class InvoiceStatus {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int invoiceStatusNo;
	private String invoiceName;
	
	
	//@OneToOne(mappedBy = "InvoiceStatusJira" ,cascade = {CascadeType.ALL})
	//private Jira jiraInvoiceStatus;

	
	public InvoiceStatus() {
		super();
	}

	public InvoiceStatus(String invoiceName) {
	
		this.invoiceName = invoiceName;
	}

	public int getInvoiceStatusNo() {
		return invoiceStatusNo;
	}
	
	public void setInvoiceStatusNo(int invoiceStatusNo) {
		this.invoiceStatusNo = invoiceStatusNo;
	}
	
	public String getInvoiceName() {
		return invoiceName;
	}
	
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	


	@Override
	public String toString() {
		return "InvoiceStatus [invoiceStatusNo=" + invoiceStatusNo + ", invoiceName=" + invoiceName + "]";
	}
	
	
	
	

}
