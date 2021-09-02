package changemng_entities;

import java.security.MessageDigest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.xml.bind.DatatypeConverter;

@Entity
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String lastName;
	private String username;
	private String password;
	private String role;
	
	public User(String name, String lastName, String username, String password, String role) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	
	public User() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastName=" + lastName + ", username=" + username + ", password="
				+ password + ", role=" + role + "]";
	}
	
	/*
	 * iaslan = 81DC9BDB52D04DC20036DBD8313ED055 --> 1234 dyel =
	 * 674F3C2C1A8A6F90461E8A66FB5550BA--> 5678 slisan =
	 * ABCEEDF5017915685F379075F00A5CCD --> 9101 skarahan =
	 * A01610228FE998F515A72DD730294D87 --> 1212
	 */
	
	
}
