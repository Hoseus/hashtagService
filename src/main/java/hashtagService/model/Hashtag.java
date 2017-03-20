package hashtagService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="HASHTAG")
public class Hashtag {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private long id;
	
	@Column(name="NAME", nullable=false)
	private String name;

	public Hashtag() {
	}

	public Hashtag(String name) {
		this.name = name;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setCourseName(String name) {
		this.name = name;
	}
}
