package hashtagService.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostData {
	@JsonProperty("caption")
	private Caption caption;

	public Caption getCaption() {
		return caption;
	}

	public void setCaption(Caption caption) {
		this.caption = caption;
	}
	
	public String getText() {
		return caption.getText();
	}
	
    @Override
    public String toString() {    	
        return String.format("		PostData : {\n%s\n		}", caption.toString());
    }
}
