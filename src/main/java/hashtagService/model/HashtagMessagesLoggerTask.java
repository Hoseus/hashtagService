package hashtagService.model;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import hashtagService.Application;

public class HashtagMessagesLoggerTask implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(HashtagMessagesLoggerTask.class);
	private FileWriter fileWriter;
	private String hashtagName;

	public HashtagMessagesLoggerTask(String hashtagName, FileWriter fileWriter) {
		this.hashtagName = hashtagName;
		this.fileWriter = fileWriter;
	}
	
	@Override
	public void run() {
		boolean runAgain = true;
		String logPrefix = buidLogPrefix();
		String separator = "\n\n===================================================================================================\n\n\n";
		String mintagId = null;
		
		while(runAgain) {
			InstagramPost instagramPost = getInstagramPost(mintagId);
		
			try {
				for(String aText : instagramPost.allTexts()) {
					log.info(logPrefix + aText + separator);
					fileWriter.write(logPrefix + aText + separator);
					fileWriter.flush();
				}

				//Sleeps for 2 minutes, then starts over logging information
				Thread.sleep(120000);
			} catch (Exception e) {
				log.error(e.toString());
				runAgain = false;
			}
		}
	}
	
	private String buidLogPrefix() {
		String separator = "===================================================================================================\n\n";
		
		separator.substring(hashtagName.length() + 1);
		return "\n#" + hashtagName + " " + separator;
	}
	
	private InstagramPost getInstagramPost(String minTagId) {
		String accessToken = Application.getAccessToken();
		String uri = "http://localhost:8181/messages?name={tag-name}&count={count}";
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("tag-name", hashtagName);
		map.put("count", "10");
		map.put("access_token", accessToken);
		//map.put("max_tag_id", "10");
		if(minTagId != null) {
			map.put("min_tag_id", minTagId);
			uri = uri + "&min_tag_id={min_tag_id}";
		}
		RestTemplate restTemplate = new RestTemplate();
		
		return restTemplate.getForObject(uri, InstagramPost.class, map);
	}
}
