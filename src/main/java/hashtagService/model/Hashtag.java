package hashtagService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="HASHTAG")
public class Hashtag {
	private long id;
	private String name;

	public Hashtag() {
	}

	public Hashtag(String name) {
		this.name = name;
	}
	
	public Hashtag(long id, String name) {
		this.id = id;
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

	@Column(name="NAME", nullable=false, unique=true)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
