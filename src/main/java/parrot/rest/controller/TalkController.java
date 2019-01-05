package parrot.rest.controller;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import parrot.rest.common.PhraseUtility;
import parrot.rest.exception.UrlNotFoundException;
import parrot.rest.service.PhraseService;

/**
 * 
 * @author David Gamez
 *
 */
@Controller(TalkController.PATH)
public class TalkController {

	public static final String PATH = "talk";

	private static final Logger logger = LoggerFactory.getLogger(TalkController.class);
	private static final Pattern URL_PATTERN = Pattern.compile("\\/{0,1}talk\\/(.*)");

	@Autowired
	private PhraseService phraseService;

	@GetMapping("talk/**")
	@ResponseBody
	public ResponseEntity<String> talk(HttpServletRequest request) throws UrlNotFoundException {
		String url = PhraseUtility.getFullUrl(request, URL_PATTERN);

		logger.debug("Talking URL: {}", url);
		if (StringUtils.isEmpty(url)) {
			throw new UrlNotFoundException(url);
		}
		return new ResponseEntity<>(phraseService.getResponse(url), HttpStatus.OK);
	}
}
