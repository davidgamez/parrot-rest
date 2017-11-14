/**
 * 
 */
package parrot.rest.repository;

import parrot.rest.common.Phrase;

/**
 * @author gamezd
 *
 */
public interface PhraseRepository {

	Phrase save(Phrase phrase);
	
	Phrase load(String fullUrl);

}
