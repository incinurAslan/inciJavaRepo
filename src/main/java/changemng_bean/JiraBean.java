package changemng_bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import changemng_entities.Jira;
import changemng_services.JiraService;

@Named
@SessionScoped
public class JiraBean implements Serializable{
	

	@Inject
	private JiraService jiraService;

	private Jira jira;
	
	private List<Jira> jiras;
	
	private int jiraId;
	
	private Jira selectedJira;

	@PostConstruct
	public void init() {
		this.jiras = jiraService.getAllJiras();
		this.jira = new Jira();
		selectedJira = new Jira();
	
		
		
	}
	
	
	public String saveNewJira() {
		
		jiraService.addJira(jira);
		return null;
				
	}

	public void deleteJira(int jiraId) {
		jiraService.deleteJira(jiraId);
		init();
		
	}
	
	public String updateJira(Jira jira) {
		jiraService.updateJiray(selectedJira);
		return null;
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
	

	
	
}


