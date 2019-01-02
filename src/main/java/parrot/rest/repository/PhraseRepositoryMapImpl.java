/**
 * 
 */
package parrot.rest.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import parrot.rest.common.Phrase;

/**
 * @author David Gamez, Isuru Weerasooriya
 *
 */
@Component
@ConditionalOnProperty(name="persistent.type", havingValue = "MAP")
public class PhraseRepositoryMapImpl extends PhraseRepositoryBase {

  private final Map<String, Phrase> map = new HashMap<>();
  
  @Override
  public Phrase save(Phrase phrase) {
    return map.put(getId(phrase), phrase);
  }

  @Override
  public Phrase load(String fullUrl) {
    return map.get(getIdFromUrl(fullUrl));
  }

  @Override
  public Phrase delete(String fullUrl) {
	  return map.remove(getIdFromUrl(fullUrl));
  }
  
}
