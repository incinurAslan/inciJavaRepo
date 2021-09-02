package changemng_bean;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import changemng_entities.Customer;
import changemng_entities.CustomerInvoice;
import changemng_entities.InvoiceStatus;
import changemng_entities.Jira;
import changemng_entities.Product;
import changemng_entities.SupplierInvoice;
import changemng_entities.SupplierInvoiceStatus;
import changemng_services.CustomerInvoiceService;
import changemng_services.CustomerService;
import changemng_services.InvoiceStatusService;
import changemng_services.JiraService;
import changemng_services.ProductService;
import changemng_services.SupplierInvoiceService;
import changemng_services.SupplierInvoiceStatusService;

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
	
	@Inject
	private SupplierInvoiceService supplierInvoiceService;
	
	@Inject
	private SupplierInvoiceStatusService supplierInvoiceStatusService;
	
	@Inject
	private CustomerInvoiceService customerInvoiceService;
	
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
	private String supplierInvoiceDateStr;
	
	private List<Customer> customers;
	private List<Product> products;
	
	private List<Integer> selectedCustomers;
	private List<Customer> selectCustomers;
	

	private List<Integer> selectedProducts;
	private List<Product> selectProducts;
	
	private InvoiceStatus selectedInvoiceStatus;
	private int selectedInvStNo;
	private int newSelectedInvoice;
	private int newSelectedSupplierInvoice;
	
	
	private List<InvoiceStatus> invoiceStatusList;
	private List<SupplierInvoiceStatus> supplierInvoiceStatusList;

	
	 private HtmlInputText inputDateComponent = new HtmlInputText();
   
     private HtmlInputText inputCsDateComponent = new HtmlInputText();
     
     private String invalidDate = "1111-11-11"; //default value to insert if no invoice will be made out
     
	
	@PostConstruct
	public void init() {
		
		inputDateComponent.setValue(invalidDate);
		inputCsDateComponent.setValue(invalidDate);
		
		this.jiras = jiraService.getAllJiras();
		this.jira = new Jira();
		this.selectedJira = new Jira();
		this.newJira = new Jira();
		
		invoiceStatusList = invoiceService.getAllInvoiceStatus();
		supplierInvoiceStatusList = supplierInvoiceStatusService.getAllSupplierInvoiceStatus();
		
		
		selectedCustomers = new ArrayList<Integer>();
		selectedProducts = new ArrayList<Integer>();
				
		this.customers = customerService.getAllCustomers();
		selectCustomers = customerService.getAllCustomers();
		
		this.products = productService.getAllProducts();
		selectProducts = productService.getAllProducts();
	
	}
	
	
	public String saveNewJira() {
		
		  //formatter 
		
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //convert String to LocalDate
		  LocalDate creationDate = LocalDate.parse(creationDateStr, formatter);
		  LocalDate crFormDate = LocalDate.parse(crFormDateStr, formatter); 
		  LocalDate approvalDate = LocalDate.parse(effortApprovalDateStr, formatter); 
		  LocalDate plannedUATDate = LocalDate.parse(plannedUATdateStr, formatter); 
		  LocalDate actualUATdate = LocalDate.parse(actualUATdateStr, formatter); 
		  LocalDate liveApprovalDate = LocalDate.parse(liveApprovalDateStr, formatter); 
		  LocalDate releaseDate = LocalDate.parse(releaseDateStr, formatter); 
		  
		  //ayrı bir entity olan CustomerInvoice'taki InvoiceDate bilgisini JSF'ten aldıktan sonra db'ye yazma adımların:
		  LocalDate custInvoiceDate = LocalDate.parse(customerInvoiceDateStr, formatter);
		  CustomerInvoice custInvoiceDate2 = new CustomerInvoice(custInvoiceDate); //local date'ten CustomerInvoice'e convert et
		  customerInvoiceService.addCustomerInvoice(custInvoiceDate2); //customer invoice'i persist et db'ye
		  newJira.setJiraInvoice(custInvoiceDate2); //onetoone ilişkisi set et
		  custInvoiceDate2.setInvoicedJira(newJira); //onetoone ilişkisi set et
		  
		  LocalDate suppInvoiceDate = LocalDate.parse(supplierInvoiceDateStr, formatter);
		  SupplierInvoice suppInvoiceDate2 = new SupplierInvoice(suppInvoiceDate);
		  supplierInvoiceService.addSupplierInvoice(suppInvoiceDate2); //persist supplier invoice
		  newJira.setSupplierJiraInvoice(suppInvoiceDate2);
		  suppInvoiceDate2.setSupplierInvoicedJira(newJira);
		  
		  
		  newJira.setCrFormDate(crFormDate); 
		  newJira.setCreationDate(creationDate);
		  newJira.setEffortApprovalDate(approvalDate);
		  newJira.setPlannedUatDate(plannedUATDate);
		  newJira.setActualUatDate(actualUATdate);
		  newJira.setLiveApprovalDate(liveApprovalDate);
		  newJira.setReleaseDate(releaseDate);
		  
		  newJira.setJiraNo(jira.getJiraNo());
		  newJira.setProjectManager(jira.getProjectManager());
		  newJira.setJiraTitle(jira.getJiraTitle());
		  newJira.setEffort(selectedJira.getEffort()); 
		  newJira.setJiraStatus(jira.getJiraStatus());
		  
	
		//set selected customers on listbox by the user
		Set<Customer> custs = new HashSet<Customer>();
		for (int selCust : selectedCustomers) {
			Customer c = new Customer();
			c.setCustomerNo(selCust);
			custs.add(c);
		}	
		newJira.setJiraCustomers(custs);
		
		
		//set selected products on checkbox by the user
		Set<Product> prods = new HashSet<Product>();
		for (int selProd : selectedProducts) {
			Product p = new Product();
			p.setProductNo(selProd);
			prods.add(p);
		}	
		newJira.setJiraProducts(prods);
	

		//Customer Invoice Status
		InvoiceStatus newInvoiceSt = new  InvoiceStatus();
		 for (InvoiceStatus custInvSt : invoiceStatusList) {
			if (custInvSt.getInvoiceStatusNo()==newSelectedInvoice)
			{
				newInvoiceSt=custInvSt;
				continue;
			}
		}		  
		  newJira.setInvoiceStatusJira(newInvoiceSt);
		 
		  
		  
		//Supplier Invoice Status  
		SupplierInvoiceStatus newSupplierInvSt = new SupplierInvoiceStatus();
		for (SupplierInvoiceStatus suppInvSt : supplierInvoiceStatusList) {
			
			if(suppInvSt.getSupplierInvoiceStatusNo()==newSelectedSupplierInvoice) {
				newSupplierInvSt=suppInvSt;
				continue;
			}
		}
		  newJira.setSupplierInvoiceStatusOfJira(newSupplierInvSt);

		 
		jiraService.addJira(newJira); 
		
		
		FacesContext.getCurrentInstance().addMessage("Successful!",
				new FacesMessage("Successful!", "The new jira is registered!"));
		init();
		return "/secure/GetAllJiras";
		
	}
	
	public String deleteJira(int jiraId) {
		//jiraId = selectedJira.getJiraNr();
		
		jiraService.deleteJira(jiraId);
		
		//init();
		System.out.println("delete worked");
		
		return "/secure/GetAllJiras";
	}
	
	
	public String countTotalEffort() { //counting the effort*manday on all the jiras in the table
		
		DecimalFormat numberFormat = new DecimalFormat("#.000");
		
		double sum = 0.0;
		
		for (Jira jira : jiras) {
			
			for (Customer customer : jira.getJiraCustomers()) {
				
				sum += customer.getMandayRate()*jira.getEffort();
				
			}
			
		}
		
		return numberFormat.format(sum);
				
	}
	

	
	public String getEachJiraPrice(Jira jira) { //counting each jira's price by multiplying with MD rate in customer entity
		
		DecimalFormat numberFormat = new DecimalFormat("#.000");
		
		double sum = 0.0;
		
		for (Customer customer : jira.getJiraCustomers()) {
			
			sum += jira.getEffort()*customer.getMandayRate();
			
		}
		
		String sumStr = numberFormat.format(sum);
		
		return sumStr;
	}
	
	
	
	

	public String updateJira(Jira jira) {
	
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
		
		selectedJira.setEffort(selectedJira.getEffort()); 
		selectedJira.setJiraTitle(selectedJira.getJiraTitle()); 
		
		selectedJira.setCrFormDate(crFormDate);
		selectedJira.setCreationDate(creationDate);
		selectedJira.setEffortApprovalDate(approvalDate);
		selectedJira.setPlannedUatDate(plannedUATDate);
		selectedJira.setActualUatDate(actualUATdate);
		selectedJira.setLiveApprovalDate(liveApprovalDate);
		selectedJira.setReleaseDate(releaseDate);
		
		Set<Customer> custs = new HashSet<Customer>();
		for (int selCust : selectedCustomers) {
			Customer c = new Customer();
			c.setCustomerNo(selCust);
			custs.add(c);
		}	
		selectedJira.setJiraCustomers(custs);
		
		
		//set selected products on checkbox by the user
		Set<Product> prods = new HashSet<Product>();
		for (int selProd : selectedProducts) {
			Product p = new Product();
			p.setProductNo(selProd);
			prods.add(p);
		}	
		selectedJira.setJiraProducts(prods);
	
		
		//Customer Invoice Status
		InvoiceStatus newInvoiceSt = new  InvoiceStatus();
		 for (InvoiceStatus custInvSt : invoiceStatusList) {
			if (custInvSt.getInvoiceStatusNo()==newSelectedInvoice)
			{
				newInvoiceSt=custInvSt;
				//newInvoiceSt = selectedJira.getInvoiceStatusJira(); //remove 
				continue;
			}
		}		  
		 selectedJira.setInvoiceStatusJira(newInvoiceSt);
		 
		
		 //Customer Invoice Date
		LocalDate custInvoiceDate = LocalDate.parse(customerInvoiceDateStr, formatter);
		CustomerInvoice custInvoiceDate2 = new CustomerInvoice(custInvoiceDate); //local date'ten CustomerInvoice'e convert et
		customerInvoiceService.addCustomerInvoice(custInvoiceDate2); //customer invoice'i persist et db'ye
		selectedJira.setJiraInvoice(custInvoiceDate2); //onetoone ilişkisi set et
		custInvoiceDate2.setInvoicedJira(selectedJira); //onetoone ilişkisi set et

		
		//Supplier Invoice Status  
		SupplierInvoiceStatus newSupplierInvSt = new SupplierInvoiceStatus();
			for (SupplierInvoiceStatus suppInvSt : supplierInvoiceStatusList) {
				
				if(suppInvSt.getSupplierInvoiceStatusNo()==newSelectedSupplierInvoice) {
					newSupplierInvSt=suppInvSt;
					continue;
				}
			}
			  selectedJira.setSupplierInvoiceStatusOfJira(newSupplierInvSt);
			  
		
		LocalDate suppInvoiceDate = LocalDate.parse(supplierInvoiceDateStr, formatter);
		SupplierInvoice suppInvoiceDate2 = new SupplierInvoice(suppInvoiceDate);
		supplierInvoiceService.addSupplierInvoice(suppInvoiceDate2); //persist supplier invoice
		selectedJira.setSupplierJiraInvoice(suppInvoiceDate2);
		suppInvoiceDate2.setSupplierInvoicedJira(selectedJira);

		jiraService.updateJiray(selectedJira);
		
		return "DONE! Jira details have been updated.";
	}
	
	
	public void searchJirasByJiraNo() {

		if (jira.getJiraNo() != null) {

		jiras = jiraService.searchByJiraNumber(jira.getJiraNo());

		} else {

		jiras = jiraService.getAllJiras();
		}

	}
	
	
	
	public void searchJirasByProjectManager() {

		if (jira.getProjectManager() != null) {

		jiras = jiraService.searchByProjectManagerName(jira.getProjectManager());

		} else {

		jiras = jiraService.getAllJiras();
		}

	}
	
	public void searchJirasByProductName() {

		if (jira.getJiraProducts() != null) {

			for(Product prod: jira.getJiraProducts()) {
				
				jiras = jiraService.searchByProductName(prod.getProductName());

			}
		
		} else {

		jiras = jiraService.getAllJiras();
		}

	}
	
	
	public String viewJira(Jira jira) {
		
		selectedJira = jira;
		
		return "/secure/updateJira.xhtml?faces-redirect=true"; 
		
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

	


	public List<Integer> getSelectedCustomers() {
		return selectedCustomers;
	}



	public void setSelectedCustomers(List<Integer> selectedCustomers) {
		this.selectedCustomers = selectedCustomers;
	}



	public List<Customer> getSelectCustomers() {
		return selectCustomers;
	}


	public void setSelectCustomers(List<Customer> selectCustomers) {
		this.selectCustomers = selectCustomers;
	}


	public List<Integer> getSelectedProducts() {
		return selectedProducts;
	}


	public void setSelectedProducts(List<Integer> selectedProducts) {
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


	public void setCustomerInvoiceDateStr(String customerInvoiceDateStr) {
		  this.customerInvoiceDateStr = customerInvoiceDateStr; 
	  }
	 



	public InvoiceStatus getSelectedInvoiceStatus() {
		return selectedInvoiceStatus;
	}



	public void setSelectedInvoiceStatus(InvoiceStatus selectedInvoiceStatus) {
		this.selectedInvoiceStatus = selectedInvoiceStatus;
	}

	public CustomerInvoiceService getCustomerInvoiceService() {
		return customerInvoiceService;
	}

	public void setCustomerInvoiceService(CustomerInvoiceService customerInvoiceService) {
		this.customerInvoiceService = customerInvoiceService;
	}

	public int getSelectedInvStNo() {
		return selectedInvStNo;
	}

	public void setSelectedInvStNo(int selectedInvStNo) {
		this.selectedInvStNo = selectedInvStNo;
	}

	public List<InvoiceStatus> getInvoiceStatusList() {
		return invoiceStatusList;
	}

	public void setInvoiceStatusList(List<InvoiceStatus> invoiceStatusList) {
		this.invoiceStatusList = invoiceStatusList;
	}

	public int getNewSelectedInvoice() {
		return newSelectedInvoice;
	}

	public void setNewSelectedInvoice(int newSelectedInvoice) {
		this.newSelectedInvoice = newSelectedInvoice;
	}

	public SupplierInvoiceService getSupplierInvoiceService() {
		return supplierInvoiceService;
	}

	public void setSupplierInvoiceService(SupplierInvoiceService supplierInvoiceService) {
		this.supplierInvoiceService = supplierInvoiceService;
	}

	public String getSupplierInvoiceDateStr() {
		return supplierInvoiceDateStr;
	}

	public void setSupplierInvoiceDateStr(String supplierInvoiceDateStr) {
		this.supplierInvoiceDateStr = supplierInvoiceDateStr;
	}

	public SupplierInvoiceStatusService getSupplierInvoiceStatusService() {
		return supplierInvoiceStatusService;
	}

	public void setSupplierInvoiceStatusService(SupplierInvoiceStatusService supplierInvoiceStatusService) {
		this.supplierInvoiceStatusService = supplierInvoiceStatusService;
	}

	public int getNewSelectedSupplierInvoice() {
		return newSelectedSupplierInvoice;
	}

	public void setNewSelectedSupplierInvoice(int newSelectedSupplierInvoice) {
		this.newSelectedSupplierInvoice = newSelectedSupplierInvoice;
	}

	public List<SupplierInvoiceStatus> getSupplierInvoiceStatusList() {
		return supplierInvoiceStatusList;
	}

	public void setSupplierInvoiceStatusList(List<SupplierInvoiceStatus> supplierInvoiceStatusList) {
		this.supplierInvoiceStatusList = supplierInvoiceStatusList;
	}

	public HtmlInputText getInputDateComponent() {
		return inputDateComponent;
	}

	public void setInputDateComponent(HtmlInputText inputDateComponent) {
		this.inputDateComponent = inputDateComponent;
	}

	public String getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(String invalidDate) {
		this.invalidDate = invalidDate;
	}

	public HtmlInputText getInputCsDateComponent() {
		return inputCsDateComponent;
	}

	public void setInputCsDateComponent(HtmlInputText inputCsDateComponent) {
		this.inputCsDateComponent = inputCsDateComponent;
	}

	

}


