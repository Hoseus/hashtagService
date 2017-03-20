package hashtagService.dao;

import hashtagService.model.Hashtag;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class HashtagDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public void create(Hashtag hashtag) {
		entityManager.persist(hashtag);
	}
	  
	public void delete(Hashtag hashtag) {
		if (entityManager.contains(hashtag)) {
			entityManager.remove(hashtag);
	    }else{
	    	entityManager.remove(entityManager.merge(hashtag));
	    }
	}

	public List<Hashtag> getAll() {
		return entityManager.createQuery("SELECT * FROM HASHTAG", Hashtag.class).getResultList();
	}
	  
	public Hashtag getById(long id) {
		return entityManager.find(Hashtag.class, id);
	}

	public Hashtag getByName(String name) {
		return entityManager.find(Hashtag.class, name);
	}

	public void update(Hashtag hashtag) {
		entityManager.merge(hashtag);
	}
}