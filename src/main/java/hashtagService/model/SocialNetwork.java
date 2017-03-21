package hashtagService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SOCIAL_NETWORK")
public class SocialNetwork {
	private long id;
	private String name;

	public SocialNetwork() {
	}

	public SocialNetwork(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue
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
}
