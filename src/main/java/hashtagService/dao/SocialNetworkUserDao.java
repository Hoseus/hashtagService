package hashtagService.dao;

import hashtagService.model.SocialNetworkUser;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class SocialNetworkUserDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public void create(SocialNetworkUser socialNetworkUser) {
		entityManager.persist(socialNetworkUser);
	}
	  
	public void delete(SocialNetworkUser socialNetworkUser) {
		if (entityManager.contains(socialNetworkUser)) {
			entityManager.remove(socialNetworkUser);
	    }else{
	    	entityManager.remove(entityManager.merge(socialNetworkUser));
	    }
	}

	public List<SocialNetworkUser> getAll() {
		return entityManager.createQuery("SELECT * FROM SOCIAL_NETWORK_USER", SocialNetworkUser.class).getResultList();
	}
	
	public SocialNetworkUser getById(long id) {
		return entityManager.find(SocialNetworkUser.class, id);
	}
	  
	public SocialNetworkUser getByUserNameAndSocialNetworkId(String userName, long socialNetworkId) {
		return (SocialNetworkUser)entityManager.createQuery(
					"SELECT * FROM SOCIAL_NETWORK_USER WHERE USER_NAME = :userName AND SOCIAL_NETWORK_ID = :socialNetworkId"
				)
				.setParameter("userName", userName)
				.setParameter("socialNetworkId", socialNetworkId)
				.getSingleResult();
	}

	//TODO Modificar para que contemple la busqueda por nombre de app social y traiga la instancia que corresp
	public SocialNetworkUser getByUserNameAndSocialNetworkName(String userName, String socialNetworkName) {
		return (SocialNetworkUser)entityManager.createQuery(
					"SELECT * FROM SOCIAL_NETWORK_USER WHERE USER_NAME = :userName AND SOCIAL_NETWORK_ID = :socialNetworkName"
				)
				.setParameter("userName", userName)
				.setParameter("socialNetworkName", socialNetworkName)
				.getSingleResult();
	}

	public void update(SocialNetworkUser socialNetworkUser) {
		entityManager.merge(socialNetworkUser);
	}
}