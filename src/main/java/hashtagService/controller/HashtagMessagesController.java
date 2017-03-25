package hashtagService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hashtagService.model.Hashtag;
import hashtagService.repository.HashtagRepository;

public class HashtagMessagesController {
	@Autowired
	private HashtagRepository hashtagRepository;

	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public void createHashtag(@RequestParam(value = "name", required = true) String name) {
		hashtagRepository.save(new Hashtag(name));
		//TODO some response;
	}
}
