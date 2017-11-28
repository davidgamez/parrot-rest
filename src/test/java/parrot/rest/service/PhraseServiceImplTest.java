/**
 * 
 */
package parrot.rest.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpMethod.GET;

import parrot.rest.common.Phrase;
import parrot.rest.exception.UrlNotFoundException;
import parrot.rest.repository.PhraseRepository;

/**
 * @author David Gamez
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PhraseServiceImplTest {

	@Autowired
	@InjectMocks
	PhraseServiceImpl fixture;

	@Mock
	PhraseRepository phraseRepo;

	@Test
	public void testSave() {
		Phrase phrase = mock(Phrase.class);

		fixture.save(phrase);

		verify(phraseRepo, times(1)).save(phrase);
	}

	@Test
	public void testGetResponseExisting() throws UrlNotFoundException {
		Phrase phrase = new Phrase();
		phrase.setAppContext("appContext");
		phrase.setResponse("response");
		phrase.setUrl("url");

		String theFullUrl = "appContext/url";

		when(phraseRepo.load(theFullUrl, GET)).thenReturn(phrase);

		String response = fixture.getResponse(theFullUrl, GET);

		assertEquals(phrase.getResponse(), response);
	}

	@Test(expected = UrlNotFoundException.class)
	public void testGetResponseNotExisting() throws UrlNotFoundException {

		String theFullUrl = "appContext/url";

		when(phraseRepo.load(theFullUrl, GET)).thenReturn(null);

		fixture.getResponse(theFullUrl, GET);
	}
}
