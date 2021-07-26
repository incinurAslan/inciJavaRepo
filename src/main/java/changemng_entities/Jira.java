package changemng_entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Jira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jiraNr;
	private String jiraNo;
	private String jiraTitle;
	private String jiraStatus;
	
	private LocalDate creationDate;

	
	private String projectManager;
	private double effort;
	
	
	private LocalDate crFormDate;
	private LocalDate effortApprovalDate;
	private LocalDate plannedUatDate;
	private LocalDate actualUatDate;
	private LocalDate liveApprovalDate;
	private LocalDate releaseDate;
	
	@OneToOne(mappedBy = "invoicedJira" ,cascade = {CascadeType.ALL}) 
	private CustomerInvoice jiraInvoice;
	

	@OneToOne(mappedBy = "supplierInvoicedJira" ,cascade = {CascadeType.ALL}) 
	private SupplierInvoice supplierJiraInvoice;
	
	
	@ManyToMany(mappedBy = "productJiras" ,cascade = {CascadeType.ALL}) 
	private List<Product> jiraProducts = new ArrayList<Product>();
	
	//added lately
	@ManyToMany(mappedBy = "customerJiras" ,cascade = {CascadeType.ALL}) 
	private List<Customer> jiraCustomers = new ArrayList<Customer>();
	
	
	
	public Jira() {
		super();
	}
	
	
	public Jira(String jiraNo, String jiraTitle, String jiraStatus, LocalDate creationDate, String projectManager) {
		super();
		this.jiraNo = jiraNo;
		this.jiraTitle = jiraTitle;
		this.jiraStatus = jiraStatus;
		this.creationDate = creationDate;
		this.projectManager = projectManager;

	}


	public String getJiraNo() {
		return jiraNo;
	}

	public void setJiraNo(String jiraNo) {
		this.jiraNo = jiraNo;
	}

	public String getJiraTitle() {
		return jiraTitle;
	}

	public void setJiraTitle(String jiraTitle) {
		this.jiraTitle = jiraTitle;
	}

	public LocalDate getCreationDate() {
		
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		//tried to turn into Str and then LocalDate while persisting
		
		//String creationDateStr = creationDate.toString(); 
		//DateTimeFormatter myFormatter = DateTimeFormatter.ISO_DATE;
		//LocalDate creationDate1 = LocalDate.parse(creationDateStr, myFormatter);
		//this.creationDate = creationDate1;
		
		this.creationDate = creationDate;
	}

	
	public String getProjectManager() {
		return projectManager;
	}


	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}


	public String getJiraStatus() {
		return jiraStatus;
	}



	public void setJiraStatus(String jiraStatus) {
		this.jiraStatus = jiraStatus;
	}

	
	public int getJiraNr() {
		return jiraNr;
	}


	public void setJiraNr(int jiraNr) {
		this.jiraNr = jiraNr;
	}


	public CustomerInvoice getJiraInvoice() {
		return jiraInvoice;
	}


	public void setJiraInvoice(CustomerInvoice jiraInvoice) {
		this.jiraInvoice = jiraInvoice;
	}

	
	public double getEffort() {
		return effort;
	}


	public void setEffort(double effort) {
		this.effort = effort;
	}


	public LocalDate getCrFormDate() {
		return crFormDate;
	}


	public void setCrFormDate(LocalDate crFormDate) {
		this.crFormDate = crFormDate;
	}


	public LocalDate getEffortApprovalDate() {
		return effortApprovalDate;
	}


	public void setEffortApprovalDate(LocalDate effortApprovalDate) {
		this.effortApprovalDate = effortApprovalDate;
	}


	public LocalDate getPlannedUatDate() {
		return plannedUatDate;
	}


	public void setPlannedUatDate(LocalDate plannedUatDate) {
		this.plannedUatDate = plannedUatDate;
	}


	public LocalDate getActualUatDate() {
		return actualUatDate;
	}


	public void setActualUatDate(LocalDate actualUatDate) {
		this.actualUatDate = actualUatDate;
	}


	public LocalDate getLiveApprovalDate() {
		return liveApprovalDate;
	}


	public void setLiveApprovalDate(LocalDate liveApprovalDate) {
		this.liveApprovalDate = liveApprovalDate;
	}


	public LocalDate getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	
	public List<Product> getJiraProducts() {
		return jiraProducts;
	}


	public void setJiraProducts(List<Product> jiraProducts) {
		this.jiraProducts = jiraProducts;
	}

	
	
	

	public SupplierInvoice getSupplierJiraInvoice() {
		return supplierJiraInvoice;
	}


	public void setSupplierJiraInvoice(SupplierInvoice supplierJiraInvoice) {
		this.supplierJiraInvoice = supplierJiraInvoice;
	}


	public List<Customer> getJiraCustomers() {
		return jiraCustomers;
	}


	public void setJiraCustomers(List<Customer> jiraCustomers) {
		this.jiraCustomers = jiraCustomers;
	}


	@Override
	public String toString() {
		return "Jira [jiraNr=" + jiraNr + ", jiraNo=" + jiraNo + ", jiraTitle=" + jiraTitle + ", jiraStatus="
				+ jiraStatus + ", creationDate=" + creationDate + ", projectManager=" + projectManager + ", effort="
				+ effort + ", crFormDate=" + crFormDate + ", effortApprovalDate=" + effortApprovalDate
				+ ", plannedUatDate=" + plannedUatDate + ", actualUatDate=" + actualUatDate + ", liveApprovalDate="
				+ liveApprovalDate + ", releaseDate=" + releaseDate + ", jiraInvoice=" + jiraInvoice
				+ ", supplierJiraInvoice=" + supplierJiraInvoice + ", jiraProducts=" + jiraProducts + ", jiraCustomers="
				+ jiraCustomers + "]";
	}


	
	
	
	
}
