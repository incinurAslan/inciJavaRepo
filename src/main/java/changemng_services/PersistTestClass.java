package changemng_services;

import java.time.LocalDate;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import changemng_entities.Credentials;
import changemng_entities.Customer;
import changemng_entities.CustomerInvoice;
import changemng_entities.InvoiceStatus;
import changemng_entities.Jira;
import changemng_entities.Product;
import changemng_entities.SupplierInvoice;

@Stateless
public class PersistTestClass {
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public void callSampleDate() {
		
		
		Customer customer1 = new Customer("Ziraat Insurance", 2.225);
		Customer customer2 = new Customer("Sompo Insurance", 2.215);
		Customer customer3 = new Customer("Ankara Insurance", 1.195);
		Customer customer4 = new Customer("Fortis Bank Fund", 1.918);
		Customer customer5 = new Customer("Halk Insurance", 1.918);
		Customer customer6 = new Customer("Zurich Insurance", 2.075);
		Customer customer7 = new Customer("Eureko Insurance", 2.165);
		Customer customer8 = new Customer("Allianz Insurance", 2.315);
		Customer customer9 = new Customer("Groupama Insurance", 2.115);
		
		
		entityManager.persist(customer1);
		entityManager.persist(customer2);
		entityManager.persist(customer3);
		entityManager.persist(customer4);
		entityManager.persist(customer5);
		entityManager.persist(customer6);
		entityManager.persist(customer7);
		entityManager.persist(customer8);
		entityManager.persist(customer9);
		
		Product product1 = new Product("Claimer");
		Product product2 = new Product("Mediclaim");
		Product product3 = new Product("Octopus");
		Product product4 = new Product("TSS Ulaks");
		Product product5 = new Product("Chatbot");
		Product product6 = new Product("Albert Web");
		
		entityManager.persist(product1);
		entityManager.persist(product2);
		entityManager.persist(product3);
		entityManager.persist(product4);
		entityManager.persist(product5);
		entityManager.persist(product6);

		
		
		LocalDate createdDate1 = LocalDate.of(2021, 07, 10);
		
		//Date createdDate1 = new Date(2021, 07, 10);
		
		
		Jira jira1 = new Jira("AZ110", "Ulak Tarih Bazlı Tevkifat Tutar Yapısı", "WaitingEffortApproval", createdDate1, "Incinur Aslan");
		
		jira1.getJiraCustomers().add(customer8);
		jira1.getJiraCustomers().add(customer7);
		
		
		LocalDate crFormDate1 = LocalDate.of(2021, 7, 11);
		jira1.setCrFormDate(crFormDate1);
	
		LocalDate approvalDate1 = LocalDate.of(2021, 7, 13);
		jira1.setEffortApprovalDate(approvalDate1);
				
		LocalDate plannedUatDate1 = LocalDate.of(2021, 7, 15);
		jira1.setPlannedUatDate(plannedUatDate1);	

		LocalDate actualuatDate1 = LocalDate.of(2021, 7, 17);
		jira1.setActualUatDate(actualuatDate1);
		
		LocalDate liveApprovalDate1 = LocalDate.of(2021, 7, 18);
		jira1.setLiveApprovalDate(liveApprovalDate1);
		
		LocalDate releaseDate1 = LocalDate.of(2021, 7, 19);
		jira1.setReleaseDate(releaseDate1);
		
		
		jira1.setEffort(3);
		
		entityManager.persist(jira1);
		
		product3.getProductJiras().add(jira1);
		product1.getProductJiras().add(jira1);
		
		
		LocalDate custInvoiceDate1 = LocalDate.of(2021, 7, 23);
		CustomerInvoice customerInvoice1 = new CustomerInvoice(custInvoiceDate1);
		//CustomerInvoice customerInvoice1 = new CustomerInvoice("Fatura Kesildi", custInvoiceDate1);
		entityManager.persist(customerInvoice1);
		
		jira1.setJiraInvoice(customerInvoice1);
		//customerInvoice1.setInvoicedJira(jira1);
		
		LocalDate createdDate2 = LocalDate.of(2021, 6, 10);
		//Date createdDate2 = new Date(2021, 06, 10);
		
		Jira jira2 = new Jira("GR120", "Claimer Tarih Bazlı Tevkifat Tutar Yapısı", "InAnalysis", createdDate2, "Serkan Yazıcı");
		jira2.getJiraCustomers().add(customer9);
		
		InvoiceStatus invoiceStatus1 = new InvoiceStatus("Invoiced");
		InvoiceStatus invoiceStatus2 = new InvoiceStatus("To be invoiced");
		InvoiceStatus invoiceStatus3 = new InvoiceStatus("Not to be invoiced");
		
		entityManager.persist(invoiceStatus1);
		entityManager.persist(invoiceStatus2);
		entityManager.persist(invoiceStatus3);
		
		jira1.setInvoiceStatusJira(invoiceStatus1);
		jira2.setInvoiceStatusJira(invoiceStatus2);
		
	
		LocalDate crFormDate2 = LocalDate.of(2021, 6, 11);
		jira2.setCrFormDate(crFormDate2);
	
		LocalDate approvalDate2 = LocalDate.of(2021, 6, 13);
		jira2.setEffortApprovalDate(approvalDate2);
				
		LocalDate plannedUatDate2 = LocalDate.of(2021, 6, 15);
		jira2.setPlannedUatDate(plannedUatDate2);	

		LocalDate actualuatDate2 = LocalDate.of(2021, 6, 17);
		jira2.setActualUatDate(actualuatDate2);
		
		LocalDate liveApprovalDate2 = LocalDate.of(2021, 6, 18);
		jira2.setLiveApprovalDate(liveApprovalDate2);
		
		LocalDate releaseDate2 = LocalDate.of(2021, 6, 19);
		jira2.setReleaseDate(releaseDate2);
		
		
		jira2.setEffort(5);
		
		entityManager.persist(jira2);
		
		
		jira2.getJiraProducts().add(product4);
		jira2.getJiraProducts().add(product1);
		
		//product4.getProductJiras().add(jira2);
		//product1.getProductJiras().add(jira2);
		
		LocalDate custInvoiceDate2 = LocalDate.of(2021, 6, 23);
		CustomerInvoice customerInvoice2 = new CustomerInvoice(custInvoiceDate2);
		
		jira2.setJiraInvoice(customerInvoice2);
		//customerInvoice2.setInvoicedJira(jira2);
		entityManager.persist(customerInvoice2);
		
	
		LocalDate suppInvoiceDate3 = LocalDate.of(2021, 6, 24);
		SupplierInvoice suppInvoice3 = new SupplierInvoice(suppInvoiceDate3);
		
	
		entityManager.persist(suppInvoice3);
		suppInvoice3.setSupplierInvoicedJira(jira2);
		
		Credentials credentials1 = new Credentials("iaslan", "1234", "Project Manager");
		Credentials credentials2 = new Credentials("demetyel", "4567", "Project Manager");
		Credentials credentials3 = new Credentials("syazici", "8989", "Project Manager");
		
		entityManager.persist(credentials1);
		entityManager.persist(credentials2);
		entityManager.persist(credentials3);
		
		
	}
	

}
