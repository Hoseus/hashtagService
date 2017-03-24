package hashtagService.repository;

import org.springframework.beans.factory.annotation.Autowired;

import hashtagService.model.Hashtag;


public class HashtagRepositoryImpl implements HashtagRepositoryCustom {
	@Autowired
	private HashtagRepository hashtagRepository;
	
	public Hashtag update(Hashtag hashtag, String name) {
		hashtag.setName(name);
		
		return hashtagRepository.save(hashtag);
	}
}
