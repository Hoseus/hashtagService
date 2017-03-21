package hashtagService.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SOCIAL_NETWORK_USER")
public class SocialNetworkUser {	
	private long id;
	private String userName;
	private SocialNetwork socialNetwork;
	private Set<Hashtag> hashtags = new HashSet<Hashtag>();
	
	@Id
	@GeneratedValue
	@Column(name="ID", updatable = false, nullable = false)
	public long getId() {
		return id;
	}
	
	public long setId(long id) {
		return this.id = id;
	}
	
	@Column(name = "USER_NAME", nullable = false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "SOCIAL_NETWORK_ID", referencedColumnName="ID", updatable = false, nullable = false)
	public SocialNetwork getSocialNetwork() {
		return socialNetwork;
	}

	public void setSocialNetwork(SocialNetwork socialNetwork) {
		this.socialNetwork = socialNetwork;
	}
	
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(
		name = "SOCIAL_NETWORK_USER_HASHTAG",
		joinColumns = @JoinColumn(name = "SOCIAL_NETWORK_USER_ID", referencedColumnName = "ID"),
		inverseJoinColumns = @JoinColumn(name = "HASHTAG_ID", referencedColumnName = "ID")
	)
	public Set<Hashtag> getHashtags() {
		return hashtags;
	}

	public void setHashtags(Set<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}
}
