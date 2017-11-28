/**
 * 
 */
package parrot.rest.service;

import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import parrot.rest.common.Phrase;
import parrot.rest.exception.UrlNotFoundException;
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
	 * @see parrot.rest.service.PhraseService#getResponse(java.lang.String, org.springframework.http.HttpMethod)
	 */
	@Override
	public String getResponse(String url, HttpMethod httpMethod) throws UrlNotFoundException {
		Phrase result = phraseRepository.load(url, httpMethod);
		if (result == null) {
			throw new UrlNotFoundException(url);
		}

		return result.getResponse();
	}
}
