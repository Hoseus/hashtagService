package hashtagService.dao;

import hashtagService.model.SocialNetwork_User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class SocialNetwork_UserDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public void create(SocialNetwork_User socialNetwork_User) {
		entityManager.persist(socialNetwork_User);
	}
	  
	public void delete(SocialNetwork_User socialNetwork_User) {
		if (entityManager.contains(socialNetwork_User)) {
			entityManager.remove(socialNetwork_User);
	    }else{
	    	entityManager.remove(entityManager.merge(socialNetwork_User));
	    }
	}

	public List<SocialNetwork_User> getAll() {
		return entityManager.createQuery("SELECT * FROM SOCIALNETWORK_USER", SocialNetwork_User.class).getResultList();
	}
	  
	public SocialNetwork_User getByUserIdAndSocialNetworkId(long userId, long socialNetworkId) {
		return (SocialNetwork_User)entityManager.createQuery(
					"SELECT * FROM SOCIALNETWORK_USER WHERE USER_ID = userId AND SOCIALNETWORK_ID = socialNetworkId"
				)
				.setParameter("userId", userId)
				.setParameter("socialNetworkId", socialNetworkId)
				.getSingleResult();
	}

	public SocialNetwork_User getByUserNameAndSocialNetworkId(String userName, long socialNetworkId) {
		return (SocialNetwork_User)entityManager.createQuery(
					"SELECT * FROM SOCIALNETWORK_USER WHERE USER_NAME = :userName AND SOCIALNETWORK_ID = :socialNetworkId"
				)
				.setParameter("userName", userName)
				.setParameter("socialNetworkId", socialNetworkId)
				.getSingleResult();
	}

	public void update(SocialNetwork_User socialNetwork_User) {
		entityManager.merge(socialNetwork_User);
	}
}