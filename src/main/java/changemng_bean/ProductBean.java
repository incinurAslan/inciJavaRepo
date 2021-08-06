package changemng_bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import changemng_entities.Product;
import changemng_services.ProductService;

@Named
@SessionScoped
public class ProductBean implements Serializable {

	@Inject
	private ProductService productService;

	private Product product;
	 
	
	private List<Product> products;
	
	//private List<Product> selectedProducts;
	
	private int productId;
	

	@PostConstruct
	public void init() {
		this.products = productService.getAllProducts();
		this.product = new Product();
		//this.selectedProducts = new ArrayList<Product>();
	}
	
	
	public String saveNewProduct() {
		
		productService.addProduct(product);
		return null;
				
	}

	public void deleteProduct(int productId) {
		productService.deleteProduct(productId);
		init();
		
	}


	public ProductService getProductService() {
		return productService;
	}
//
//	
//	public void submit() {
//	    System.out.println("Selected products: " + selectedProducts);
//	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


//	public List<Product> getSelectedProducts() {
//		return selectedProducts;
//	}
//
//
//	public void setSelectedProducts(List<Product> selectedProducts) {
//		this.selectedProducts = selectedProducts;
//	}
//	
	
	
	
	
	
}
