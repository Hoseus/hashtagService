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
	private Object lock = new Object();
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

	public void addHashtags(List<Hashtag> hashtags) {
		for (Hashtag aHashtag : hashtags) {
			addHashtag(aHashtag);
		}
	}
	
	public void addHashtag(Hashtag hashtag) {
		HashtagLogger hashtagLogger = new HashtagLogger(hashtag, fileWriter);
		synchronized(lock) {
			hashtagLoggers.add(hashtagLogger);
		}
		
		//Signal
		semaphore.release();
	}
	
	public void removeHashtag(Hashtag hashtag) {
		synchronized(lock) {
			HashtagLogger hashtagLogger = hashtagLoggers.stream().filter(aHashtagLogger -> aHashtagLogger.getHashtag().getId() == hashtag.getId()).findFirst().get();
			hashtagLoggers.remove(hashtagLogger);
		}
		
		try {
			//Wait
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void logHashtagTexts() {
		while(true) {
			try {
				//Wait - This wait will stop this loop when the hashtagLoggers collection is empty
				semaphore.acquire();
				//Signal
				semaphore.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			synchronized(lock) {
				for(HashtagLogger aHashtagLogger : hashtagLoggers) {
					aHashtagLogger.logTexts();
				}
			}
			
			//Sleeps for 2 minutes, then starts over logging information
			try {
				Thread.sleep(120000);
			} catch (InterruptedException e) {
				log.error(e.toString());
			}
		}
	}
}
