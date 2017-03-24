package hashtagService.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import hashtagService.repository.HashtagRepository;

public class MessagesCollectorTask implements Runnable {
	@Autowired
	private HashtagRepository hashtagRepository;
	private final String filePath = "asd";

	@Override
	public void run() {
		FileWriter file = null;
		try {
			file = new FileWriter(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.toString();
		}
		BufferedWriter bufferedWriter = new BufferedWriter(file);
		
		for (Hashtag aHashtag : hashtagRepository.findAll()) {
			/*
			 * Levanto un thread por cada hashtag para que puedan logear en paralelo.
			 * Ademas, esto va a servir para el stream de mensajes de un hashtag especifico
			 */
			ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	        executor.setCorePoolSize(2);
	        executor.setMaxPoolSize(2);
	        executor.setQueueCapacity(500);
	        executor.setThreadNamePrefix("HashtagMessageLogger-");
	        executor.initialize();
	        
	        executor.execute(new HashtagMessagesLoggerTask(aHashtag, bufferedWriter));
		}
	}
}
