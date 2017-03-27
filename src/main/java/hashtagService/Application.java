package hashtagService;

import java.io.FileWriter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hashtagService.model.HashtagLoggerAdministrator;
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
	public CommandLineRunner messagesCollector(HashtagRepository hashtagRepository) {
		return (args) -> {
			accessToken = "4840848317.1f2b73b.608a4ee3a7454901ba3c61c600009600";
			
			FileWriter fileWriter = new FileWriter("./instagramMessages.txt");
			HashtagLoggerAdministrator hashtagLoggerAdministrator = HashtagLoggerAdministrator.getInstance();
			hashtagLoggerAdministrator.setFileWriter(fileWriter);
			hashtagLoggerAdministrator.addHashtags(hashtagRepository.findAll());
			hashtagLoggerAdministrator.logHashtagTexts();
		};
	}
}
