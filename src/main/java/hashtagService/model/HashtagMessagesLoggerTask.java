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
	private Hashtag hashtag;

	public HashtagMessagesLoggerTask(Hashtag hashtag, FileWriter fileWriter) {
		this.hashtag = hashtag;
		this.fileWriter = fileWriter;
	}
	
	@Override
	public void run() {
		boolean runAgain = true;
		String logPrefix = buidLogPrefix();
		String separator = "\n\n===================================================================================================\n\n\n";		
		InstagramPost instagramPost = getInstagramPost();
		
		try {
			for(String aText : instagramPost.getAllTexts()) {
				log.info(logPrefix + aText + separator);
				fileWriter.write(logPrefix + aText + separator);
				fileWriter.flush();
			}

			//Sleeps for 2 minutes, then starts over with the file logs
			Thread.sleep(120000);
		} catch (Exception e) {
			log.error(e.toString());
			runAgain = false;
		}
	}
	
	private String buidLogPrefix() {
		String hashtagName = hashtag.getName();
		String separator = "===================================================================================================\n";
		
		separator.substring(hashtagName.length() + 1);
		return "\n#" + hashtagName + " " + separator + "\n";
	}
	
	private InstagramPost getInstagramPost() {
		String accessToken = Application.getAccessToken();
		String uri = "https://api.instagram.com/v1/tags/{tag-name}/media/recent?count={count}&access_token={access_token}";
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("tag-name", hashtag.getName());
		map.put("count", "4");
		//map.put("min_tag_id", "9");
		//map.put("max_tag_id", "10");
		map.put("access_token", accessToken);
		
		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.getForObject(uri, InstagramPost.class, map);
	}
}
