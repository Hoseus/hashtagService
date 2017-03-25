package hashtagService;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import hashtagService.model.HashtagLoggerThreadStarter;
import hashtagService.model.InstagramPost;
import hashtagService.repository.HashtagRepository;

@SpringBootApplication
public class Application {
	private static String accessToken;
	
	public static String getAccessToken() {
		return accessToken;
	}
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
	public CommandLineRunner messagesCollector(HashtagRepository hashtgRepository) {
		return (args) -> {
			/*hashtgRepository.save(new Hashtag("nofilter"));
			hashtgRepository.save(new Hashtag("axe"));*/
			accessToken = "4840848317.1f2b73b.608a4ee3a7454901ba3c61c600009600";
			
			HashtagLoggerThreadStarter threadStarter= HashtagLoggerThreadStarter.getInstance();
			
			FileWriter fileWriter = new FileWriter("./instagramMessages.txt");
			threadStarter.showHashtags(fileWriter, hashtgRepository.findAll());

			fileWriter.close();
		};
	}
}
