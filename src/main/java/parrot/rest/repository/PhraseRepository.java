/**
 * 
 */
package parrot.rest.repository;

import parrot.rest.common.Phrase;

/**
 * @author David Gamez, Isuru Weerasooriya
 *
 */
public interface PhraseRepository {

	Phrase save(Phrase phrase);
	
	Phrase load(String fullUrl);

	Phrase delete(String fullUrl);
	
}
