package parrot.rest.exception;

public class UrlNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	private String url;
	
	public UrlNotFoundException(String url) {
	  this.url = url;
	}

  public String getUrl() {
    return url;
  }
	
	
}
