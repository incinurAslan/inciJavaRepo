package changemng_services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import changemng_entities.Product;

@Stateless
public class ProductService {
	

	@PersistenceContext
	private EntityManager entityManager;
		
	
	public List<Product> getAllProducts()
	{
		return entityManager.createQuery("select p from Product p", Product.class).getResultList();
	}
	
	
	public void addProduct(Product product)
	{
		entityManager.persist(product);
		//product.getProductCustomers().add(null);
	}
	
	
	public void deleteProduct(int productID) {
		 Product deletedProduct =  entityManager.find(Product.class, productID);
		 entityManager.remove(deletedProduct);	
	}
	

	public void updateProduct(Product product) {
		
		entityManager.merge(product);
		
	}
	
	
	public List<Product> searchByProductName(String productName){
		return entityManager.createQuery("select p from Product p where UPPER(p.productName) LIKE '%" + productName + "%'", Product.class).getResultList();
	
	}
	
	
	
}
