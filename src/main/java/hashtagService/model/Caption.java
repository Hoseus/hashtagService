package hashtagService.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Caption {
	@JsonProperty("text")
	String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
    @Override
    public String toString() {    	
        return String.format("			Caption : {\n				Text : %s\n			}", text);
    }
}
