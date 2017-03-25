package hashtagService.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagination {
	@JsonProperty("min_tag_id")
	private String minTagId;

	public String getMinTagId() {
		return minTagId;
	}

	public void setMinTagId(String minTagId) {
		this.minTagId = minTagId;
	}
	
    @Override
    public String toString() {    	
        return String.format("	Pagination : {\n		min_tag_id : %s\n	}", minTagId);
    }
}
