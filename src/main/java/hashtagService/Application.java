package hashtagService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import hashtagService.model.Hashtag;
import hashtagService.repository.HashtagRepository;
import hashtagService.services.InstagramOAuth2Template;

@SpringBootApplication
@Component
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
	public CommandLineRunner demo(HashtagRepository dao) {
		return (args) -> {
			//Creo un thread recolector
			
			//Genero hashtags en la base de datos
			//Hashtag hashtag = new Hashtag("nofilter");
			//dao.save(hashtag);

			/*log.info("Hashtag name");
			log.info("-------------------------------");
			log.info(hashtag.getName());
			log.info("");*/
			
			String authToken = "4840848317.1f2b73b.608a4ee3a7454901ba3c61c600009600";
			
			//String uri = String.format("https://api.instagram.com/v1/tags/{tag}/media/recent?count={count}&min_tag_id={min_tag_id}&max_tag_id={max_tag_id}&access_token={access_token}", "nofilter", authToken);
			String uri = String.format("https://api.instagram.com/v1/tags/{tag-name}/media/recent?count={count}&min_tag_id={min_tag_id}&max_tag_id={max_tag_id}&access_token={access_token}");
			
			RestTemplate restTemplate = new RestTemplate();
			Map<String, String> map = new HashMap<String, String>(5);
			map.put("tag-name", "snow");
			map.put("count", "4");
			map.put("min_tag_id", "9");
			map.put("max_tag_id", "10");
			map.put("access_token", "4840848317.1f2b73b.608a4ee3a7454901ba3c61c600009600");
			//ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class, map);
			
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		    HttpEntity<String> request = new HttpEntity<String>(headers);
		    
			ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, request, String.class, map);
			
		    /*try {
		        String url = "https://api.instagram.com/oauth/authorize/?client_id=1f2b73b40cee49aeaf5f8234f582ef09&redirect_uri=http%3A%2F%2Flocalhost%3A8181%2FinstagramRedirect%2F&scope=public_content&response_type=code";
		        HttpClient client = HttpClientBuilder.create().build();
		        HttpGet request = new HttpGet(url);
		        HttpResponse response = client.execute(request);
		        BufferedReader rd = new BufferedReader(
		        	new InputStreamReader(response.getEntity().getContent())
		        );

		        StringBuffer result = new StringBuffer();
		        String line = "";
		        while ((line = rd.readLine()) != null) {
		            result.append(line);
		        }

		        System.out.println(result.toString());

		    }
		    catch(Exception e)
		    {
		        System.out.print("GET CODE ERROR->"+e.toString());
		    }*/
			

			InstagramOAuth2Template a = new InstagramOAuth2Template();
			//log.info(a.getInstagramAccessToken());

			log.info("");
		};
	}
}
