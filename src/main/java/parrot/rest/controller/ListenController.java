package parrot.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import parrot.rest.common.Phrase;
import parrot.rest.service.PhraseService;

/**
 * 
 * @author David Gamez
 *
 */
@Controller("listen")
public class ListenController {

	private static final Logger logger = LoggerFactory.getLogger(ListenController.class);
	
	@Autowired
	PhraseService phraseService;
	
	@PostMapping
	public ResponseEntity<String> listen(@RequestBody Phrase phrase) {
		logger.debug("Listening to: {}", phrase);
		phraseService.save(phrase);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
}
