/**
 * 
 */
package parrot.rest.repository;

import org.apache.commons.lang3.StringUtils;

import parrot.rest.common.Phrase;

/**
 *
 */
public abstract class PhraseRepositoryBase  implements PhraseRepository {

  /**
   * 
   * @param phrase
   * @return the identifier used by the persistent layer for saving and retrieving URLs
   */
  public String getId(Phrase phrase) {
    String result = String.format("%s/%s", getNormalizeContext(phrase.getAppContext()), getNormalizePath(phrase.getUrl()));
    return result.replaceAll("\\/\\/", "\\/");
  }

  private String getNormalizeContext(String appContext) {
    String result = getNormalizePath(appContext);
    if (result.endsWith("/")) {
      result = result.substring(0 , result.length() -1);
    }
    return result;
  }

  public String getIdFromUrl(String fullUrl) {
    return getNormalizePath(fullUrl);
  }


  private String getNormalizePath(String fullUrl) {
    if (StringUtils.isEmpty(fullUrl)) {
      return StringUtils.EMPTY;
    }
    
    String normilized = fullUrl.trim().toLowerCase();
    if (normilized.startsWith("/") &&
        normilized.length() > 1) {
      normilized = normilized.substring(1);
    }
    return normilized;
  }
}
