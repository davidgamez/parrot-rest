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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

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
		String url = getFullUrl(request);
		
		logger.debug("Talking URL: {}", url);
		if (StringUtils.isEmpty(url)) {
			throw new UrlNotFoundException(url);
		}
		return new ResponseEntity<>(phraseService.getResponse(url), HttpStatus.OK);
	}

  private String getFullUrl(HttpServletRequest request) {
    StringBuilder builder = new StringBuilder();
    String fullUrl = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
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
