package hashtagService.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import hashtagService.Application;
import hashtagService.model.Hashtag;
import hashtagService.model.HashtagLoggerAdministrator;
import hashtagService.model.HashtagLogger;
import hashtagService.model.TagRecentMedia;
import hashtagService.repository.HashtagRepository;

@RestController
public class HashtagController {
	@Autowired
	private HashtagRepository hashtagRepository;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createHashtag(@RequestParam(value = "name", required = true) String name) {
		Hashtag hashtag = new Hashtag(name);
		hashtagRepository.save(hashtag);
		HashtagLoggerAdministrator.getInstance().addHashtag(hashtag);
		
		return "Hashtag created successfully";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteHashtag(@RequestParam(value = "name", required = true) String name) {
		Hashtag hashtag = hashtagRepository.findByName(name);
		hashtagRepository.delete(hashtag);
		HashtagLoggerAdministrator.getInstance().removeHashtag(hashtag);
		
		return "Hashtag deleted successfully";
	}

	@RequestMapping(value = "/rename", method = RequestMethod.GET)
	public String renameHashtag(@RequestParam(value = "oldName", required = true) String oldName, @RequestParam(value = "newName", required = true) String newName) {
		Hashtag oldHashtag = hashtagRepository.findByName(oldName);
		Hashtag newHashtag = hashtagRepository.update(oldHashtag, newName);
		HashtagLoggerAdministrator.getInstance().removeHashtag(oldHashtag);
		HashtagLoggerAdministrator.getInstance().addHashtag(newHashtag);
		
		return "Hashtag renamed successfully";
	}
	
	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public TagRecentMedia showMessages(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "count", required = true) String count, @RequestParam(value = "min_tag_id", required = false) String minTagId) {
		TagRecentMedia instagramPost = getTagRecentMedia(name, count, minTagId);
		
		return instagramPost;
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String showDashboard() {
		String dashboard = "";
		String suscripcionesPorHashtag = "";
		String cantidadSuscripciones = "";
		
		cantidadSuscripciones = String.format("Cantidad de suscripciones: %d", hashtagRepository.count());
		cantidadSuscripciones = cantidadSuscripciones + "\n";
		for(HashtagLogger aHashtagLogger : HashtagLoggerAdministrator.getInstance().getHashtagLoggers()) {
			suscripcionesPorHashtag = suscripcionesPorHashtag + String.format(aHashtagLogger.getHashtag().getName() + ": %d", aHashtagLogger.getTextCount());
			suscripcionesPorHashtag = suscripcionesPorHashtag + "\n";
		}
		dashboard = cantidadSuscripciones + suscripcionesPorHashtag;
		
		return dashboard;
	}
	
	private TagRecentMedia getTagRecentMedia(String hashtagName, String count, String minTagId) {
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

		return new RestTemplate().getForObject(uri, TagRecentMedia.class, map);
	}
}
