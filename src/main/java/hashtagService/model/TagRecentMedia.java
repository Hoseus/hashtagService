package hashtagService.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TagRecentMedia {
	@JsonProperty("data")
	private List<Post> posts;
	
	@JsonProperty("pagination")
	private Pagination pagination;
	
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	@JsonIgnore
	public List<String> getAllTexts() {
		ArrayList<String> allTexts = new ArrayList<String>();

		for(Post aPost : posts) {
			allTexts.add(aPost.getText());
		}
		
		return allTexts;
	}
}
