package parrot.rest.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerMapping;

/**
 * 
 * @author Isuru Weerasooriya
 *
 */
public class PhraseUtility {

	private PhraseUtility() {
	}

	public static String getFullUrl(HttpServletRequest request, Pattern urlPattern) {
		StringBuilder builder = new StringBuilder();
		String fullUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String url = getAppContextUrl(fullUrl, urlPattern);
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

	public static String getAppContextUrl(String fullUrl, Pattern urlPattern) {
		Matcher matcher = urlPattern.matcher(fullUrl);
		if (matcher.matches() && matcher.groupCount() > 0) {
			return matcher.group(1);
		}
		return null;
	}

}
