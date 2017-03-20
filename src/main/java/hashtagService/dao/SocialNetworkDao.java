package hashtagService.dao;

import hashtagService.model.SocialNetwork;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class SocialNetworkDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public void create(SocialNetwork socialNetwork) {
		entityManager.persist(socialNetwork);
	}
	  
	public void delete(SocialNetwork socialNetwork) {
		if (entityManager.contains(socialNetwork)) {
			entityManager.remove(socialNetwork);
	    }else{
	    	entityManager.remove(entityManager.merge(socialNetwork));
	    }
	}

	public List<SocialNetwork> getAll() {
		return entityManager.createQuery("SELECT * FROM SOCIALNETWORK", SocialNetwork.class).getResultList();
	}
	  
	public SocialNetwork getById(long id) {
		return entityManager.find(SocialNetwork.class, id);
	}

	public SocialNetwork getByName(String socialNetworkName) {		
		return (SocialNetwork) entityManager.createQuery(
				"SELECT * FROM SOCIALNETWORK WHERE NAME = :socialNetworkName"
			).getSingleResult();
	}

	public void update(SocialNetwork socialNetwork) {
		entityManager.merge(socialNetwork);
	}
}