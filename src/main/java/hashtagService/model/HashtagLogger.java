package hashtagService.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import hashtagService.Application;

public class HashtagLogger {
	private static final Logger log = LoggerFactory.getLogger(HashtagLogger.class);
	private FileWriter fileWriter;
	private Hashtag hashtag;
	private int textCount = 0;
	private String minTagId = null;

	public HashtagLogger(Hashtag hashtag, FileWriter fileWriter) {
		this.hashtag = hashtag;
		this.fileWriter = fileWriter;
	}
	
	public FileWriter getFileWriter() {
		return fileWriter;
	}
	
	public Hashtag getHashtag() {
		return hashtag;
	}
	
	public int getTextCount() {
		return textCount;
	}
	
	public void logTexts() {
		String logPrefix = buidLogPrefix();
		String separator = "\n\n===================================================================================================\n\n\n";

		TagRecentMedia tagRecentMedia = getTagRecentMedia();
		List<String> allTexts = tagRecentMedia.getAllTexts();
		
		textCount += allTexts.size();
		
		if(tagRecentMedia.getPagination().getMinTagId() != null) {
			minTagId = tagRecentMedia.getPagination().getMinTagId();
		}

		try {
			for(String aText : allTexts) {
				log.info(logPrefix + aText + separator);
				fileWriter.write(logPrefix + aText + separator);
				fileWriter.flush();
			}
		} catch (IOException e) {
			log.error(e.toString());
		}
	}
	
	private String buidLogPrefix() {
		String separator = "===================================================================================================\n\n";
		String hashtagName = hashtag.getName();
		
		separator.substring(hashtagName.length() + 1);
		return "\n#" + hashtagName + " " + separator;
	}
	
	private TagRecentMedia getTagRecentMedia() {
		String accessToken = Application.getAccessToken();
		String uri = "http://localhost:8181/messages?name={tag-name}&count={count}";
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("tag-name", hashtag.getName());
		map.put("count", "10");
		map.put("access_token", accessToken);
		if(minTagId != null) {
			map.put("min_tag_id", minTagId);
			uri = uri + "&min_tag_id={min_tag_id}";
		}
		RestTemplate restTemplate = new RestTemplate();
		
		return restTemplate.getForObject(uri, TagRecentMedia.class, map);
	}
}
