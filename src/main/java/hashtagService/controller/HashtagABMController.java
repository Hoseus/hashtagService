package hashtagService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hashtagService.model.Hashtag;
import hashtagService.repository.HashtagRepository;

@RestController
public class HashtagABMController {
	@Autowired
	private HashtagRepository hashtagRepository;

	@RequestMapping(value = "/createHashtag", method = RequestMethod.GET)
	public void createHashtag(@RequestParam(value = "name", required = true) String name) {
		hashtagRepository.save(new Hashtag(name));
		//TODO some response;
	}

	@RequestMapping(value = "/deleteHashtag", method = RequestMethod.GET)
	public void deleteHashtag(@RequestParam(value = "name", required = true) String name) {
		hashtagRepository.delete(hashtagRepository.findByName(name));
		//TODO some response;
	}

	@RequestMapping(value = "/renameHashtag", method = RequestMethod.GET)
	public void renameHashtag(@RequestParam(value = "oldName", required = true) String oldName, @RequestParam(value = "newName", required = true) String newName) {
		hashtagRepository.update(hashtagRepository.findByName(oldName), newName);
		//TODO some response
	}
}
