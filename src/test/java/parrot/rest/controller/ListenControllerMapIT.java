/**
 * 
 */
package parrot.rest.controller;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.google.gson.Gson;

import parrot.rest.BaseIntegrationController;
import parrot.rest.common.Phrase;

/**
 *
 */
@PropertySource("classpath:application-test-map.properties")
public class ListenControllerMapIT extends BaseIntegrationController {

  private static final String JSON_RESPONSE = "{\"id\": 1, \"name\": \"Test response\"}";
  
  @Autowired
  ListenController listenController;
  
  @Before
  public void setup() {
    initMocks(this);
    this.mockMvc = standaloneSetup(listenController)
      .alwaysDo(print()).build();
  }
  
  @After
  public void validate() {
    Mockito.validateMockitoUsage();
  }
  
  @Test
  public void test_GetUserFundGroups() throws Exception {
    
    Phrase phrase = new Phrase();
    phrase.setAppContext("app_context");
    phrase.setUrl("the_url");
    phrase.setResponse(JSON_RESPONSE);
    
    Gson gson = new Gson();
    String json = gson.toJson(phrase);
    
    mockMvc.perform(post("/listen").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isOk())
      .andExpect(content().string("OK"));

  }
}
