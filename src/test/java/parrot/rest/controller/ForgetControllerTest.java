package parrot.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import parrot.rest.common.FixtureUtility;
import parrot.rest.exception.UrlNotFoundException;
import parrot.rest.service.PhraseService;

/**
 * @author Isuru Weerasooriya
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ForgetControllerTest {

	@InjectMocks
	ForgetController fixture;

	@Mock
	PhraseService phraseService;

	@Test
	public void testForgetExistingUrl() throws UrlNotFoundException {
		HttpServletRequest request = FixtureUtility.getMockRequest("forget/appContext/url", "");

		ResponseEntity<String> response = fixture.forget(request);

		verify(phraseService, times(1)).remove("appContext/url");

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testForgetNotExistingUrl() throws UrlNotFoundException {
		HttpServletRequest request = FixtureUtility.getMockRequest("forget/appContext/url", "");

		when(phraseService.remove("appContext/url")).thenThrow(UrlNotFoundException.class);
		ResponseEntity<String> response = fixture.forget(request);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
}
