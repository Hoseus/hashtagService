package hashtagService.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User {
	private long id;
	private Set<Hashtag> hashtags = new HashSet<Hashtag>();
	private Set<SocialNetwork_User> socialNetwork_Users = new HashSet<SocialNetwork_User>();

	public User() {
	}
	
	public User(Set<Hashtag> hashtags, Set<SocialNetwork_User> socialNetworks) {
		this.hashtags = hashtags;
		this.socialNetwork_Users = socialNetworks;
	}
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name = "USER_HASHTAG",
		joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
		inverseJoinColumns = @JoinColumn(name = "HASHTAG_ID", referencedColumnName = "ID")
	)
	public Set<Hashtag> getHashtags() {
		return hashtags;
	}

	public void setHashtags(Set<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}
	
	@OneToMany(mappedBy = "user")
	public Set<SocialNetwork_User> getSocialNetwork_Users() {
		return socialNetwork_Users;
	}

	public void setSocialNetwork_Users(Set<SocialNetwork_User> socialNetwork_Users) {
		this.socialNetwork_Users = socialNetwork_Users;
	}
}
