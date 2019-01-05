/**
 * 
 */
package parrot.rest.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import parrot.rest.common.Phrase;

/**
 * @author David Gamez
 *
 */
public class PhraseRepositoryBaseTest {

  PhraseRepositoryBase repository;
  
  @Before
  public void setup() {
    repository = Mockito.mock(PhraseRepositoryBase.class, Mockito.CALLS_REAL_METHODS);  
  }
  
  @Test
  public void testGetIdCaseInsensitive() {
    assertEquals("context/the/url", repository.getId(getPhrase("context", "the/URL")));
    assertEquals("context/the/url", repository.getId(getPhrase("Context", "THE/URL")));
    assertEquals("context/the/url", repository.getId(getPhrase("CONTEXT", "thE/uRL")));
  }

  @Test
  public void testSlashes() {
    assertEquals("context/the/url", repository.getId(getPhrase("context", "the/URL")));
    assertEquals("context/the/url", repository.getId(getPhrase("context", "/the/URL")));
    assertEquals("context/the/url/", repository.getId(getPhrase("context", "the/URL/")));
    assertEquals("context/the/url/", repository.getId(getPhrase("context", "/the/URL/")));
    
    assertEquals("context/the/url", repository.getId(getPhrase("/context", "the/URL")));
    assertEquals("context/the/url", repository.getId(getPhrase("/context", "/the/URL")));
    assertEquals("context/the/url/", repository.getId(getPhrase("/context", "the/URL/")));
    assertEquals("context/the/url/", repository.getId(getPhrase("/context", "/the/URL/")));
    
    assertEquals("context/the/url", repository.getId(getPhrase("/context/", "the/URL")));
    assertEquals("context/the/url", repository.getId(getPhrase("/context/", "/the/URL")));
    assertEquals("context/the/url/", repository.getId(getPhrase("/context/", "the/URL/")));
    assertEquals("context/the/url/", repository.getId(getPhrase("/context/", "/the/URL/")));
  }
  
  @Test
  public void testEmptyContextAndPath() {
    assertEquals("/the/url", repository.getId(getPhrase("", "the/URL")));
    assertEquals("/the/url/", repository.getId(getPhrase("", "the/URL/")));
    
    assertEquals("context/", repository.getId(getPhrase("context", "")));
    assertEquals("context/", repository.getId(getPhrase("context/", "")));
    
    assertEquals("/", repository.getId(getPhrase("/", "")));
    assertEquals("/", repository.getId(getPhrase("", "/")));
  }
  
  /**
   * Helper methods
   */
  private Phrase getPhrase(String context, String url) {
    Phrase result = new Phrase();
    result.setAppContext(context);
    result.setUrl(url);
    return result;
  }
}
