/**
 * 
 */
package parrot.rest.service;

import org.springframework.http.HttpMethod;

import parrot.rest.common.Phrase;
import parrot.rest.exception.UrlNotFoundException;

/**
 * @author David Gamez
 *
 */
public interface PhraseService {

	Phrase save(Phrase phrase);
	
	String getResponse(String url, HttpMethod httpMethod) throws UrlNotFoundException;
}
