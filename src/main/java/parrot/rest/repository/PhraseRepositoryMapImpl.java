/**
 * 
 */
package parrot.rest.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import parrot.rest.common.Phrase;

/**
 *
 */
@Component
@ConditionalOnProperty(name = "persistent.type", havingValue = "MAP")
public class PhraseRepositoryMapImpl extends PhraseRepositoryBase {

	private final Map<String, Phrase> map = new HashMap<>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see parrot.rest.repository.PhraseRepository#save(parrot.rest.common.Phrase)
	 */
	@Override
	public Phrase save(Phrase phrase) {
		return map.put(getId(phrase), phrase);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see parrot.rest.repository.PhraseRepository#load(java.lang.String,
	 * org.springframework.http.HttpMethod)
	 */
	@Override
	public Phrase load(String fullUrl, HttpMethod httpMethod) {
		return map.get(getIdFromUrl(fullUrl));
	}

}
