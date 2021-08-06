package changemng_bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import changemng_entities.Customer;
import changemng_entities.CustomerInvoice;
import changemng_entities.InvoiceStatus;
import changemng_entities.Jira;
import changemng_entities.Product;
import changemng_services.CustomerService;
import changemng_services.InvoiceStatusService;
import changemng_services.JiraService;
import changemng_services.ProductService;

@Named
@SessionScoped
public class JiraBean implements Serializable{
	

	@Inject
	private JiraService jiraService;
	
	@Inject
	private CustomerService customerService;
	
	@Inject
	private ProductService productService;
	
	@Inject
	private InvoiceStatusService invoiceService;
	
	
	private Jira jira;
	
	private List<Jira> jiras;
	
	private int jiraId;
	
	private Jira selectedJira;
	
	private Jira newJira;
	
	private String creationDateStr;
	private String crFormDateStr;
	private String effortApprovalDateStr;
	private String plannedUATdateStr;
	private String actualUATdateStr;
	private String liveApprovalDateStr;
	private String releaseDateStr;
	private String customerInvoiceDateStr;
	
	private List<Customer> customers;
	private List<Product> products;
	
	private List<Object> selectedCustomers;
	private List<Customer> selectCustomers;
	

	private List<Object> selectedProducts;
	private List<Product> selectProducts;
	
	//private InvoiceStatus selectedInvoiceStatus;
	
	//private InvoiceStatus selectedInvoiceStatus;
	private List<InvoiceStatus> invoiceStatusList;
	
	
	
	@PostConstruct
	public void init() {
		
		this.jiras = jiraService.getAllJiras();
		this.jira = new Jira();
		this.selectedJira = new Jira();
		this.newJira = new Jira();
		
	//	this.selectedInvoiceStatus = new InvoiceStatus();
		invoiceStatusList = invoiceService.getAllInvoiceStatus();
	
		selectedCustomers = new ArrayList<Object>();
		selectedProducts = new ArrayList<Object>();
				
		this.customers = customerService.getAllCustomers();
		selectCustomers = customerService.getAllCustomers();
		
		this.products = productService.getAllProducts();
		selectProducts = productService.getAllProducts();

	}


	
	public String saveNewJira() {
		
		//formatter
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//convert String to LocalDate
		LocalDate creationDate = LocalDate.parse(creationDateStr, formatter);
		LocalDate crFormDate = LocalDate.parse(crFormDateStr, formatter);
		LocalDate approvalDate = LocalDate.parse(effortApprovalDateStr, formatter);
		LocalDate plannedUATDate = LocalDate.parse(plannedUATdateStr, formatter);
		LocalDate actualUATdate = LocalDate.parse(actualUATdateStr, formatter);
		LocalDate liveApprovalDate = LocalDate.parse(liveApprovalDateStr, formatter);
		LocalDate releaseDate = LocalDate.parse(releaseDateStr, formatter);
		LocalDate custInvoiceDate = LocalDate.parse(customerInvoiceDateStr, formatter);
		
		CustomerInvoice custInvoiceDate2 = new CustomerInvoice(custInvoiceDate);
		
		newJira.setCrFormDate(crFormDate);
		newJira.setCreationDate(creationDate);
		newJira.setEffortApprovalDate(approvalDate);
		newJira.setPlannedUatDate(plannedUATDate);
		newJira.setActualUatDate(actualUATdate);
		newJira.setLiveApprovalDate(liveApprovalDate);
		newJira.setReleaseDate(releaseDate);
		newJira.setJiraInvoice(custInvoiceDate2);
		
		
		newJira.setJiraNo(jira.getJiraNo());
		newJira.setProjectManager(jira.getProjectManager());
		newJira.setJiraTitle(jira.getJiraTitle());
		newJira.setEffort((Double)jira.getEffort()); //JSF'ten alınan eforu ekleyemiyorum
		newJira.setJiraStatus(jira.getJiraStatus());
		newJira.setInvoiceStatusJira(jira.getInvoiceStatusJira()); //persist ile bu şekilde çalıştı, JSF'ten null converter veriyor.
		
		/*
		 * //aşağıdaki metodla cast hatası alıyorum 
		 * for (Object cust : getSelectedCustomers()) {
		 * Customer myCustTypeCust = (Customer)cust;
		 * newJira.getJiraCustomers().add(myCustTypeCust);
		 * }
		 * 
		 */
		
		jiraService.addJira(newJira); //add metoduna set metodları eklenecek customer ve product için
		return "DONE! New jira details have been saved!";
					
	}
	
	public void deleteJira(int jiraId) {
		jiraService.deleteJira(jiraId);
		init();
		
	}
	
