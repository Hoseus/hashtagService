package hashtagService.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import hashtagService.Application;
import hashtagService.model.Hashtag;
import hashtagService.model.HashtagLoggerThreadStarter;
import hashtagService.model.InstagramPost;
import hashtagService.repository.HashtagRepository;

@RestController
public class HashtagController {
	@Autowired
	private HashtagRepository hashtagRepository;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createHashtag(@RequestParam(value = "name", required = true) String name) {
		HashtagLoggerThreadStarter.getInstance();
		hashtagRepository.save(new Hashtag(name));
		
		return "Hashtag created successfully";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteHashtag(@RequestParam(value = "name", required = true) String name) {
		hashtagRepository.delete(hashtagRepository.findByName(name));
		
		return "Hashtag deleted successfully";
	}

	@RequestMapping(value = "/rename", method = RequestMethod.GET)
	public String renameHashtag(@RequestParam(value = "oldName", required = true) String oldName, @RequestParam(value = "newName", required = true) String newName) {
		hashtagRepository.update(hashtagRepository.findByName(oldName), newName);
		
		return "Hashtag renamed successfully";
	}
	
	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public InstagramPost showMessages(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "count", required = true) String count, @RequestParam(value = "min_tag_id", required = false) String minTagId) throws JsonProcessingException {
		InstagramPost instagramPost = getInstagramPost(name, count, minTagId);
		
		return instagramPost;		
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public void showDashboard(@RequestParam(value = "name", required = true) String name) {
		
	}
	
	private InstagramPost getInstagramPost(String hashtagName, String count, String minTagId) {
		String accessToken = Application.getAccessToken();
		String uri = "https://api.instagram.com/v1/tags/{tag-name}/media/recent?count={count}&access_token={access_token}";
			
		Map<String, String> map = new HashMap<String, String>();
		map.put("tag-name", hashtagName);
		map.put("access_token", accessToken);
		map.put("count", count);
		//map.put("max_tag_id", "10");
		if(minTagId != null) {
			map.put("min_tag_id", minTagId);
			uri = uri + "&min_tag_id={min_tag_id}";
		}

		return new RestTemplate().getForObject(uri, InstagramPost.class, map);
	}
}
