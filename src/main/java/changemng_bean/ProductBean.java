package changemng_bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import changemng_entities.Customer;
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
	
	private Product selectedProduct;
	
	private List<Product> productsByName;

	@PostConstruct
	public void init() {
		
		this.products = productService.getAllProducts();
		this.product = new Product();
		this.selectedProduct = new Product();
	
	}
	
	
	public String saveNewProduct() {
		
		productService.addProduct(product);
		init();
		return "/secure/GetAllProducts";
				
	}

	public void deleteProduct(int productId) {
		productService.deleteProduct(productId);
		init();
		
	}


	public ProductService getProductService() {
		return productService;
	}
	
	public String updateProduct(Product product) {

		selectedProduct.setProductName(selectedProduct.getProductName());
		init();
		return "/secure/GetAllProducts";
		
	}
	
	
	
	public String viewProduct(Product product) {
		
		selectedProduct = product;
		
		return "/secure/UpdateProduct.xhtml?faces-redirect=true"; 
		
	}
	
	
	
	public List<Product> getProductsByProdName(){
		
		products = productService.getAllProducts();
		
		if(product.getProductName() != null) {
			
			products = productService.searchByProductName(product.getProductName());
			
		}
		
		return products;
	}
	
	
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


	public Product getSelectedProduct() {
		return selectedProduct;
	}


	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public void setProductsByName(List<Product> productsByName) {
		this.productsByName = productsByName;
	}


	public List<Product> getProductsByName() {
		return productsByName;
	}

	
	

}
