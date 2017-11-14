/**
 * 
 */
package parrot.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import parrot.rest.common.Phrase;
import parrot.rest.repository.PhraseRepository;

/**
 * @author David Gamez
 *
 */
@Service
public class PhraseServiceImpl implements PhraseService {

	@Autowired
	PhraseRepository phraseRepository;
	
	/* (non-Javadoc)
	 * @see parrot.rest.repository.PhraseService#save(parrot.rest.common.Phrase)
	 */
	@Override
	public Phrase save(Phrase phrase) {
		return phraseRepository.save(phrase);
	}

	/* (non-Javadoc)
	 * @see parrot.rest.repository.PhraseService#get(java.lang.String, java.lang.String)
	 */
	@Override
	public String getResponse(String url) throws UrlNotFoundException {
		Phrase result = phraseRepository.load(url);
		if (result == null) {
			throw new UrlNotFoundException();
		}

		return result.getResponse();
	}
}
