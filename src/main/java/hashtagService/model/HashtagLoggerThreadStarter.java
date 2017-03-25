package hashtagService.model;

import java.io.FileWriter;
import java.util.List;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class HashtagLoggerThreadStarter {
	private static HashtagLoggerThreadStarter instance = null;
	
	public static HashtagLoggerThreadStarter getInstance() {
		if(instance == null) {
			return new HashtagLoggerThreadStarter();
		}

		return instance;
	}

	public void showHashtags(FileWriter fileWriter, List<Hashtag> hashtags) {		
		ThreadPoolTaskExecutor executor = getThreadTaskExecutor();

		/*
		 * Levanto un thread por cada hashtag para que puedan logear en paralelo.
		 * Ademas, esto va a servir para el stream de mensajes de un hashtag especifico
		 */
		for (Hashtag aHashtag : hashtags) {
			executor.setThreadNamePrefix("HashtagMessageLogger-" + aHashtag.getName() + "-");

			//TODO puede ser que el thread conozca al intermediario y se mate si pasa algo
			executor.execute(new HashtagMessagesLoggerTask(aHashtag.getName(), fileWriter));
		}

		showHashtags(fileWriter);
	}
	
	public void showHashtags(FileWriter fileWriter) {	
		ThreadPoolTaskExecutor executor = getThreadTaskExecutor();
		
		while(true) {
			
		}
	}
	
	private ThreadPoolTaskExecutor getThreadTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		
	    executor.setCorePoolSize(2);
	    executor.setMaxPoolSize(2);
	    executor.setQueueCapacity(500);
	    executor.initialize();
	    
	    return executor;
	}
}
