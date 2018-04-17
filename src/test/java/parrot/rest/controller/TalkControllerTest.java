package parrot.rest.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.HandlerMapping;
import static org.springframework.http.HttpMethod.GET;
import parrot.rest.exception.UrlNotFoundException;
import parrot.rest.service.PhraseService;

@RunWith(MockitoJUnitRunner.class)
public class TalkControllerTest {

  @InjectMocks
  TalkController fixture;

  @Mock
  PhraseService phraseService;

  @Test
  public void testTalkExistingUrlGet() throws UrlNotFoundException {
    HttpServletRequest request = getMockRequest("talk/appContext/url", "");

    fixture.talk(request);

    verify(phraseService, times(1)).getResponse("appContext/url", GET);
  }

  @Test(expected = UrlNotFoundException.class)
  public void testTalkBadUrlRequest() throws UrlNotFoundException {
    HttpServletRequest request = getMockRequest("talk", "");

    fixture.talk(request);
  }

  @SuppressWarnings("unchecked")
  @Test(expected = UrlNotFoundException.class)
  public void testTalkNotExistingUrl() throws UrlNotFoundException {
    HttpServletRequest request = getMockRequest("talk/appContext/url", "");

    when(phraseService.getResponse("appContext/url", GET)).thenThrow(UrlNotFoundException.class);
    fixture.talk(request);
  }

  @SuppressWarnings("unchecked")
  @Test(expected = UrlNotFoundException.class)
  public void testTalkEmptyUrl() throws UrlNotFoundException {
    HttpServletRequest request = getMockRequest("", "");

    when(phraseService.getResponse("appContext/url", GET)).thenThrow(UrlNotFoundException.class);
    fixture.talk(request);
  }

  private HttpServletRequest getMockRequest(String url, String parameters) {
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE)).thenReturn(url);
    when(request.getQueryString()).thenReturn(parameters);
    return request;
  }
}
