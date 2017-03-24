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

//TODO puede ser util
/*    @Query(value = "SELECT * FROM collection WHERE season=:season LIMIT :count", nativeQuery = true)
List<Collection> findCollectionsForSeason(@Param("season") int season, @Param("count") int count);
}*/