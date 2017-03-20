package hashtagService.dao;

import hashtagService.model.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public void create(User user) {
		entityManager.persist(user);
	}
	  
	public void delete(User user) {
		if (entityManager.contains(user)) {
			entityManager.remove(user);
	    }else{
	    	entityManager.remove(entityManager.merge(user));
	    }
	}

	public List<User> getAll() {
		return entityManager.createQuery("SELECT * FROM USER", User.class).getResultList();
	}
	  
	public User getById(long id) {
		return entityManager.find(User.class, id);
	}

	public void update(User user) {
		entityManager.merge(user);
	}
}