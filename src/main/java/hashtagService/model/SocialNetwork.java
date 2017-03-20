package hashtagService.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SOCIAL_NETWORK")
public class SocialNetwork {
	private long id;
	private String name;
	private Set<SocialNetwork_User> socialNetwork_Users = new HashSet<SocialNetwork_User>();

	public SocialNetwork() {
	}

	public SocialNetwork(long id, String name) {
		this.id = id;
		this.name = name;
	}

	@Id
	@Column(name="ID", updatable = false, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name="NAME", nullable=false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(mappedBy = "socialNetwork")
	public Set<SocialNetwork_User> getSocialNetwork_Users() {
		return this.socialNetwork_Users;
	}

	public void setSocialNetwork_Users(Set<SocialNetwork_User> socialNetwork_Users) {
		this.socialNetwork_Users = socialNetwork_Users;
	}
}
