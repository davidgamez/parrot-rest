package parrot.rest.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.servlet.HandlerMapping;

import parrot.rest.exception.UrlNotFoundException;
import parrot.rest.service.PhraseService;

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
		String url = getFullUrl(request);

		logger.debug("Forgetting URL: {}", url);
		try {
			phraseService.remove(url);
			result = new ResponseEntity<>("OK", HttpStatus.OK);
		} catch (UrlNotFoundException e) {
			result = new ResponseEntity<>("NO_CONTENT", HttpStatus.NO_CONTENT);
		}
		return result;

	}

	private String getFullUrl(HttpServletRequest request) {
		StringBuilder builder = new StringBuilder();
		String fullUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String url = getAppContextUrl(fullUrl);
		if (url == null) {
			return null;
		}
		builder.append(url);
		if (StringUtils.isNotEmpty(request.getQueryString())) {
			builder.append("?");
			builder.append(request.getQueryString());
		}
		return builder.toString();
	}

	private String getAppContextUrl(String fullUrl) {
		Matcher matcher = URL_PATTERN.matcher(fullUrl);
		if (matcher.matches() && matcher.groupCount() > 0) {
			return matcher.group(1);
		}
		return null;
	}

}
