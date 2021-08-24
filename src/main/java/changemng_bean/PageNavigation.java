package changemng_bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.inject.Named;

@Named
@RequestScoped
@javax.annotation.ManagedBean
public class PageNavigation implements Serializable{

	@ManagedProperty(value = "#{param.pageId}")
	   private String pageId;
	
	   public String showPage() {
	      if(pageId == null) {
	         return "GetAllJiras";
	      }
	      
	      if(pageId.equals("1")) {
	         return "/RegisterNewJira.xhtml?faces-redirect=true";
	         
	      }else if(pageId.equals("2")) {
	         return "/GetAllCustomers.xhtml?faces-redirect=true";
	       
	      }else if(pageId.equals("3")) {
		         return "/AddCustomer.xhtml?faces-redirect=true";
	           
	      }else if(pageId.equals("4")) {
		         return "/GetAllProducts.xhtml?faces-redirect=true"; 
	       
	      }else if(pageId.equals("5")) {
		         return "/AddProduct.xhtml?faces-redirect=true"; 
	      }else {
	         return "/GetAllJiras.xhtml?faces-redirect=true";
	      }
	   }


}
