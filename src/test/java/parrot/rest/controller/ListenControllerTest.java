package parrot.rest.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import parrot.rest.common.Phrase;
import parrot.rest.controller.ListenController;
import parrot.rest.service.PhraseService;

@RunWith(MockitoJUnitRunner.class)
public class ListenControllerTest {

	@InjectMocks
	ListenController fixture;
	
	@Mock
	PhraseService phraseService;
	
	@Test
	public void testListen() {
		Phrase phrase = mock(Phrase.class);
		fixture.listen(phrase);
		
		verify(phraseService, times(1)).save(phrase);
	}

}
