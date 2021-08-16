package changemng_services;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.soap.SOAPBinding.Use;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import changemng_entities.User;
import changemng_utils.Utility;


@Stateless
public class UserService {
	
	@PersistenceContext
	private EntityManager entityManager;

	public User checkUser(String username, String password) {
		 
		 password = Utility.hash(password);
		 System.out.println("User Input password (from textbox) hashed --> "+password);
					
		 List<User> users = entityManager.createQuery("select u from User u where u.username=?1 and u.password=?2", User.class)
				 .setParameter(1, username)
				 .setParameter(2, password)
				 .getResultList();	
		
		if (users.size()==1)
		{
			System.out.println("User password ( from DB ) hashed --> "+password);
			return users.get(0);
		}
		return null;
	}

	
	
	
}