	public String updateJira(Jira jira) {
		
		//not to get null converter error in jsf page
		
		//formatter
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		//String Formatted
		String formattedDateTime = selectedJira.getCreationDate().format(formatter); //Later: creationDate'in güncellenmesine izin vermeyebiliriz. 
		String formattedDateTime1 = selectedJira.getCrFormDate().format(formatter);
		String formattedDateTime2 = selectedJira.getEffortApprovalDate().format(formatter);
		String formattedDateTime3 = selectedJira.getPlannedUatDate().format(formatter);
		String formattedDateTime4 = selectedJira.getActualUatDate().format(formatter);
		String formattedDateTime5 = selectedJira.getLiveApprovalDate().format(formatter);
		String formattedDateTime6 = selectedJira.getReleaseDate().format(formatter);
		
		//convert String to LocalDate
		LocalDate creationDate = LocalDate.parse(creationDateStr, formatter);
		LocalDate crFormDate = LocalDate.parse(crFormDateStr, formatter);
		LocalDate approvalDate = LocalDate.parse(effortApprovalDateStr, formatter);
		LocalDate plannedUATDate = LocalDate.parse(plannedUATdateStr, formatter);
		LocalDate actualUATdate = LocalDate.parse(actualUATdateStr, formatter);
		LocalDate liveApprovalDate = LocalDate.parse(liveApprovalDateStr, formatter);
		LocalDate releaseDate = LocalDate.parse(releaseDateStr, formatter);
		
		
		selectedJira.setCrFormDate(crFormDate);
		selectedJira.setCreationDate(creationDate);
		selectedJira.setEffortApprovalDate(approvalDate);
		selectedJira.setPlannedUatDate(plannedUATDate);
		selectedJira.setActualUatDate(actualUATdate);
		selectedJira.setLiveApprovalDate(liveApprovalDate);
		selectedJira.setReleaseDate(releaseDate);
		
		jiraService.updateJiray(selectedJira);
		
		return "DONE! Jira details have been updated.";
	}
	

	public String viewJira(Jira jira) {
		
		selectedJira = jira;
		
		return "/updateJira.xhtml?faces-redirect=true"; 
		
	}


	public JiraService getJiraService() {
		return jiraService;
	}


	public void setJiraService(JiraService jiraService) {
		this.jiraService = jiraService;
	}


	public CustomerService getCustomerService() {
		return customerService;
	}


	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}


	public ProductService getProductService() {
		return productService;
	}


	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	public Jira getJira() {
		return jira;
	}


	public void setJira(Jira jira) {
		this.jira = jira;
	}


	public List<Jira> getJiras() {
		return jiras;
	}


	public void setJiras(List<Jira> jiras) {
		this.jiras = jiras;
	}


	public int getJiraId() {
		return jiraId;
	}


	public void setJiraId(int jiraId) {
		this.jiraId = jiraId;
	}


	public Jira getSelectedJira() {
		return selectedJira;
	}


	public void setSelectedJira(Jira selectedJira) {
		this.selectedJira = selectedJira;
	}


	public Jira getNewJira() {
		return newJira;
	}


	public void setNewJira(Jira newJira) {
		this.newJira = newJira;
	}


	public String getCreationDateStr() {
		return creationDateStr;
	}


	public void setCreationDateStr(String creationDateStr) {
		this.creationDateStr = creationDateStr;
	}


	public String getCrFormDateStr() {
		return crFormDateStr;
	}


	public void setCrFormDateStr(String crFormDateStr) {
		this.crFormDateStr = crFormDateStr;
	}


	public String getEffortApprovalDateStr() {
		return effortApprovalDateStr;
	}


	public void setEffortApprovalDateStr(String effortApprovalDateStr) {
		this.effortApprovalDateStr = effortApprovalDateStr;
	}


	public String getPlannedUATdateStr() {
		return plannedUATdateStr;
	}


	public void setPlannedUATdateStr(String plannedUATdateStr) {
		this.plannedUATdateStr = plannedUATdateStr;
	}


	public String getActualUATdateStr() {
		return actualUATdateStr;
	}


	public void setActualUATdateStr(String actualUATdateStr) {
		this.actualUATdateStr = actualUATdateStr;
	}


	public String getLiveApprovalDateStr() {
		return liveApprovalDateStr;
	}


	public void setLiveApprovalDateStr(String liveApprovalDateStr) {
		this.liveApprovalDateStr = liveApprovalDateStr;
	}


	public String getReleaseDateStr() {
		return releaseDateStr;
	}


	public void setReleaseDateStr(String releaseDateStr) {
		this.releaseDateStr = releaseDateStr;
	}


	public List<Customer> getCustomers() {
		return customers;
	}


	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public List<Object> getSelectedCustomers() {
		return selectedCustomers;
	}


	public void setSelectedCustomers(List<Object> selectedCustomers) {
		this.selectedCustomers = selectedCustomers;
	}


	public List<Customer> getSelectCustomers() {
		return selectCustomers;
	}


	public void setSelectCustomers(List<Customer> selectCustomers) {
		this.selectCustomers = selectCustomers;
	}


	public List<Object> getSelectedProducts() {
		return selectedProducts;
	}


	public void setSelectedProducts(List<Object> selectedProducts) {
		this.selectedProducts = selectedProducts;
	}


	public List<Product> getSelectProducts() {
		return selectProducts;
	}


	public void setSelectProducts(List<Product> selectProducts) {
		this.selectProducts = selectProducts;
	}



	public String getCustomerInvoiceDateStr() {
		return customerInvoiceDateStr;
	}



	public InvoiceStatusService getInvoiceService() {
		return invoiceService;
	}



	public void setInvoiceService(InvoiceStatusService invoiceService) {
		this.invoiceService = invoiceService;
	}




	public List<InvoiceStatus> getInvoiceStatusList() {
		return invoiceStatusList;
	}



	public void setInvoiceStatusList(List<InvoiceStatus> invoiceStatusList) {
		this.invoiceStatusList = invoiceStatusList;
	}



	public void setCustomerInvoiceDateStr(String customerInvoiceDateStr) {
		this.customerInvoiceDateStr = customerInvoiceDateStr;
	}





	
	
	
	
	
}


