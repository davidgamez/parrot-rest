/**
 * 
 */
package parrot.rest.common;

import java.io.Serializable;

/**
 * @author David Gamez
 *
 */
public class Phrase implements Serializable {

	private static final long serialVersionUID = 1L;

	private String appContext;
	private String url;
	private String response;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}

	public String getAppContext() {
		return appContext;
	}
	public void setAppContext(String appContext) {
		this.appContext = appContext;
	}
	@Override
	public String toString() {
		return "Phrase [" + (appContext != null ? "appContext=" + appContext + ", " : "")
				+ (url != null ? "url=" + url + ", " : "") + (response != null ? "response=" + response : "") + "]";
	}
}
