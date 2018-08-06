package parrot.rest.controller;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import parrot.rest.common.Phrase;
import parrot.rest.exception.UrlNotFoundException;
import parrot.rest.service.PhraseService;

@Controller(ForgetController.PATH)
public class ForgetController {

	public static final String PATH = "forget";
	
	private static final Logger logger = LoggerFactory.getLogger(ForgetController.class);

	@Autowired
	private PhraseService phraseService;
	
	@DeleteMapping("delete/**")
	public ResponseEntity<String> forget(@RequestBody Phrase phrase) throws UrlNotFoundException {
		logger.debug("Deleting phrase: {}", phrase);
		phraseService.remove(phrase.getUrl());
		return new ResponseEntity<>("NO_CONTENT", HttpStatus.NO_CONTENT);
		
	}
	
}

