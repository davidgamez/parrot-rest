/**
 *
 */
package parrot.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author David Gamez
 *
 */
@EnableWebMvc
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

  @ExceptionHandler(value = { UrlNotFoundException.class})
  protected ResponseEntity<String> handleConflict(UrlNotFoundException ex, WebRequest request) {
    LOGGER.error("Exception while handling request [" + request.getDescription(true)+"] \n", ex);
    String body = String.format("URL not found %s", ex.getUrl());
    return  new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }

}
