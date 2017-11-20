/**
 * 
 */
package parrot.rest.service;

import parrot.rest.common.Phrase;
import parrot.rest.exception.UrlNotFoundException;

/**
 * @author David Gamez
 *
 */
public interface PhraseService {

	Phrase save(Phrase phrase);
	
	String getResponse(String url) throws UrlNotFoundException;
}
