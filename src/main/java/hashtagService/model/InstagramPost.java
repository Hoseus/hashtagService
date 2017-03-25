package hashtagService.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InstagramPost {
	@JsonProperty("data")
	private List<PostData> postDatas;
	
	@JsonProperty("pagination")
	private Pagination pagination;
	
	public List<PostData> getData() {
		return postDatas;
	}
	public void setData(List<PostData> postDatas) {
		this.postDatas = postDatas;
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	public List<String> getAllTexts() {
		ArrayList<String> allTexts = new ArrayList<String>();
		
		for(PostData aPostData : postDatas) {
			allTexts.add(aPostData.getText());
		}
		return allTexts;
	}
	
    @Override
    public String toString() {
    	String postDatasToString = "";
    	for(PostData aPostData : postDatas){
    		postDatasToString = postDatasToString + aPostData.toString() + ",\n";
    	}
        return String.format("InstagramPost : {\n	PostDetails : [\n%s\n	],\n%s\n}", postDatasToString, pagination.toString());
    }
}
