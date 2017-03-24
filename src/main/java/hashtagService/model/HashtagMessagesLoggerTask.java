package hashtagService.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HashtagMessagesLoggerTask implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(HashtagMessagesLoggerTask.class);
	private BufferedWriter bufferedWriter;
	private Hashtag hashtag;

	public HashtagMessagesLoggerTask(Hashtag hashtag, BufferedWriter bufferedWriter) {
		this.hashtag = hashtag;
		this.bufferedWriter = bufferedWriter;
	}
	
	@Override
	public void run() {
		String logText = hashtag.getName();
		String separator = "===================================================================================================";		
		separator.substring(logText.length() + 1);		
		logText = logText + " " + separator + "\n";
		
		String authToken = "4840848317.1f2b73b.608a4ee3a7454901ba3c61c600009600";
		
		String uri = String.format("https://api.instagram.com/v1/tags/%s/media/recent?access_token=%s", hashtag.getName(), authToken);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class, new HashMap<String, String>());
		try {
			bufferedWriter.write(logText);
		} catch (IOException e) {
			log.error(e.toString());
		}
		
	}

}
