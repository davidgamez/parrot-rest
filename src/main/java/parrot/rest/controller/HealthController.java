/**
 * 
 */
package parrot.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @author David Gamez
 *
 */
@Controller("health")
public class HealthController {

	
	@GetMapping
	public ResponseEntity<String> listen() {
		return new ResponseEntity<>("I'm a healthy parrot, do you have sunflower seeds for me?", HttpStatus.OK);
	}
}
