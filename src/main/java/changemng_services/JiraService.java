package changemng_services;

import java.util.List;

import javax.ejb.Stateless;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import changemng_entities.Customer;
import changemng_entities.Jira;
import changemng_entities.Product;

@Stateless
public class JiraService {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Jira> getAllJiras() {
		return entityManager.createQuery("select j from Jira j", Jira.class).getResultList();
	}

	public void addJira(Jira jira)// , Customer customer, Product product, CustomerInvoice customerInvoice,
									// SupplierInvoice supplierInvoice)
	{
		entityManager.persist(jira);
	}

	
	public void deleteJira(int jiraID) {
		Jira deletedJira = entityManager.find(Jira.class, jiraID);

		// delete jira
		entityManager.createQuery("delete from Jira j where j.jiraNr = :jiraId").setParameter("jiraId", jiraID)
				.executeUpdate();

		// delete customer invoices
		if (deletedJira.getJiraInvoice() != null) {
			entityManager.createQuery("delete from CustomerInvoice ci where ci.invoiceNo = :invoiceId")
					.setParameter("invoiceId", deletedJira.getJiraInvoice().getInvoiceNo()).executeUpdate();
		}

		// delete supplier invoices
		if (deletedJira.getSupplierJiraInvoice() != null) {
			entityManager.createQuery("delete from SupplierInvoice si where si.suppInvoiceNo = :invoiceId")
					.setParameter("invoiceId", deletedJira.getSupplierJiraInvoice().getSuppInvoiceNo()).executeUpdate();
		}
	}

	
	
	public void updateJiray(Jira jira) {

		entityManager.merge(jira);

	}

	public List<Jira> searchByProjectManagerName(String projectManagerName) {
		return entityManager
				.createQuery("select j from Jira j where UPPER(j.projectManager) LIKE '%" + projectManagerName + "%'",
						Jira.class)
				.getResultList();

	}

	public List<Jira> searchByJiraNumber(String jiraNo) {
		return entityManager
				.createQuery("select j from Jira j where UPPER(j.jiraNo) LIKE '%" + jiraNo + "%'", Jira.class)
				.getResultList();

	}

	public List<Jira> searchByProductName(String productName) {
		return entityManager
				.createQuery("select distinct j from Jira j join j.jiraProducts p where upper(p.productName) LIKE '%"
						+ productName + "%'", Jira.class)
				.getResultList();
	}

	public List<Jira> searchByCustomerName(String customerName) {
		return entityManager
				.createQuery("select distinct j from Jira j join j.jiraCustomers c where upper(c.customerName) LIKE '%"
						+ customerName + "%'", Jira.class)
				.getResultList();
	}

	public List<Jira> searchByInvoiceStatus(String invoiceStatus) {
		return entityManager.createQuery(
				"select distinct j from Jira j join j.InvoiceStatusJira i where upper(i.invoiceName) LIKE '%"
						+ invoiceStatus + "%'",
				Jira.class).getResultList();
	}

	public List<Jira> searchBySupplierInvoiceStatus(String suppInvoiceStatus) {
		return entityManager.createQuery(
				"select distinct j from Jira j join j.SupplierInvoiceStatusOfJira s where upper(s.supplierInvoiceStatus) LIKE '%"
						+ suppInvoiceStatus + "%'",
				Jira.class).getResultList();
	}

}
