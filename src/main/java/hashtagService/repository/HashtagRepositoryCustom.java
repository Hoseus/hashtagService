package hashtagService.repository;

import javax.transaction.Transactional;

import hashtagService.model.Hashtag;

@Transactional
public interface HashtagRepositoryCustom {
	public Hashtag update(Hashtag hashtag, String name);
}
