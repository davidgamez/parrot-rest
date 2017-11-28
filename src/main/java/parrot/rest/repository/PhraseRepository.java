/**
 * 
 */
package parrot.rest.repository;

import org.springframework.http.HttpMethod;

import parrot.rest.common.Phrase;

/**
 * @author gamezd
 *
 */
public interface PhraseRepository {

	Phrase save(Phrase phrase);
	
	Phrase load(String fullUrl, HttpMethod httpmethod);

}
