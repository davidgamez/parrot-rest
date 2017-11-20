/**
 * 
 */
package parrot.rest;

import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@EnableAutoConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ParrotRestApplication.class})
public abstract class BaseIntegrationController {

  protected MockMvc mockMvc;
}
