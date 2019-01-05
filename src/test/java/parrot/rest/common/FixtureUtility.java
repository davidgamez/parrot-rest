package parrot.rest.common;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.HandlerMapping;

public class FixtureUtility {

	private FixtureUtility() {

	}

	public static HttpServletRequest getMockRequest(String url, String parameters) {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE)).thenReturn(url);
		when(request.getQueryString()).thenReturn(parameters);
		return request;
	}

}
