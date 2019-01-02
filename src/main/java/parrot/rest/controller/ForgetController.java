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

import parrot.rest.common.PhraseUtility;
import parrot.rest.exception.UrlNotFoundException;
import parrot.rest.service.PhraseService;

/**
 * 
 * @author Isuru Weerasooriya
 *
 */
@Controller(ForgetController.PATH)
public class ForgetController {

	public static final String PATH = "forget";

	private static final Logger logger = LoggerFactory.getLogger(ForgetController.class);
	private static final Pattern URL_PATTERN = Pattern.compile("\\/{0,1}forget\\/(.*)");

	@Autowired
	private PhraseService phraseService;

	@DeleteMapping("forget/**")
	public ResponseEntity<String> talk(HttpServletRequest request) {
		ResponseEntity<String> result = null;
		String url = PhraseUtility.getFullUrl(request, URL_PATTERN);

		logger.debug("Forgetting URL: {}", url);
		try {
			phraseService.remove(url);
			result = new ResponseEntity<>("OK", HttpStatus.OK);
		} catch (UrlNotFoundException e) {
			result = new ResponseEntity<>("NO_CONTENT", HttpStatus.NO_CONTENT);
		}
		return result;
	}

}
