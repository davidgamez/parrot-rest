/**
 * 
 */
package parrot.rest.common;

import java.io.Serializable;

import org.springframework.http.HttpMethod;

/**
 * @author David Gamez
 *
 */
public class Phrase implements Serializable {

	private static final long serialVersionUID = 1L;

	private String appContext;
	private String url;
	private String response;
	private HttpMethod httpMethod;

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
	public HttpMethod getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}
	
	@Override
	public String toString() {
		return "Phrase [appContext=" + appContext + ", url=" + url + ", response=" + response + ", httpMethod="
				+ httpMethod + "]";
	}
	
}
