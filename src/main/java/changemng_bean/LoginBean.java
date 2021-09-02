package changemng_bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import changemng_entities.User;
import changemng_services.UserService;
import changemng_utils.Utility;


@Named
@SessionScoped
public class LoginBean implements Serializable{
	
	@EJB
	private UserService userService;
	
	private String username;
	private String password;
	
	private boolean loggedIn;
	private String role;
	
	private String accessPage = "/secure/GetAllJiras";
	
	public String login()
	{
		User user = userService.checkUser(username,password);
		//System.out.println( "HASH  --> " + Utility.hash("123"));
		if ( user != null)
		{
			this.loggedIn = true;
			return this.accessPage;
			
			//return "updateJira";
			
		}else {
			
			FacesContext.getCurrentInstance().addMessage("Wrong credentials",
					new FacesMessage("Wrong credentials!", "Please check your username and password again!"));
			
			return "/Login";
	
		}
		
	}
	
	public String logout()
	{
		this.loggedIn = false;
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.invalidate();
		System.out.println("User Logged out");	
		return "/Login.xhtml?faces-redirect=true";
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAccessPage() {
		return accessPage;
	}

	public void setAccessPage(String accessPage) {
		this.accessPage = accessPage;
	}
	
	
	

}
