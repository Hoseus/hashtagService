package hashtagService.model;

import java.io.FileWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashtagLoggerAdministrator {
	private static HashtagLoggerAdministrator instance = null;
	private FileWriter fileWriter;
	private Set<HashtagLogger> hashtagLoggers;
	Semaphore semaphore;
	private static final Logger log = LoggerFactory.getLogger(HashtagLoggerAdministrator.class);
	
	public static HashtagLoggerAdministrator getInstance() {
		if(instance == null) {
			instance = new HashtagLoggerAdministrator();
			instance.initialize();
			return instance;
		}

		return instance;
	}
	
	private void initialize() {
		this.hashtagLoggers = new HashSet<HashtagLogger>();
		this.semaphore = new Semaphore(0);
	}
	
	public FileWriter getFileWriter() {
		return fileWriter;
	}

	public void setFileWriter(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	
	public Set<HashtagLogger> getHashtagLoggers() {
		return hashtagLoggers;
	}
	
	public Set<HashtagLogger> setHashtagLoggers(Set<HashtagLogger> hashtagLoggers) {
		return this.hashtagLoggers = hashtagLoggers;
	}

	public void addHashtags(List<Hashtag> hashtags) {
		for (Hashtag aHashtag : hashtags) {
			addHashtag(aHashtag);
		}
	}
	
	public void addHashtag(Hashtag hashtag) {
		HashtagLogger hashtagLogger = new HashtagLogger(hashtag, fileWriter);
		hashtagLoggers.add(hashtagLogger);
		
		//Signal
		semaphore.release();
	}
	
	public void removeHashtag(Hashtag hashtag) {
		HashtagLogger hashtagLogger = hashtagLoggers.stream().filter(aHashtagLogger -> aHashtagLogger.getHashtag().getId() == hashtag.getId()).findFirst().get();
		try {
			//Wait
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		hashtagLoggers.remove(hashtagLogger);
	}
	
	public void logHashtagTexts() {
		while(true) {
			try {
				//Wait
				semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for(HashtagLogger aHashtagLogger : hashtagLoggers) {
				aHashtagLogger.logTexts();
			}
			//Signal
			semaphore.release();
			
			//Sleeps for 2 minutes, then starts over logging information
			try {
				//Thread.sleep(120000);
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				log.error(e.toString());
			}
		}
	}
}
