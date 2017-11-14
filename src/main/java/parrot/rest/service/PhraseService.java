/**
 * 
 */
package parrot.rest.service;

import parrot.rest.common.Phrase;

/**
 * @author David Gamez
 *
 */
public interface PhraseService {

	Phrase save(Phrase phrase);
	
	String getResponse(String url) throws UrlNotFoundException;
}
