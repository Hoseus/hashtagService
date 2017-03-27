package hashtagService.repository;

import hashtagService.model.Hashtag;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;


@Transactional
public interface HashtagRepository extends CrudRepository<Hashtag, Long>, HashtagRepositoryCustom {
	public Hashtag findByName(String name);
	public List<Hashtag> findAll();
}